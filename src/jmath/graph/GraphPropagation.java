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

package jmath.graph;

import java.util.LinkedList;

/**
 * A class to represent the propagation
 * of a method across a function
 */
public class GraphPropagation<T> {


  /**
   * Create a graph propagation object
   *
   * @param graph the graph with which to create the propagation
   * @param controller the propagation controller with which to create
   *                   the graph propagation
   * @return the specified graph propagation object
   */
  public static <T> GraphPropagation<T> create(
          Graph<T> graph,
          GraphPropagationController<T> controller
          ) {
    if (graph == null)
      throw new IllegalArgumentException("Graph cannot be null");
    if (controller == null)
      throw new IllegalArgumentException("Controller cannot be null");

    return new GraphPropagation<>(graph, controller, controller);
  }

  /**
   * Create a graph propagation
   *
   * @param graph the graph with which to create the propagation
   * @param effectController the propagation effect controller with which
   *                         to create the graph propagation
   * @param directionController the propagation direction controller with
   *                            which to create the graph propagation
   * @return the specified graph propagation object
   */
  public static <T> GraphPropagation<T> create(
          Graph<T> graph,
          GraphPropagationEffectController<T> effectController,
          GraphPropagationDirectionController<T> directionController
          ) {
    if (graph == null)
      throw new IllegalArgumentException("Graph cannot be null");
    if (effectController == null)
      throw new IllegalArgumentException("Effect controller cannot be null");
    if (directionController == null)
      throw new IllegalArgumentException("Direction controller cannot be null");

    return new GraphPropagation<>(graph, effectController, directionController);
  }

  /**
   * Propagates over the graph with the specified controller and
   * starting node
   *
   * @param graph a graph over which to propagate
   * @param gnsi an interface with which to find the start node
   * @param effectController controller to regulate the effect
   *                         of the propagation
   * @param directionController controller to regulate the
   *                            direction of the propagation
   * @param raiseExceptionOnInvalidNode if 'true' will raise an
   *                                    exception if a discovered node is invalid
   * @param treatGraphDirectionally if 'true' will propagate with respect to
   *                                the direction of the edges
   * @return the propagation used
   * @throws PropagationException if an invalid node is encountered
   *                              and the 'raiseExceptionOnInvalidNode' is true. Also
   *                              if the controller raises an exception to the invocation
   */
  public static <T> GraphPropagation<T> propagateOverGraph(
          Graph<T> graph,
          GraphNodeSearchInterface<T> gnsi,
          GraphPropagationEffectController<T> effectController,
          GraphPropagationDirectionController<T> directionController,
          boolean raiseExceptionOnInvalidNode,
          boolean treatGraphDirectionally
          ) throws PropagationException{
    return propagateOverGraph(
            graph,
            graph.findNode(gnsi),
            effectController,
            directionController,
            raiseExceptionOnInvalidNode,
            treatGraphDirectionally);
  }

  /**
   * Propagates over the graph with the specified controller and
   * starting node
   *
   * @param graph a graph over which to propagate
   * @param startNode a node in the graph at which to
   *                  begin the propagation
   * @param controller a controller to regulate the propagation
   *                            direction of the propagation
   * @param raiseExceptionOnInvalidNode if 'true' will raise an
   *                                    exception if a discovered node is invalid
   * @param treatGraphDirectionally if 'true' will propagate with respect to
   *                                the direction of the edges
   * @return the propagation used
   * @throws PropagationException if an invalid node is encountered
   *                              and the 'raiseExceptionOnInvalidNode' is true. Also
   *                              if the controller raises an exception to the invocation
   */
  public static <T> GraphPropagation<T> propagateOverGraph(
          Graph<T> graph,
          Node<T> startNode,
          GraphPropagationController<T> controller,
          boolean raiseExceptionOnInvalidNode,
          boolean treatGraphDirectionally
          ) throws PropagationException {
    GraphPropagation<T> ret = create(graph, controller);
    ret.propagate(startNode, raiseExceptionOnInvalidNode, treatGraphDirectionally);
    return ret;
  }

