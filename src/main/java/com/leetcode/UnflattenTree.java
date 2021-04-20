package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Task In this challenge, your task is to write the method unflattenTree(List<Node> nodes). This function will perform
 * an unflattening transformation on a nodes List of Nodes objects which represents a directory structure or routing
 * path tree.
 * 
 * IMPORTANT: please note the definition of the Node class which is given as a comment in the Solution Setup tab (where
 * you'll be writing your solution). Please do not uncomment the code as the class is already compiled in a different
 * section which will not be visible to you.
 * 
 * Here's an example nodes List (represented in JSON format):
 * 
 * const nodes = [ { "id": "1", "path": "/home", }, { "id": "2", "path": "/users", }, { "id": "3", "path":
 * "/users/alice", }, { "id": "4", "path": "/users/bob", }, { "id": "5", "path": "/users/alice/posts", } ]; Your
 * function should transform this List into the following nested object (again, represented in JSON format):
 * 
 * { "path": "/", "children": [ { "id": "1", "path": "/home", "children": [] }, { "id": "2", "path": "/users",
 * "children": [ { "id": "3", "path": "/users/alice", "children": [ { "children": [], "id": "5", "path":
 * "/users/alice/posts" } ] }, { "id": "4", "path": "/users/bob", "children": [] } ] } ] } The nesting for this object
 * is based on the path property: each '/' character delimits a directory and alphabetical characters between slashes
 * represent directory names. Directories should be grouped in a "children" ArrayList per level and sorted by path value
 * ("/alice" should be before "/bob"). Preserve any existing keys in the tree (in this example, id).
 * 
 * Since there may be multiple nodes in the top level, please root the tree with a dummy node:
 * 
 * { path: "/", children: [...] } Additionally, should any paths involve creation of empty nodes, please create these
 * nodes for your result structure (similar to the mkdir -p command in a Unix environment).
 * 
 * For example, if nodes = [{id: 42, path: "/a/b"}], return
 * 
 * { "path": "/", "children": [ { "path": "/a", "children": [ { "id": 42, "path": "/a/b", "children": [] } ] } ] } The
 * "empty" directories simply have their path and children keys only.
 * 
 * Constraints:
 * 
 * 0 ≤ number of nodes in the result tree ≤ 100. nodes is guaranteed to be well-formed; each of its elements is an
 * object with a path key. nodes.get(i).getPath() is guaranteed to be well-formed; it will be a string consisting of
 * lowercase a-z characters delimited by slashes (/). The path value will always have a leading slash and no trailing
 * slash, will never contain consecutive slashes and represents a unique node path.
 * 
 * TODO : to complete
 * 
 * category : Hard
 *
 */
public class UnflattenTree {

	public static Node unflattenTree(List<Node> nodes) {
		Node parent = new Node("/");

		SortedMap<Integer, SortedSet<String>> map = new TreeMap<>();
		// preprocess
		for (Node node : nodes) {
			String path = node.getPath();
			String[] names = path.split("/");
			for (int i = 0; i < names.length; i++) {
				if (map.containsKey(i)) {
					map.get(i).add(names[i]);
				} else {
					SortedSet<String> set = new TreeSet<>();
					set.add(names[i]);
					map.put(i, set);
				}
			}
		}

		for (Map.Entry<Integer, SortedSet<String>> entry : map.entrySet()) {

		}
	}
}

class Node implements Comparable<Node> {

	private String id = null;
	private String path;
	private List<Node> children = new ArrayList<>();

	public Node(String path) {
		this.path = path;
	}

	public Node(String id, String path) {
		this(path);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}

	public void sort() {
		Collections.sort(this.children);
		for (Node c : this.children) {
			c.sort();
		}
	}

	public Node findChildByPath(String path) {
		for (Node child : this.children) {
			if (path.equals(child.getPath())) {
				return child;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "{" + (id == null || id == "" ? "" : "id='" + id + "\', ") + "path='" + path + '\'' + ", children="
				+ children + '}';
	}

	@Override
	public int compareTo(Node o) {
		return path.compareTo(o.getPath());
	}

}