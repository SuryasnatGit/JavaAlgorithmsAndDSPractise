package com.hackerrank.algo.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * The Ruler of HackerLand believes that every citizen of the country should
 * have access to a library. Unfortunately, HackerLand was hit by a tornado that
 * destroyed all of its libraries and obstructed its roads! As you are the
 * greatest programmer of HackerLand, the ruler wants your help to repair the
 * roads and build some new libraries efficiently.
 * 
 * HackerLand has cities numbered from to . The cities are connected by
 * bidirectional roads. A citizen has access to a library if:
 * 
 * Their city contains a library. They can travel by road from their city to a
 * city containing a library.
 * 
 * The cost of repairing any road is dollars, and the cost to build a library in
 * any city is dollars.
 * 
 * You are given queries, where each query consists of a map of HackerLand and
 * value of and .
 * 
 * For each query, find the minimum cost of making libraries accessible to all
 * the citizens and print it on a new line.
 * 
 * Input Format
 * 
 * The first line contains a single integer, , denoting the number of queries.
 * The subsequent lines describe each query in the following format:
 * 
 * The first line contains four space-separated integers describing the
 * respective values of (the number of cities), (the number of roads), (the cost
 * to build a library), and (the cost to repair a road). Each line of the
 * subsequent lines contains two space-separated integers, and , describing a
 * bidirectional road connecting cities and .
 * 
 * For each query, print an integer denoting the minimum cost of making
 * libraries accessible to all the citizens on a new line.
 * 
 * If the cost of a road is >= the cost of a library, just build a library at
 * each node.
 * 
 * Otherwise, use DFS to get the number of nodes (ct) in each connected
 * component. Put 1 library in each component, and the total per component cost
 * is simply ct-1 (a road to connect to each node in the compomnent) * cost of a
 * road + cost of one library.
 * 
 * @author surya
 *
 */
public class RoadsAndLibraries {

	private static ArrayList<Integer>[] adjCities;
	private static int connectedComponents;
	private static boolean[] isVisited;

	public static void main(String[] args) throws FileNotFoundException {
		long result = 0;
		// Scanner in = new Scanner(System.in);
		Scanner in = new Scanner(
				new File(
						"C:\\Developer\\GitRepo\\JavaAlgorithmsAndDSPractise\\src\\main\\java\\com\\hackerrank\\algo\\graph\\input.txt"));
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			int cities = in.nextInt(); // number of cities
			int roads = in.nextInt(); // number of roads
			long costLibrary = in.nextLong();
			long costRoad = in.nextLong();

			// simple - if road cost is > library cost
			if (costRoad >= costLibrary || roads == 0) {
				result = costLibrary * cities;
				System.out.println(result);
			} else {
				// try to get all connected components
				// use adjacency list
				adjCities = new ArrayList[cities + 1];

				// initialize the list
				for (int i = 0; i <= cities; i++)
					adjCities[i] = new ArrayList<>();

				// track if each node is visited or not
				isVisited = new boolean[cities + 1];

				// populate the adjacency list
				for (int i = 0; i < roads; i++) {
					int city1 = in.nextInt();
					int city2 = in.nextInt();
					adjCities[city1].add(city2);
					adjCities[city2].add(city1);
				}

				// check the cities
				// System.out.println(Arrays.toString(adjCities));

				for (int c = 1; c <= cities; c++) {
					if (!isVisited[c]) {
						dfs(c);
						connectedComponents++;
					}
				}

				// System.out.println(connectedComponents);
				result = costRoad * (cities - connectedComponents) + costLibrary * connectedComponents;
				System.out.println(result);
			}
			// reset
			connectedComponents = 0;
			isVisited = new boolean[10000];
			adjCities = (ArrayList<Integer>[]) new ArrayList[10000 + 1];
		}
		in.close();
	}

	private static void dfs(int city) {
		isVisited[city] = true;
		for (int c = 0; c < adjCities[city].size(); c++) {
			if (!isVisited[adjCities[city].get(c)]) {
				dfs(adjCities[city].get(c));
			}
		}
	}

}
