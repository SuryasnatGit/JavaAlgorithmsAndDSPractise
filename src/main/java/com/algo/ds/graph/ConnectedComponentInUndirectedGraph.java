package com.algo.ds.graph;

import com.algo.ds.graph.CommonDTOs.UndirectedGraphNode;

import java.util.*;

public class ConnectedComponentInUndirectedGraph {

    public static void main(String[] args) {
        ConnectedComponentInUndirectedGraph con = new ConnectedComponentInUndirectedGraph();
        List<UndirectedGraphNode> list = new ArrayList<>();
        list.add(new UndirectedGraphNode(1));
        list.add(new UndirectedGraphNode(2));
        System.out.println(con.connectedComponents(list));
    }

    public List<List<Integer>> connectedComponents(List<UndirectedGraphNode> nodes) {
        Set<Integer> nodeSet = new HashSet<>();
        for (UndirectedGraphNode node : nodes) {
            nodeSet.add(node.name);
            for (UndirectedGraphNode neighbor : node.neighbors) {
                nodeSet.add(neighbor.name);
            }
        }

        UnionFind uf = new UnionFind(nodeSet);

        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode neighbor : node.neighbors) {
                int parentNode = uf.find(node.name);
                int parentNeighbor = uf.find(neighbor.name);
                if (parentNode != parentNeighbor) {
                    uf.union(parentNode, parentNeighbor);
                }
            }
        }

        return print(nodeSet, uf, nodes.size());
    }

    private List<List<Integer>> print(Set<Integer> nodeSet, UnionFind uf, int size) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (Integer node : nodeSet) {
            int parentNode = uf.find(node);
            if (!map.containsKey(parentNode)) {
                map.put(parentNode, new ArrayList<>());
            }
            List<Integer> list = map.get(parentNode);
            list.add(node);
            map.put(parentNode, list);
        }
        for (List<Integer> values : map.values()) {
            Collections.sort(values);
            result.add(values);
        }
        return result;
    }

    class UnionFind {
        private Map<Integer, Integer> parent = new HashMap<>();

        public UnionFind(Set<Integer> input) {
            for (Integer vertex : input) {
                parent.put(vertex, vertex);
            }
        }

        public void union(int v, int w) {
            int parentV = find(v);
            int parentW = find(w);
            if (parentV != parentW) {
                parent.put(parentV, parentW);
            }
        }

        public int find(int u) {
            int parentU = parent.get(u);
            while (parentU != parent.get(parentU)) {
                parentU = parent.get(parentU);
            }
            return parentU;
        }
    }
}
