package com.design.filesystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/articles/design-in-memory-file-system/
 *
 */
public class LeetCodeFileSystem {

	class Directory {
		HashMap<String, Directory> dirs = new HashMap<>();
		HashMap<String, String> files = new HashMap<>();
	}

	private Directory root;

	public LeetCodeFileSystem() {
		this.root = new Directory();
	}

	public List<String> ls(String path) {
		Directory t = root;
		List<String> files = new ArrayList<>();
		if (!path.equals("/")) {
			String[] d = path.split("/");
			for (int i = 1; i < d.length - 1; i++) {
				t = t.dirs.get(d[i]);
			}
			if (t.files.containsKey(d[d.length - 1])) {
				files.add(d[d.length - 1]);
				return files;
			} else {
				t = t.dirs.get(d[d.length - 1]);
			}
		}
		files.addAll(new ArrayList<>(t.dirs.keySet()));
		files.addAll(new ArrayList<>(t.files.keySet()));
		Collections.sort(files);
		return files;
	}

	public void mkdir(String path) {
		Directory t = root;
		String[] d = path.split("/");
		for (int i = 1; i < d.length; i++) {
			if (!t.dirs.containsKey(d[i]))
				t.dirs.put(d[i], new Directory());
			t = t.dirs.get(d[i]);
		}
	}

	public void addContentToFile(String filePath, String content) {
		Directory t = root;
		String[] d = filePath.split("/");
		for (int i = 1; i < d.length - 1; i++) {
			t = t.dirs.get(d[i]);
		}
		t.files.put(d[d.length - 1], t.files.getOrDefault(d[d.length - 1], "") + content);
	}

	public String readContentFromFile(String filePath) {
		Directory t = root;
		String[] d = filePath.split("/");
		for (int i = 1; i < d.length - 1; i++) {
			t = t.dirs.get(d[i]);
		}
		return t.files.get(d[d.length - 1]);
	}

	public static void main(String[] args) {
		LeetCodeFileSystem fs = new LeetCodeFileSystem();
		fs.ls("/cygdrive/c/Developer/GitRepo").forEach(s -> System.out.println(s));
	}

	/**
	 * Your FileSystem object will be instantiated and called as such: FileSystem obj = new FileSystem(); List<String>
	 * param_1 = obj.ls(path); obj.mkdir(path); obj.addContentToFile(filePath,content); String param_4 =
	 * obj.readContentFromFile(filePath);
	 */
}