  /**
   * Propagates over the graph with the specified controllers and
   * starting node
   *
   * @param graph a graph over which to propagate
   * @param startNode a node in the graph at which to
   *                  begin the propagation
   * @param effectController controller to regulate the effect
   *                         of the propagation
   * @param directionController controller to regulate the
   *                            direction of the propagation
   * @param raiseExceptionOnInvalidNode if 'true' will raise an
   *                                    exception if a discovered node is invalid
   * @param treatGraphDirectionally if 'true' will propagate with respect to
   *                                the direction of the edges
   * @return the propagation used
   * @throws PropagationException if an invalid node is encountered
   *                              and the 'raiseExceptionOnInvalidNode' is true. Also
   *                              if one of the controllers raises an exception to the
   *                              invocation
   */
  public static <T> GraphPropagation<T> propagateOverGraph(
          Graph<T> graph,
          Node<T> startNode,
          GraphPropagationEffectController<T> effectController,
          GraphPropagationDirectionController<T> directionController,
          boolean raiseExceptionOnInvalidNode,
          boolean treatGraphDirectionally
          ) throws PropagationException {
    GraphPropagation<T> ret = create(graph, effectController, directionController);
    ret.propagate(startNode, raiseExceptionOnInvalidNode, treatGraphDirectionally);
    return ret;
  }




  private Graph<T> graph;
  private LinkedList<Node<T>> visitedNodes = new LinkedList<>();

  private GraphPropagationEffectController<T> effectController;
  private GraphPropagationDirectionController<T> directionController;

  private GraphPropagation(
          Graph<T> graph,
          GraphPropagationEffectController<T> effectController,
          GraphPropagationDirectionController<T> directionController
          ) {
    this.graph = graph;

    this.effectController = effectController;
    this.directionController = directionController;

  }

  /**
   *
   * Returns the graph object assigned to
   * this propagation object
   *
   * @return the graph object of this propagation
   */
  public Graph<T> getGraph() {
    return graph;
  }

  /**
   * Returns the array of nodes visited
   * during this propagation during the
   * last time 'propagate' was invoked
   * upon this object
   *
   * @return the array of nodes visited
   */
  public Node[] getVisitedNodes() {
    return visitedNodes.toArray(new Node[visitedNodes.size()]);
  }


  /**
   * Convenience method for
   * propagate(Graph, Node, boolean)
   * where the second and third arguments
   * default to 'false'
   *
   * @param start the initial node
   */
  public void propagate(
          Node<T> start
          ) throws PropagationException{
    propagate(start, false, false);
  }

  /**
   * Method to propagate through
   * the provided graph, starting
   * at the provided initial node
   *
   * @param start the initial node to start the propagation
   * @param raiseExceptionOnInvalidNode if 'true' will raise an
   *                                    exception if a discovered node is invalid
   * @param treatGraphDirectionally if 'true' will propagate with respect to
   *                                the direction of the edges
   */
  public void propagate(
          Node<T> start,
          boolean raiseExceptionOnInvalidNode,
          boolean treatGraphDirectionally
          ) throws PropagationException {
    if (start == null)
      throw new IllegalArgumentException("Starting node cannot be null");
    if (!graph.contains(start))
      throw new IllegalArgumentException("Node is not contained in the graph");

    visitedNodes.clear();

    try {
      effectController.applyEffect(start, null);
    } catch (Throwable t) {
      throw new PropagationException(t, "Effect controller raised exception");
    }

    propagateImp(graph, start, raiseExceptionOnInvalidNode, treatGraphDirectionally);
  }

  private void propagateImp(
          Graph<T> graph,
          Node<T> start,
          boolean raiseExceptionOnInvalidNode,
          boolean treatGraphDirectionally
          ) throws PropagationException{

    visitedNodes.add(start);

    for (Node<T> node : treatGraphDirectionally ? start.getOutgoingConnectedNodes() : start.getConnectedNodes()) {

      boolean allowTravel;
      try {
        allowTravel = directionController.allowTravel(node, start);
      } catch (Throwable t) {
        throw new PropagationException(t, "Direction controller raised exception");
      }

      if (allowTravel) {

        if (!graph.contains(node)) {
          if (raiseExceptionOnInvalidNode)
            throw new PropagationException("Encountered node outside of provided graph");
        }
        else if (visitedNodes.contains(node)) {
          if (raiseExceptionOnInvalidNode)
            throw new PropagationException("Encountered node circuit");
        }
        else {

          try {
            effectController.applyEffect(node, start);
          } catch (Throwable t) {
            throw new PropagationException(t, "Effect controller raised exception");
          }


          propagateImp(graph, node, raiseExceptionOnInvalidNode, treatGraphDirectionally);
        }


      }

    }
  }


}
