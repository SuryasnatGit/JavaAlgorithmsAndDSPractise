package com.algo.matrix;

public class SparseMatrix {

	private int n;
	private SparseVector[] rows;

	public SparseMatrix(int initialCapacity) {
		this.n = initialCapacity;
		rows = new SparseVector[n];
		for (int i = 0; i < n; i++)
			rows[i] = new SparseVector(n);
	}

	public void put(int i, int j, int value) {
		if (i < 0 || i >= n || j < 0 || j >= n)
			throw new RuntimeException("index out of bounds");
		this.rows[i].put(j, value);
	}

	public int get(int i, int j) {
		if (i < 0 || i >= n || j < 0 || j >= n)
			throw new RuntimeException("index out of bounds");
		return this.rows[i].get(j);
	}

	public SparseVector times(SparseVector that) {
		if (n != that.getVectorSize())
			throw new IllegalArgumentException("dimension not matching");
		SparseVector res = new SparseVector(n);
		for (int i = 0; i < n; i++)
			res.put(i, this.rows[i].dotProduct(that));
		return res;
	}

	public SparseMatrix plus(SparseMatrix that) {
		if (n != that.n)
			throw new IllegalArgumentException("dimension not matching");
		SparseMatrix m = new SparseMatrix(n);
		for (int i = 0; i < n; i++)
			m.rows[i] = this.rows[i].plus(that.rows[i]);
		return m;
	}

}
