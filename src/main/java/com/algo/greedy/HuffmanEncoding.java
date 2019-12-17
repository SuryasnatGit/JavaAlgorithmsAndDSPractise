package com.algo.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding-set-2/
 * 
 * https://www.techiedelight.com/huffman-coding/
 * 
 * 
 * Huffman Coding (also known as Huffman Encoding) is a algorithm for doing data compression and it forms the basic idea
 * behind file compression. This post talks about fixed length and variable length encoding, uniquely decodable codes,
 * prefix rules and construction of Huffman Tree.
 * 
 * Category : Hard
 */
class Node {
	int freq;
	char data;
	Node left;
	Node right;

	public Node(char ch, int fr) {
		this.data = ch;
		this.freq = fr;
		this.left = null;
		this.right = null;
	}

	public Node(char ch, int fr, Node left, Node right) {
		this.data = ch;
		this.freq = fr;
		this.left = left;
		this.right = right;
	}
}

public class HuffmanEncoding {

	public void encode(Node root, String input, Map<Character, String> huffmanCodes) {
		if (root == null)
			return;

		// if leaf node
		if (root.left == null && root.right == null) {
			huffmanCodes.put(root.data, input);
		}

		// recurse for left and right sub trees
		encode(root.left, input + "0", huffmanCodes);
		encode(root.right, input + "1", huffmanCodes);
	}

	public int decode(Node root, int index, StringBuilder sb) {
		if (root == null)
			return index;

		// found a leaf node
		if (root.left == null && root.right == null) {
			System.out.println(root.data);
			return index;
		}

		index++;

		// traverse sub trees
		if (sb.charAt(index) == '0') {
			index = decode(root.left, index, sb);
		} else {
			index = decode(root.right, index, sb);
		}

		return index;
	}

	public void huffmanCode(String input) {
		// create a map to capture the frequencies of each char
		Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
		for (char ch : input.toCharArray()) {
			if (!freqMap.containsKey(ch)) {
				freqMap.put(ch, 0);
			}
			freqMap.put(ch, freqMap.get(ch) + 1);
		}

		// create a PQ add the chars sorted by frequency(highest priority has less freqeuncy)
		PriorityQueue<Node> pq = new PriorityQueue<Node>((l, r) -> l.freq - r.freq);
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		// keep reading from the PQ until the size is > 2
		while (pq.size() != 1) {
			// poll top 2 elements from PQ containing least freqeuncy
			Node left = pq.poll();
			Node right = pq.poll();

			int sum = left.freq + right.freq;
			pq.add(new Node('\0', sum, left, right));
		}

		// root stores pointer to root of huffman tree
		Node root = pq.peek();

		// traverse huffman tree and store huffman code in a map
		Map<Character, String> huffmanCodes = new HashMap<Character, String>();
		encode(root, "", huffmanCodes);

		// print the huffman codes
		for (Map.Entry<Character, String> code : huffmanCodes.entrySet()) {
			System.out.println("char :" + code.getKey() + " Value:" + code.getValue());
		}

		// print encoded string
		StringBuilder sb = new StringBuilder();
		for (char ch : input.toCharArray()) {
			sb.append(huffmanCodes.get(ch));
		}
		System.out.println("Encoded string : " + sb.toString());

		// decode the encoded string
		System.out.println("Decoded string :");
		int index = -1;
		while (index < sb.length() - 2) {
			index = decode(root, index, sb);
		}
	}

	public static void main(String args[]) {
		HuffmanEncoding he = new HuffmanEncoding();
		he.huffmanCode("suryasnat");
	}

}
