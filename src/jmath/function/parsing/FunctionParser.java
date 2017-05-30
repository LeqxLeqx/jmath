/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\
 *                                                                         *
 *  jmath: a library for mathematical computation for Java                 *
 *  Copyright (C) 2017  LeqxLeqx                                           *
 *                                                                         *
 *  This program is free software: you can redistribute it and/or modify   *
 *  it under the terms of the GNU General Public License as published by   *
 *  the Free Software Foundation, either version 3 of the License, or      *
 *  (at your option) any later version.                                    *
 *                                                                         *
 *  This program is distributed in the hope that it will be useful,        *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of         *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *
 *  GNU General Public License for more details.                           *
 *                                                                         *
 *  You should have received a copy of the GNU General Public License      *
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.  *
 *                                                                         *
\* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package jmath.function.parsing;

import jmath.function.*;
import jmath.types.Complex;
import jmath.types.NMatrix;
import jmath.types.NVector;

import java.util.LinkedList;

/**
 * A class whose purpose is to
 * parse strings into an evaluable
 */
public class FunctionParser {

  private static final byte
          ELEMENT_PARSE_PHASE_NEW = 0,
          ELEMENT_PARSE_PHASE_IDENTIFIER = 1,
          ELEMENT_PARSE_PHASE_OPERATOR = 2,
          ELEMENT_PARSE_PHASE_LITERAL_COMPLEX = 13,
          ELEMENT_PARSE_PHASE_LITERAL_VECTOR = 14,
          ELEMENT_PARSE_PHASE_LITERAL_MATRIX = 15,
          ELEMENT_PARSE_PHASE_LITERAL_VECTOR_OR_MATRIX = 16
                  ;

  public static Evaluable parseEvaluable(String string) {
    FunctionParser functionParser = new FunctionParser(string);
    functionParser.generateElements();

    return functionParser.collateElements(functionParser.parsedElements);
  }

  public static Equation parseEquation(String string) {
    FunctionParser functionParser = new FunctionParser(string);
    functionParser.generateElements();

    return functionParser.collateEquation(functionParser.parsedElements);
  }



  private LinkedList<LinkedList<Element>> splitElementListUnparenthetical(LinkedList<Element> elements, ElementType split) {
    LinkedList<LinkedList<Element>> ret = new LinkedList<>();
    ret.add(new LinkedList<>());
    int depth = 0;


    for(Element e : elements) {


      if (depth == -1)
        throw new ParsingException(this, "Encountered parenthetical-like statement mismatch");

      if (e.type.opening)
        depth++;
      else if (e.type.closing)
        depth--;

      if (depth == 0 && e.type == split)
        ret.add(new LinkedList<>());
      else
        ret.getLast().add(e);
    }

    return ret;
  }

  private LinkedList<LinkedList<Element>> splitElementListUnparenthetical(LinkedList<Element> elements, ElementType... split) {
    LinkedList<LinkedList<Element>> ret = new LinkedList<>();
    ret.add(new LinkedList<>());
    int depth = 0;


    for(Element e : elements) {


      if (depth == -1)
        throw new ParsingException(this, "Encountered parenthetical-like statement mismatch");

      if (e.type.opening)
        depth++;
      else if (e.type.closing)
        depth--;

      boolean isSplitElement = false;
      for(ElementType et : split) {
        if (e.type == et)
          isSplitElement = true;
      }
      if (depth == 0 && isSplitElement)
        ret.add(new LinkedList<>());
      else
        ret.getLast().add(e);
    }

    return ret;
  }


