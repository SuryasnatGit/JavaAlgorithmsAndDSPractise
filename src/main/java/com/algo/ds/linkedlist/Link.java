package com.algo.ds.linkedlist;

public class Link {

	private int data;
	public Link next;

	public Link() {
		// TODO Auto-generated constructor stub
	}

	public Link(int d) {
		data = d;
	}

	public int getKey() {
		return data;
	}

	@Override
	public String toString() {
		return "Link [data=" + data + ", next=" + next + "]";
	}

}
