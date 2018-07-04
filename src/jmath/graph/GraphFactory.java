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
import jmath.types.Vector;

/**
 * A static class for generating
 * commonly used graphs of nodes
 */
public /*static*/ class GraphFactory {
  private GraphFactory() {  }

  public static <T> Graph<T> get2dGrid(int width, int height) {
    return get2dGrid(
            width,
            height,
            (v0, v1) -> true,
            v -> null
          );
  }
  public static <T> Graph<T> get2dGrid(
          int width,
          int height,
          NodeEdgeInterface nei,
          NodeContentInterface<T> nci) {

    if (width <= 0 || height <= 0)
      throw new IllegalArgumentException(String.format("Width and height must both be positive (%d,%d)", width, height));
    if (nei == null)
      throw new IllegalArgumentException("Node-edge interface / lambda may not be null");
    if (nci == null)
      throw new IllegalArgumentException("Node content interface / lambda may not be null");


    Node[] grid = new Node[width * height];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {

        Vector position = new Vector(x, y);
        Node<T> node = new Node<>(nci.getNodeContent(position), position);
        grid[y * width + x] = node;

      }
    }

    for (int x = 0; x < width - 1; x++) {
      for (int y = 0; y < height - 1; y++) {

        if (nei.mayFormEdge(new Vector(x, y), new Vector(x, y + 1)))
          grid[y * width + x].connect(grid[(y + 1) * width + x]);
        if (nei.mayFormEdge(new Vector(x, y), new Vector(x + 1, y)))
          grid[y * width + x].connect(grid[y * width + x + 1]);

      }
    }

    if (width > 1) {
      for (int y = 0; y < height - 1; y++) {

        if (nei.mayFormEdge(new Vector(y, width - 1), new Vector(y + 1, width - 1)))
          grid[y * width + width - 1].connect(grid[(y + 1) * width + width - 1]);

      }
    }
    if (height > 1) {
      for (int x = 0; x < width - 1; x++) {

        if (nei.mayFormEdge(new Vector(height - 1, x), new Vector(height - 1, x + 1)))
          grid[(height - 1) * width + x].connect(grid[(height - 1) * width + x + 1]);

      }
    }

    return new Graph<T>(grid);
  }


  public static <T> Graph<T> getCompleteGraph(int n) {

    if (n <= 0)
      throw new IllegalArgumentException(String.format("Cannot create complete graph of less than 1 node (%d)", n));

    Node<T>[] nodes = new Node[n];
    ArrayTools.fill(nodes, (k) -> new Node<T>());

    for (int k = 0; k < nodes.length - 1; k++) {
      for (int i = k + 1; i < nodes.length; i++) {
        nodes[k].connect(nodes[i]);
      }
    }

    return new Graph<>(nodes);
  }





}
