package com.algo.ds.graph;


import java.util.Iterator;
import java.util.LinkedList;

import com.algo.ds.queue.QueueAsArray;
import com.algo.ds.stack.StackAsArray;

public class Graph {

	private int MAX_VERTEX = 20;
	private Vertex[] vertexArray;
	private int[][] adjMatrix;
	private int numVer;
	private StackAsArray stack;
	private QueueAsArray queue;
    // array of linked lists for adjacency list
    private LinkedList<Integer>[] adjList;
	
    public Graph(int n) {
		vertexArray = new Vertex[MAX_VERTEX];
		adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
        numVer = n;
		for(int i=0;i<MAX_VERTEX;i++)
			for(int j=0;j<MAX_VERTEX;j++)
				adjMatrix[i][j] = 0; // initialize
		stack = new StackAsArray(MAX_VERTEX);
		queue = new QueueAsArray(MAX_VERTEX);
        // for adjacency list
        adjList = new LinkedList[numVer];
        for (int i = 0; i < numVer; i++)
            adjList[i] = new LinkedList<>();
	}
	
	public void addVertex(char l){
		vertexArray[numVer++] = new Vertex(l);
	}
	
	public void addEdge(int start, int end){
		adjMatrix[start][end] = 1;
		adjMatrix[end][start] = 1;
	}
	
	public void displayVertex(int j){
		System.out.println(vertexArray[j].label);
	}
	
	/**
	 * Rule 1- If possible visit an adjacent unvisited vertex, mark it and push it onto the stack.
	 * 
	 * Rule 2 - If you can't follow Rule 1, because there are no adjacent unvisited vertex, then pop a vertex off the stack, if possible
	 * 
	 * Rule 3 - If you can't follow Rule 2, as the stack is empty then you are done.
	 * 
	 */
	public void depthFirstSearch(){
		vertexArray[0].wasVisited = true; // start with the root
		displayVertex(0); // display
		stack.push((char)0); // push it onto the stack
		
		while(!stack.isEmpty()){// until stack empty
			// find the first unvisited adjacent vertex 
			int d = findAdjUnvisitedVertex(stack.peek());
			if(d == -1)
				stack.pop();
			else{
				vertexArray[d].wasVisited = true;
				displayVertex(d);
				stack.push((char)d);
			}
		}
		
		// after all the vertex have been visited, reset them
		for(int i=0;i<numVer;i++)
			vertexArray[i].wasVisited = false;
		
	}

	/**
	 * According to adjacency matrix, 1 means the vertex is adjacent. 
	 * wasVisited = false, means the vertex is unvisited.
	 * @param i
	 * @return
	 */
	private int findAdjUnvisitedVertex(int i) {
		for(int j=0;j<numVer;j++){
			if(adjMatrix[i][j] == 1 && vertexArray[j].wasVisited == false)
				return j;
		}
		return -1;
	}
	
	/**
	 * Rule 1 - Visit the next unvisited adjacent vertex(if there is one) if possible, to the current vertex, mark it and insert it into the queue.
	 * 
	 * Rule 2 - If you cannot carry out Rule 1, because there is no more adjacent unvisited vertex, then remove an item from queue, if possible
	 * and make it current vertex.
	 * 
	 * Rule 3 - If you cannot carry out Rule 2, because queue is empty then you are done.
	 * 
	 */
	public void bredthFirstSearch(){
		vertexArray[0].wasVisited = true; // start with the top
		displayVertex(0);
		queue.insert(0);
		
		while(!queue.isEmpty()){ // until the queue is empty
			int v1 = queue.remove(); // remove vertex at head
			int v2 = findAdjUnvisitedVertex(v1);
			while(v2 != -1){
				vertexArray[v2].wasVisited = true;
				displayVertex(v2);
				queue.insert(v2);
			}
			// reset
			for(int i=0;i<numVer;i++)
				vertexArray[i].wasVisited = false;
		}
	}
	
    public void addEdge_adjList(int v, int w) {
        adjList[v].add(w);
    }

    public void bfs_adjacencyList(int n) {
        // checker for vertex visited or not. by default its valse
        boolean[] isVisited = new boolean[n];

        // create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
        isVisited[n] = true; // its visited
        queue.add(n);

        while (!queue.isEmpty()) {
            // remove element from queue
            Integer elem = queue.poll();
            System.out.println(elem);
            // Get all adjacent vertices of the dequeued vertex elem
            // If a adjacent has not been visited, then mark it visited and enqueue it
            for (Iterator<Integer> iter = adjList[elem].iterator(); iter.hasNext();) {
                Integer next = iter.next();
                if (!isVisited[next]) {
                    isVisited[next] = true;
                    queue.add(next);
                }
            }
        }

    }

	public void minimumSpanningTree(){
		vertexArray[0].wasVisited = true; // start at 0, mark it and push it.
		stack.push((char)0);
		
		while(!stack.isEmpty()){
			int currentVertex = stack.peek(); // current vertex.
			int v2 = findAdjUnvisitedVertex(currentVertex);
			if(v2 == -1)
				stack.pop();
			else{
				vertexArray[v2].wasVisited = true;
				stack.push((char)v2);
				
				displayVertex(currentVertex);
				displayVertex(v2);
			}
		}
	}
}
