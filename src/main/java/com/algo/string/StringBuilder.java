package com.algo.string;

public class StringBuilder {

	// buffer to hold the string
	private char[] buffer;
	// capacity of the buffer
	private int capacity;
	// total length of the string
	private int length;

	/**
	 * default constructor
	 */
	public StringBuilder() {
		buffer = new char[16];
	}

	/**
	 * constructor which accepts initial capacity l
	 * 
	 * @param l
	 */
	public StringBuilder(int l) {
		capacity = l;
		buffer = new char[capacity];
		length = 0;
	}

	public StringBuilder append(String str) {
		String tempStr = str;
		if (str == null)
			str = "null";
		// check if str length + initial length is > capacity and if so increase the buffer size and move contents from
		// existing buffer to new buffer
		if (length + tempStr.length() > capacity) {
			int max = Math.max(length + tempStr.length(), capacity * 2);
			char[] tempBuf = new char[max];
			for (int i = 0; i < length; i++) {
				tempBuf[i] = buffer[i];
			}
			capacity = max;
			buffer = tempBuf;
		}
		for (int i = 0; i < tempStr.length(); i++) {
			buffer[length + i] = tempStr.charAt(i);
		}
		length += str.length();
		return this;
	}

	public String toString() {
		return new String(buffer, 0, length);
	}

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(20);
		sb.append("hi how r u");
		System.out.println(sb.toString());
		sb.append("i am fine thank you");
		System.out.println(sb.toString());
		sb.append("no u r not");
		System.out.println(sb.toString());
	}

}
