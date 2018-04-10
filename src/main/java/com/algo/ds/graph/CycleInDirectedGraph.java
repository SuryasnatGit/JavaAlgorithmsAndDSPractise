package com.algo.ds.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * http://www.geeksforgeeks.org/detect-cycle-in-a-graph/.
 * 
 * Depth First Traversal can be used to detect cycle in a Graph. DFS for a connected graph produces
 * a tree. There is a cycle in a graph only if there is a back edge present in the graph. A back
 * edge is an edge that is from a node to itself (self loop) or one of its ancestor in the tree
 * produced by DFS.
 * 
 * For a disconnected graph, we get the DFS forest as output. To detect cycle, we can check for
 * cycle in individual trees by checking back edges.
 * 
 * To detect a back edge, we can keep track of vertices currently in recursion stack of function for
 * DFS traversal. If we reach a vertex that is already in the recursion stack, then there is a cycle
 * in the tree. The edge that connects current vertex to the vertex in the recursion stack is back
 * edge. We have used recStack[] array to keep track of vertices in the recursion stack.
 * 
 * Time Complexity of this method is same as time complexity of DFS traversal which is O(V+E).
 */
public class CycleInDirectedGraph {

    public boolean hasCycle(Graph<Integer> graph) {
        Set<Vertex<Integer>> whiteSet = new HashSet<>();
        Set<Vertex<Integer>> graySet = new HashSet<>();
        Set<Vertex<Integer>> blackSet = new HashSet<>();

        for (Vertex<Integer> vertex : graph.getAllVertex()) {
            whiteSet.add(vertex);
        }

        while (whiteSet.size() > 0) {
            Vertex<Integer> current = whiteSet.iterator().next();
            if(dfs(current, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(Vertex<Integer> current, Set<Vertex<Integer>> whiteSet,
                        Set<Vertex<Integer>> graySet, Set<Vertex<Integer>> blackSet ) {
        //move current to gray set from white set and then explore it.
        moveVertex(current, whiteSet, graySet);
        for(Vertex<Integer> neighbor : current.getAdjacentVertexes()) {
            //if in black set means already explored so continue.
            if (blackSet.contains(neighbor)) {
                continue;
            }
            //if in gray set then cycle found.
            if (graySet.contains(neighbor)) {
                return true;
            }
            if(dfs(neighbor, whiteSet, graySet, blackSet)) {
                return true;
            }
        }
        //move vertex from gray set to black set when done exploring.
        moveVertex(current, graySet, blackSet);
        return false;
    }

    private void moveVertex(Vertex<Integer> vertex, Set<Vertex<Integer>> sourceSet,
                            Set<Vertex<Integer>> destinationSet) {
        sourceSet.remove(vertex);
        destinationSet.add(vertex);
    }

    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        CycleInDirectedGraph cdg = new CycleInDirectedGraph();
        System.out.println(cdg.hasCycle(graph));
    }
}