  private Equation collateEquation(LinkedList<Element> elements) {
    LinkedList<LinkedList<Element>> split = splitElementListUnparenthetical(
            elements,
            ElementType.EQUALS,
            ElementType.GREATER_THAN,
            ElementType.LESS_THAN,
            ElementType.GREATER_THAN_OR_EQUAL,
            ElementType.LESS_THAN_OR_EQUAL
            );

    if (split.size() != 2)
      throw new ParsingException(this, "Equation has invalidly many comparing operators");

    LinkedList<Element> left = split.get(0), right = split.get(1);
    ElementType relativeElement = elements.get(left.size()).type;

    Evaluable
            e1 = collateElements(left),
            e2 = collateElements(right)
            ;

    EquationType et;
    switch (relativeElement) {

      case EQUALS:
        et = EquationType.EQUAL;
        break;
      case GREATER_THAN:
        et = EquationType.GREATER_THAN;
        break;
      case GREATER_THAN_OR_EQUAL:
        et = EquationType.GREATER_THAN_OR_EQUAL;
        break;
      case LESS_THAN:
        et = EquationType.LESS_THAN;
        break;
      case LESS_THAN_OR_EQUAL:
        et = EquationType.LESS_THAN_OR_EQUAL;
        break;

      default:
        throw new RuntimeException();
    }

    return new Equation(et, new Expression(e1), new Expression(e2));
  }


  private Evaluable collateElements(LinkedList<Element> elements) {
    if (elements.size() == 0)
      throw new ParsingException(this, "Element collate attempt failed due to insufficiently many elements");


    resolveParentheticalStatements(elements);
    resolveAbsoluteValueStatements(elements);

    resolveReplaceVariables(elements);
    resolveNegatives(elements);

    resolveBaseArithmeticFunctions(elements);

    if (elements.size() != 1)
      throw new ParsingException(this, "Element collate attempt failed due to irresponsibility of provided elements");

    Element e = elements.get(0);
    if (e.type == ElementType.EVALUABLE)
      return e.asEvaluable();
    else if (e.type == ElementType.LITERAL)
      return new Value<>(e.value);
    else
      throw new ParsingException(this, "Element collate attempt failed due to invalid resolved element type");
  }



  private void resolveReplaceVariables(LinkedList<Element> elements) {
    for(int k = 0; k < elements.size(); k++) {

      Element e = elements.get(k);
      if (e.type == ElementType.VARIABLE) {
        elements.remove(k);
        e = new Element(new Variable(e.asString()), ElementType.EVALUABLE);
        elements.add(k, e);
      }

    }
  }

  private Element getNegation(Element element) {

    Object o;
    ElementType t;

    switch (element.type) {

      case EVALUABLE:
        o = new ArithmeticOperation(OperationType.MULTIPLICATION, new Value<>(-1.0), element.asEvaluable());
        t = ElementType.EVALUABLE;
        break;

      case LITERAL:
        switch (element.getLiteralType()) {

          case REAL:
            o = -element.asDouble();
            break;
          case COMPLEX:
            o = element.asComplex().negative();
            break;
          case VECTOR:
            o = element.asVector().negative();
            break;
          case MATRIX:
            o = element.asMatrix().negative();
            break;
          default:
            throw new RuntimeException();

        }
        t = ElementType.LITERAL;
        break;

      default:
        throw new ParsingException(this, "Negation failed on element of type: " + element.type.toString().toLowerCase());

    }

    return new Element(o, t);
  }

