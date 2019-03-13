package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Hamiltonian Path in an undirected graph is a path that visits each vertex
 * exactly once. A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian
 * Path such that there is an edge (in graph) from the last vertex to the first
 * vertex of the Hamiltonian Path. Determine whether a given graph contains
 * Hamiltonian Cycle or not. If it contains, then print the path.
 * 
 * @author M_402201
 *
 * @param <T>
 */
public class HamiltonianCycle<T> {

    public boolean getHamiltonianCycle(Graph<T> graph,List<Vertex<T>> result){
        
        Vertex<T> startVertex = graph.getAllVertex().iterator().next();
        Set<Vertex<T>> visited = new HashSet<Vertex<T>>();
        return hamiltonianUtil(startVertex,startVertex,result,visited,graph.getAllVertex().size());     
        
    }
    
    private boolean hamiltonianUtil(Vertex<T> startVertex, Vertex<T> currentVertex
            , List<Vertex<T>> result, Set<Vertex<T>> visited, int totalVertex){
        visited.add(currentVertex);
        result.add(currentVertex);
        
        for(Vertex<T> child : currentVertex.getAdjacentVertexes()){
            if(startVertex.equals(child) && totalVertex == result.size()){
                result.add(startVertex);
                return true;
            }
            if(!visited.contains(child)){
                boolean isHamil = hamiltonianUtil(startVertex,child,result,visited,totalVertex);
                if(isHamil){
                    return true;
                }
            }
        }

		// if adding current vertex does not lead to a solution then backtrack by
		// removing it.
        result.remove(result.size()-1);
        visited.remove(currentVertex);
        return false;
    }
    
    public static void main(String args[]){
        
        Graph<Integer> graph = new Graph<Integer>(false);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 2);
        graph.addEdge(2, 4);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        
        HamiltonianCycle<Integer> hamil = new HamiltonianCycle<Integer>();
        List<Vertex<Integer>> result = new ArrayList<Vertex<Integer>>();
        boolean isHamiltonian = hamil.getHamiltonianCycle(graph, result);
        System.out.println(isHamiltonian);
        if(isHamiltonian){
            System.out.print(result);
        }
        
    }
}
