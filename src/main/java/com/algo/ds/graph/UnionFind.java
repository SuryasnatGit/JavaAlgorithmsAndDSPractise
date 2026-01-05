package com.algo.ds.graph;

public class UnionFind {

    private int[] parent; // parent[i] is parent of i
    private int count; // number of components
    // for weighted union find
    private int[] rank;
    // for weighted union by height
    private int[] height;

    // O(n)
    public UnionFind(int size) {
        this.count = size;
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = i;
            height[i] = 0;
        }
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(9);
    }

    public int count() {
        return count;
    }

    public boolean isConnected(int v, int w) {
        return find(v) == find(w);
    }

    // log(n)
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void validate(int p) {
        if (p < 0 || p >= count) {
            throw new IllegalArgumentException();
        }
    }

    public int findWithPathCompression(int p) {
        validate(p);
        int root = p;
        while (root != parent[root]) { // find root first
            root = parent[root];
        }
        while (p != root) { // compress path every time
            int newP = parent[p];
            parent[p] = root;
            p = newP;
        }
        return root;
    }

    // log(n)
    public void union(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);
        if (parentP == parentQ) {
            return;
        }
        parent[parentP] = parentQ;
        count--; // 1 less count of number of sets
    }

    // log(n)
    public void unionWithWeightage(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);
        if (parentP == parentQ) {
            return;
        }
        if (rank[parentP] < rank[parentQ]) {
            parent[parentP] = parentQ;
            rank[parentQ] += rank[parentP];
        } else {
            parent[parentQ] = parentP;
            rank[parentP] += rank[parentQ];
        }
        count--; // 1 less count of number of sets
    }

    // log(n)
    public void unionWithWeightedByHeight(int p, int q) {
        int parentP = find(p);
        int parentQ = find(q);
        if (parentP == parentQ) {
            return;
        }
        if (height[parentP] < height[parentQ]) {
            parent[parentP] = parentQ;
        } else if (height[parentP] > height[parentQ]) {
            parent[parentQ] = parentP;
        } else {
            parent[parentQ] = parentP;
            height[parentP]++;
        }
        count--;
    }
}