  private Element evaluateElements(Element arg1, ElementType operation, Element arg2) {

    Evaluable
            e1,
            e2
            ;
    Operation ret;

    if (arg1.type == ElementType.LITERAL)
      e1 = new Value<>(arg1.value);
    else if (arg1.type == ElementType.EVALUABLE)
      e1 = arg1.asEvaluable();
    else
      throw new ParsingException(this, "Failed to evaluate arithmetic operation due to invalid left argument element type: " + arg1.type.toString().toLowerCase());

    if (arg2.type == ElementType.LITERAL)
      e2 = new Value<>(arg2.value);
    else if (arg2.type == ElementType.EVALUABLE)
      e2 = arg2.asEvaluable();
    else
      throw new ParsingException(this, "Failed to evaluate arithmetic operation due to invalid right argument element type: " + arg1.type.toString().toLowerCase());


    switch (operation) {

      case ADD:
        ret = new ArithmeticOperation(OperationType.ADDITION, e1, e2);
        break;
      case SUB:
        ret = new ArithmeticOperation(OperationType.SUBTRACTION, e1, e2);
        break;
      case MUL:
        ret = new ArithmeticOperation(OperationType.MULTIPLICATION, e1, e2);
        break;
      case DIV:
        ret = new ArithmeticOperation(OperationType.DIVISION, e1, e2);
        break;
      case POW:
        ret = new ArithmeticOperation(OperationType.EXPONENT, e1, e2);
        break;

      default:
        throw new ParsingException(this, "Failed to evaluate arithmetic operation of type: " + operation.toString().toLowerCase());

    }

    return new Element(ret, ElementType.EVALUABLE);
  }

  private void resolveBaseArithmeticFunctions(LinkedList<Element> elements) {

    ElementType[] operations = new ElementType[] {
            ElementType.POW,
            ElementType.MUL,
            ElementType.DIV,
            ElementType.SUB,
            ElementType.ADD,
      };

    for(ElementType op : operations) {

      boolean found;

      do {
        found = false;
        for (int i = 1; i < elements.size() - 1; i++) {
          Element e = elements.get(i);

          if (e.type == op) {
            found = true;

            Element newElement = evaluateElements(elements.get(i - 1), e.type, elements.get(i + 1));

            elements.remove(i + 1);
            elements.remove(i);
            elements.remove(i - 1);

            elements.add(i - 1, newElement);
          }

        }
      } while (found);
    }


  }

  private void resolveNegatives(LinkedList<Element> elements) {

    for(int k = elements.size() - 2; k >= 0; k--) {

      Element
        after = elements.get(k + 1),
        middle = elements.get(k),
        before = k - 1 >= 0 ? elements.get(k - 1) : null;

      if(
              middle.type == ElementType.SUB &&
              (
                after.type == ElementType.LITERAL ||
                after.type == ElementType.EVALUABLE
              ) &&
              (
                before == null ||
                (
                  before.type != ElementType.LITERAL &&
                  before.type != ElementType.EVALUABLE
                )
              )
         ) {
        elements.remove(k);
        elements.remove(k - 1);

        elements.add(k - 1, getNegation(after));

        k = elements.size() - 1;
      }

    }
  }

  private void resolveAbsoluteValueStatements(LinkedList<Element> elements) {
    int depth = 0, start, end;

    do {

      start = -1;
      end = -1;

      for(int k = 0; k < elements.size() && end == -1; k++) {
        Element e = elements.get(k), last;

        if (start == -1) {
          if (e.type == ElementType.BAR)
            start = k;
        }
        else {
          last = elements.get(k - 1);

          if (e.type == ElementType.BAR) {
            if (
                last.type == ElementType.ADD ||
                last.type == ElementType.SUB ||
                last.type == ElementType.MUL ||
                last.type == ElementType.DIV ||
                last.type == ElementType.POW
               )
              depth++;
            else
              depth--;

            if (depth == -1)
              end = k;
            else if (depth < -1)
              throw new ParsingException(this, "Encountered absolute-value element mismatch");
          }

        }
      }


      if (end == -1)
        continue;


      elements.remove(end);
      LinkedList<Element> inner = exciseRange(elements, start + 1, end);
      elements.remove(start);

      Evaluable op = collateElements(inner);
      elements.add(start, new Element(new UnaryOperation(OperationType.ABSOLUTE_VALUE, op), ElementType.EVALUABLE));

    } while (end != -1);


  }

  private LinkedList<Element> exciseRange(LinkedList<Element> elements, int start, int end) {

    LinkedList<Element> within = new LinkedList<>();

    for(int k = end - 1; k >= start; k--) {
      within.add(0, elements.remove(k)); /* add to 0 retains element orders */
    }

    return within;
  }

