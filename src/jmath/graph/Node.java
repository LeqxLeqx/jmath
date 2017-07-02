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

import jmath.types.Vector;

import java.util.LinkedList;

/**
 * A class representing a node contained
 * by a graph
 */
public class Node <T> {


  private T contained;
  private Vector position = Vector.ZERO;
  private LinkedList<Edge> edges = new LinkedList<>();

  public Node () { }

  public Node (T contained) {
    this.contained = contained;
  }

  public Node (T contained, Vector position) {
    this(contained);

    if (position == null)
      throw new IllegalArgumentException("Position cannot be null");

    this.position = position;
  }


  public synchronized T getContained() {
    return contained;
  }

  public synchronized Vector getPosition() {
    return position;
  }

  public synchronized Edge[] getEdges() {
    return edges.toArray(new Edge[edges.size()]);
  }

  public synchronized Node[] getConnectedNodes() {
    LinkedList<Node> ret = new LinkedList<>();
    for (Edge edge : edges) {

      if (edge.getStart() != this && !ret.contains(edge.getStart()))
        ret.add(edge.getStart());
      else if (edge.getEnd() != this && !ret.contains(edge.getEnd()))
        ret.add(edge.getEnd());

    }

    return ret.toArray(new Node[ret.size()]);
  }

  public synchronized Node[] getOutgoingConnectedNodes() {
    LinkedList<Node> ret = new LinkedList<>();
    for (Edge edge : edges) {

      if (edge.getStart() == this && !ret.contains(edge.getEnd()))
        ret.add(edge.getEnd());

    }

    return ret.toArray(new Node[ret.size()]);
  }

  public synchronized Node[] getIncomingConnectedNodes() {
    LinkedList<Node> ret = new LinkedList<>();
    for (Edge edge : edges) {

      if (edge.getEnd() == this && !ret.contains(edge.getStart()))
        ret.add(edge.getStart());

    }

    return ret.toArray(new Node[ret.size()]);
  }



  public synchronized Node<T> setContained(T contained) {
    this.contained = contained;

    return this;
  }

  public synchronized Node<T> setPosition(Vector position) {
    if (position == null)
      throw new IllegalArgumentException("Cannot set position to null");

    this.position = position;
    return this;
  }



  public synchronized boolean isConnected(Node<T> node) {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    synchronized (node) {

      for (Edge e : edges) {

        if (e.has(node))
          return true;

      }

      return false;
    }
  }

  public synchronized Edge[] getConnectingEdges(Node<T> node) {
    if (node == null)
      throw new IllegalArgumentException("Node cannot be null");

    synchronized (node) {

      LinkedList<Edge> ret = new LinkedList<>();

      for (Edge e : edges) {

        if (e.has(node))
          ret.add(e);

      }

      return ret.toArray(new Edge[ret.size()]);
    }

  }




  public Edge connect(Node<T> node) {
    return connect(node, 1.0);
  }
  public synchronized Edge connect(Node<T> node, double weight) {

    if (node == null)
      throw new IllegalArgumentException("Cannot connect to null node");

    Edge edge = new Edge(this, node, weight);
    edges.add(edge);
    node.edges.add(edge);

    return edge;
  }


  public synchronized boolean disconnect(Edge edge) {
    if (edge.getStart() != this && edge.getEnd() != this)
      throw new IllegalArgumentException("cannot disconnect edge which is not extant from the node");

    Node<T> other;
    if (edge.getStart() == this)
      other = edge.getEnd();
    else
      other = edge.getStart();


    boolean
      r0 = other.edges.remove(edge),
      r1 = edges.remove(edge);

    return r0 && r1;
  }
  public synchronized boolean disconnect(Node<T> node, double weight) {

    for (int k = 0; k < edges.size(); k++) {
      if (
          (edges.get(k).getEnd() == node || edges.get(k).getStart() == node) &&
          weight == edges.get(k).weight
              ) {
        Edge removedEdge = edges.remove(k);
        node.edges.remove(removedEdge);
        return true;
      }
    }

    return false;
  }
  public synchronized boolean disconnect(Node<T> node) {

    for (int k = 0; k < edges.size(); k++) {
      if (edges.get(k).getEnd() == node || edges.get(k).getStart() == node) {
        Edge removedEdge = edges.remove(k);
        node.edges.remove(removedEdge);
        return true;
      }
    }

    return false;
  }



}
