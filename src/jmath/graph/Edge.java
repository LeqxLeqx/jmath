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

/**
 * A class whose instantiated objects
 * represent connections between nodes
 * in a graph
 */
public class Edge {

  private Node start, end;
  public double weight;


  public Edge(Node start, Node end, double weight) {

    if (start == null || end == null)
      throw new IllegalArgumentException("Edge cannot be connected to null node");

    this.start = start;
    this.end = end;
    this.weight = weight;
  }

  public <T> Node<T> getStart() {
    return (Node<T>) start;
  }
  public <T> Node<T> getEnd() {
    return (Node<T>) end;
  }


  public boolean has(Node node) {
    return
            start == node || end == node;
  }


}
