package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Category : Hard
 *
 */
public class IPAddressValidationProblems {

	/**
	 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
	 * 
	 * A valid IP address must be in the form of A.B.C.D, where A,B,C and D are numbers from 0-255. The numbers cannot
	 * be 0 prefixed unless they are 0.
	 * 
	 * Example:
	 * 
	 * Given “25525511135”,
	 * 
	 * return [“255.255.11.135”, “255.255.111.35”]. (Make sure the returned strings are sorted in order)
	 * 
	 * FB: valid IP in string format and return the uint32 format
	 */
	public List<String> restoreIPAddress(String s) {
		List<String> res = new ArrayList<String>();

		// base condition
		if (s == null || s.length() < 3 || s.length() > 12)
			return res;

		dfs(s, res, "", 0);
		return res;
	}

	private void dfs(String s, List<String> res, String prev, int count) {
		// base condition
		if (count == 3 && isValid(s)) {
			res.add(prev + s);
		}

		for (int end = 1; end <= 3 && end < s.length(); end++) {
			String subStr = s.substring(0, end);
			if (isValid(subStr)) {
				dfs(s.substring(end), res, prev + subStr + ".", count + 1);
			}
		}
	}

	private boolean isValid(String s) {
		if (s.charAt(0) == '0')
			return s.length() == 1;

		// parse the string and check the boundries
		int i = Integer.parseInt(s);
		return i >= 1 && i <= 255;
	}

	/**
	 * In this problem, your job to write a function to check whether a input string is a valid IPv4 address or IPv6
	 * address or neither.
	 * 
	 * IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, each
	 * ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
	 * 
	 * Besides, you need to keep in mind that leading zeros in the IPv4 is illegal. For example, the address
	 * 172.16.254.01 is illegal.
	 * 
	 * IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. The
	 * groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a legal
	 * one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the
	 * address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros
	 * and using upper cases).
	 * 
	 * However, we don't replace a consecutive group of zero value with a single empty group using two consecutive
	 * colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
	 * 
	 * Besides, you need to keep in mind that extra leading zeros in the IPv6 is also illegal. For example, the address
	 * 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is also illegal.
	 * 
	 * Note: You could assume there is no extra space in the test cases and there may some special characters in the
	 * input string.
	 * 
	 * Example 1: Input: "172.16.254.1"
	 * 
	 * Output: "IPv4"
	 * 
	 * Explanation: This is a valid IPv4 address, return "IPv4". Example 2: Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
	 * 
	 * Output: "IPv6"
	 * 
	 * Explanation: This is a valid IPv6 address, return "IPv6". Example 3: Input: "256.256.256.256"
	 * 
	 * Output: "Neither"
	 * 
	 * Explanation: This is neither a IPv4 address nor a IPv6 address.
	 * 
	 * @param args
	 */
	public String validIPAddress(String IP) {
		String[] v4 = IP.split("\\.");
		String[] v6 = IP.split(":");

		int v4Count = IP.length() - IP.replace(".", "").length();
		int v6Count = IP.length() - IP.replace(":", "").length();

		if (v4.length == 4 && v4Count == 3) { // Try V4. v4Count is to avoid 1.1.1.1.
			for (int i = 0; i < 4; i++) {
				String v = v4[i];
				if (!validDecSection(v)) {
					return "Neither";
				}
			}

			return "IPv4";
		} else if (v6.length == 8 && v6Count == 7) { // Try V6
			for (int i = 0; i < 8; i++) {
				String v = v6[i];

				if (!validHexSection(v)) {
					return "Neither";
				}

			}
			return "IPv6";
		}

		return "Neither";
	}

	private boolean validDecSection(String v) {
		if (v.length() > 3 || v.length() == 0) {
			return false;
		}

		if (v.charAt(0) == '0' && v.length() != 1) {
			return false;
		}

		if (v.charAt(0) == '-') {
			return false;
		}

		int val = 0;
		try {
			val = Integer.parseInt(v);
		} catch (NumberFormatException ex) {
			return false;
		}

		if (val > 255) {
			return false;
		}

		return true;
	}

	private boolean validHexSection(String str) {
		if (str.length() == 0 || str.length() > 4) {
			return false;
		}

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (!validHexChar(c)) {
				return false;
			}
		}

		return true;
	}

	private boolean validHexChar(char c) {
		return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
	}

	public static void main(String[] args) {
		IPAddressValidationProblems ip = new IPAddressValidationProblems();
		List<String> res = ip.restoreIPAddress("2552511135");
		res.forEach(a -> System.out.println(a));

		System.out.println(ip.validIPAddress("172.16.254.1"));
		System.out.println(ip.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
		System.out.println(ip.validIPAddress("256.256.256.256"));
		System.out.println(ip.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
		System.out.println(ip.validIPAddress("1e1.4.5.6"));
		System.out.println(ip.validIPAddress("12..33.4"));
	}
}
