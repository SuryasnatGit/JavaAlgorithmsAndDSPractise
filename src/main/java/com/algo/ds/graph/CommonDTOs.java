package com.algo.ds.graph;

import java.util.ArrayList;
import java.util.List;

public class CommonDTOs {

    public static class UndirectedGraphNode {
        int name;
        List<UndirectedGraphNode> neighbors;

        public UndirectedGraphNode(int x) {
            name = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

        public int getName() {
            return name;
        }

        public List<UndirectedGraphNode> getNeighbors() {
            return neighbors;
        }
    }
}