  private void resolveParentheticalStatements(LinkedList<Element> elements) {

    int[] parvals;

    do {

      parvals = findOpenAndCloseBracket(elements);
      if (parvals == null) continue;

      LinkedList<Element> within;
      Element startElement, endElement;


      endElement = elements.remove(parvals[1]);
      within = exciseRange(elements, parvals[0] + 1, parvals[1]);
      startElement = elements.remove(parvals[0]);

      Evaluable op;
      if(startElement.type == ElementType.FUNCTION) {
        op = collateElementsForFunction(within, startElement,  endElement);
        elements.add(parvals[0], new Element(op, ElementType.EVALUABLE));
      }
      else if (startElement.type == ElementType.OPEN_BRACKET) {
        op = collateElementsForPieceWiseFunction(within, startElement, endElement);
        elements.add(parvals[0], new Element(op, ElementType.EVALUABLE));
      }
      else {
        op = collateElements(within); /* Recursive call */
        elements.add(parvals[0], new Element(addSurroundingOperation(op, startElement, endElement), ElementType.EVALUABLE));
      }

    } while( parvals != null );


  }

  private Evaluable collateElementsForFunction(LinkedList<Element> elements, Element start, Element end) {
    if (end.type != ElementType.CLOSE_PARENTHETICAL)
      throw new ParsingException(this, "Encountered functional statement which is not terminated with a parenthetical");

    LinkedList<LinkedList<Element>> split = splitElementListUnparenthetical(elements, ElementType.COMMA);

    Evaluable[] operations = new Evaluable[split.size()];

    for(int k = 0; k < operations.length; k++) {
      operations[k] = collateElements(split.get(k));
    }

    String funcName = start.asString(), namespace = "";
    if (funcName.contains(".")) {
      String[] funcSplit = funcName.split("\\.", 2);
      namespace = funcSplit[0];
      funcName = funcSplit[1];
    }

    return OperationFactory.get(namespace, funcName, operations);
  }

  private Evaluable collateElementsForPieceWiseFunction(LinkedList<Element> elements, Element start, Element end) {
    if (end.type != ElementType.CLOSE_BRACKET)
      throw new ParsingException(this, "Encountered piece-wise function statement which is not terminated with a bracket");

    LinkedList<LinkedList<Element>> primarySplit = splitElementListUnparenthetical(elements, ElementType.COMMA);

    if (primarySplit.size() != 2)
      throw new ParsingException(this, "Encountered piece-wise function of too many alternate evaluation subdivisions");

    LinkedList<Element>
            firstAndEquation = primarySplit.get(0),
            second = primarySplit.get(1),
            first,
            equation
        ;
    LinkedList<LinkedList<Element>> firstAndEquationSplit = splitElementListUnparenthetical(firstAndEquation, ElementType.COLON);

    if (firstAndEquationSplit.size() != 2)
      throw new ParsingException(this, "Encountered piece-wise function of too many conditional subdivisions");

    first = firstAndEquationSplit.get(0);
    equation = firstAndEquationSplit.get(1);

    Evaluable
            e1 = collateElements(first),
            e2 = collateElements(second)
            ;
    Equation
            eq = collateEquation(equation);

    return new PieceWiseOperation(e1, e2, eq);
  }

  private Evaluable addSurroundingOperation(Evaluable op, Element startElement, Element endElement) {

    if (
            startElement.type == ElementType.OPEN_PARENTHETICAL &&
            endElement.type == ElementType.CLOSE_PARENTHETICAL
            )
      return op;
    else if (
            startElement.type == ElementType.OPEN_CEILING &&
            endElement.type == ElementType.CLOSE_CEILING
            )
      return new UnaryOperation(OperationType.CEILING, op);
    else if (
            startElement.type == ElementType.OPEN_FLOOR &&
            endElement.type == ElementType.CLOSE_FLOOR
            )
      return new UnaryOperation(OperationType.FLOOR, op);


    throw new RuntimeException();
  }

