/*
 * Copyright 2019 rugalbernstein.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ga.rugal.leetcode.courseschedule;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * @author rugalbernstein
 */
public class Solution {

  public boolean canFinish(final int numCourses, final int[][] prerequisites) {
    final Graph g = new Graph(numCourses);
    for (int[] pre : prerequisites) {
      //from -> to
      g.addEdge(pre[1], pre[0]);
    }

    final Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < g.nVertices; i++) {
      //courses that have no prerequisite
      if (g.inDegrees[i] == 0) {
        queue.add(i);
      }
    }
    //only process this many nodes
    //if we have more, or few number of nodes got processed, means this is not topologically sortable
    int topSortNodes = g.nVertices;
    //start from courses that have no prerequisite
    while (!queue.isEmpty()) {
      topSortNodes--;
      for (int node : g.adjacencyList[queue.poll()]) {
        //similarly, add these that have no prerequisite after removing the up relationship
        if (0 == --g.inDegrees[node]) {
          queue.add(node);
        }
      }
    }
    return topSortNodes == 0;
  }

  class Graph {

    final int nVertices;

    final LinkedList<Integer>[] adjacencyList;

    /**
     * How many edges connect to this vertices. 0 means it is a start.
     */
    final int[] inDegrees;

    Graph(int n) {
      nVertices = n;
      adjacencyList = new LinkedList[n];
      for (int i = 0; i < n; i++) {
        adjacencyList[i] = new LinkedList<>();
      }
      inDegrees = new int[n];
    }

    private void addEdge(final int src, final int destination) {
      adjacencyList[src].add(destination);
      inDegrees[destination]++;
    }
  }
}
