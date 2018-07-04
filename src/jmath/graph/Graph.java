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

import upsilon.tools.ArrayTools;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A class representing a graph
 */
public class Graph <T> {

  private LinkedList<Node<T>> nodes = new LinkedList<>();


  public Graph(Node<T>... nodes) {

    if (nodes == null)
      throw new IllegalArgumentException("Nodes array cannot be null");
    if (ArrayTools.containsNull(nodes))
      throw new IllegalArgumentException("Nodes array cannot contain nulls");

    Collections.addAll(this.nodes, nodes);
  }

  public Graph(List<Node<T>> nodes) {

    if (nodes == null)
      throw new IllegalArgumentException("Nodes array cannot be null");
    if (nodes.contains(null))
      throw new IllegalArgumentException("Nodes array cannot contain nulls");

    this.nodes.addAll(nodes);
  }

  public boolean contains(Node<T> node) {
    return this.nodes.contains(node);
  }

  public boolean contains(T value) {

    for (Node<T> node : this.nodes) {
      if (node == null && value == null)
        return true;
      else if (node == null || value == null)
        continue;
      else if (node.getContained().equals(value))
        return true;
    }

    return false;
  }




  public Graph<T> addNode(Node<T>... nodes) {

    for (Node<T> node : nodes) {
      if (!this.nodes.contains(node))
        this.nodes.add(node);
    }

    return this;
  }

  public Graph<T> removeNode(Node<T>... nodes) {

    for (Node<T> node : nodes) {
      this.nodes.remove(node);
    }

    return this;
  }

  public Graph<T> removeAllNodes() {
    this.nodes.clear();
    return this;
  }


  public Node[] getNodes() {
    return nodes.toArray(new Node[nodes.size()]);
  }

  public Node<T> findNode(GraphNodeSearchInterface<T> finder) {

    if (finder == null)
      throw new IllegalArgumentException("Graph-node search interface cannot be null");

    for (Node<T> node : this.nodes) {
      if (finder.accept(node))
        return node;
    }

    return null;
  }


}