  private int[] findOpenAndCloseBracket(LinkedList<Element> elements) {

    boolean found = false;
    int[] ret = new int[2];

    int top = 0;
    int depth = -1;
    for(Element e : elements) {

      if (depth == -1) {
        if (e.type.opening) {
          depth = 0;
          ret[0] = top;
          found = true;
        }
        else if (e.type.closing) {
          throw new ParsingException(this, "Encountered parenthetical-like statement mismatch");
        }
      }
      else {
        if (e.type.opening)
          depth++;
        else if (e.type.closing)
          depth--;

        if (depth == -1) {
          ret[1] = top;
          break;
        }
      }


      top++;
    }

    if (!found)
      return null;
    if (depth != -1)
      throw new ParsingException(this, "Encountered parenthetical-like statement mismatch");

    return ret;
  }


  private boolean isOperatorChar(char c) {
    switch (c) {
      case '+':
      case '-':
      case '*':
      case '/':
      case '%':
      case '^':
      case '=':
      case '>':
      case '<':
      case '\u2265':
      case '\u2264':
        return true;
      default:
        return false;

    }
  }

  private final LinkedList<Element> parsedElements = new LinkedList<>();
  final String string;
  private String buffer;
  private int top = 0;
  private byte phase;

  private FunctionParser(String string) {
    if (string == null)
      throw new IllegalArgumentException("Cannot parse null string");
    this.string = string + '\0';
  }

  private boolean hasNextChar() {
    return top < string.length();
  }

  private char nextChar() {

    if(top >= string.length())
      throw new RuntimeException();
    else
      return string.charAt(top);
  }

  private void forwardChar() {
    top++;
  }


  private boolean parseUniCharacterElement(char c) {

    ElementType element;
    switch (c) {

      case '+':
        element = ElementType.ADD;
        break;
      case '-':
        element = ElementType.SUB;
        break;
      case '*':
        element = ElementType.MUL;
        break;
      case '/':
        element = ElementType.DIV;
        break;
      case '^':
        element = ElementType.POW;
        break;
      case '%':
        element = ElementType.MOD;
        break;
      case '=':
        element = ElementType.EQUALS;
        break;
      case '>':
        element = ElementType.GREATER_THAN;
        break;
      case '<':
        element = ElementType.LESS_THAN;
        break;
      case '\u2265':
        element = ElementType.GREATER_THAN_OR_EQUAL;
        break;
      case '\u2264':
        element = ElementType.LESS_THAN_OR_EQUAL;
        break;

      case '(':
        element = ElementType.OPEN_PARENTHETICAL;
        break;
      case ')':
        element = ElementType.CLOSE_PARENTHETICAL;
        break;

      case '{':
        element = ElementType.OPEN_BRACKET;
        break;
      case '}':
        element = ElementType.CLOSE_BRACKET;
        break;

      case 0x230A:
        element = ElementType.OPEN_FLOOR;
        break;
      case 0x230B:
        element = ElementType.CLOSE_FLOOR;
        break;

      case 0x2308:
        element = ElementType.OPEN_CEILING;
        break;
      case 0x2309:
        element = ElementType.CLOSE_CEILING;
        break;

      case '|':
        element = ElementType.BAR;
        break;
      case ':':
        element = ElementType.COLON;
        break;
      case ',':
        element = ElementType.COMMA;
        break;

      default:
        element = null;
    }

    if (element == null)
      return false;

    parsedElements.add(new Element(element));

    return true;
  }


  private void generateElements() {

    phase = ELEMENT_PARSE_PHASE_NEW;
    buffer = "";

    while(hasNextChar()) {
      char c = nextChar();
      if (Character.isWhitespace(c)) {
        forwardChar();
        continue;
      }

      switch(phase) {

        case ELEMENT_PARSE_PHASE_NEW:
          phaseNew(c);
          break;

        case ELEMENT_PARSE_PHASE_IDENTIFIER:
          phaseIdentifier(c);
          break;

        case ELEMENT_PARSE_PHASE_LITERAL_COMPLEX:
          phaseComplex(c);
          break;

        case ELEMENT_PARSE_PHASE_LITERAL_VECTOR_OR_MATRIX:
          phaseVectorOrMatrix(c);
          break;

        case ELEMENT_PARSE_PHASE_LITERAL_VECTOR:
          phaseVector(c);
          break;

        case ELEMENT_PARSE_PHASE_LITERAL_MATRIX:
          phaseMatrix(c);
          break;

        default:
          throw new RuntimeException();

      }
    }

    if (phase != ELEMENT_PARSE_PHASE_NEW)
      throw new ParsingException(this, "Element parse failed due to unexpected premature string termination");

  }

  private void phaseNew(char c) {
    if (parseUniCharacterElement(c))
      forwardChar();
    else if (Character.isAlphabetic(c))
      phase = ELEMENT_PARSE_PHASE_IDENTIFIER;
    else if (isOperatorChar(c))
      phase = ELEMENT_PARSE_PHASE_OPERATOR;
    else if (Character.isDigit(c))
      phase = ELEMENT_PARSE_PHASE_LITERAL_COMPLEX;
    else if (c == '[')
      phase = ELEMENT_PARSE_PHASE_LITERAL_VECTOR_OR_MATRIX;
    else if (c == '\0')
      forwardChar(); /* Should end */
    else
      throw new ParsingException(this, "Element parse failed as a result of encountering an unexpected character: '" + c + "'");

  }
  private void phaseIdentifier(char c) {
    if (
          Character.isAlphabetic(c) ||
          Character.isDigit(c) ||
          c == '_'
            ) {
      buffer += c;
      forwardChar();
    }
    else if (c == '.' && !buffer.contains(".")) {
      buffer += c;
      forwardChar();
    }
    else {
      phase = ELEMENT_PARSE_PHASE_NEW;
      if (c == '(') {
        parsedElements.add(new Element(buffer, ElementType.FUNCTION)); /* function is assume to be an opening bracket */
        forwardChar();
      }
      else
        parsedElements.add(new Element(buffer, ElementType.VARIABLE));
      buffer = "";
    }
  }
  private void phaseComplex(char c) {
    if (Character.isDigit(c) || c == '.') {
      buffer += c;
      forwardChar();
    }
    else if (c == 'i') {
      buffer += c;
      parsedElements.add(new Element(Complex.parse(buffer), ElementType.LITERAL));
      buffer = "";
      forwardChar();
      phase = ELEMENT_PARSE_PHASE_NEW;
    }
    else {
      parsedElements.add(new Element(Double.parseDouble(buffer), ElementType.LITERAL));
      buffer = "";
      phase = ELEMENT_PARSE_PHASE_NEW;
    }
  }
  private void phaseVector(char c) {
    buffer += c;

    if (c == ']') {
      phase = ELEMENT_PARSE_PHASE_NEW;
      parsedElements.add(new Element(NVector.parse(buffer), ElementType.LITERAL));
      buffer = "";
    }

    forwardChar();
  }
  private void phaseMatrix(char c) {
    buffer += c;

    if (buffer.endsWith("]]")) {
      phase = ELEMENT_PARSE_PHASE_NEW;
      parsedElements.add(new Element(NMatrix.parse(buffer), ElementType.LITERAL));
      buffer = "";
    }

    forwardChar();
  }
  private void phaseVectorOrMatrix(char c) {
    if (!buffer.isEmpty()){
      if (c == '[')
        phase = ELEMENT_PARSE_PHASE_LITERAL_MATRIX;
      else
        phase = ELEMENT_PARSE_PHASE_LITERAL_VECTOR;
    }

    buffer += c;
    forwardChar();
  }



}
