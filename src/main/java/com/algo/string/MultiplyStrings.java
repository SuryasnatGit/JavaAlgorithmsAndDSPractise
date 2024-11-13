package com.algo.string;

/**
 * https://leetcode.com/discuss/questions/oj/multiply-strings.
 * 
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also
 * represented as a string.
 * 
 * Example 1:
 * 
 * Input: num1 = "2", num2 = "3" Output: "6" Example 2:
 * 
 * Input: num1 = "123", num2 = "456" Output: "56088" Note:
 * 
 * The length of both num1 and num2 is < 110. Both num1 and num2 contain only digits 0-9. Both num1 and num2 do not
 * contain any leading zero, except the number 0 itself. You must not use any built-in BigInteger library or convert the
 * inputs to integer directly.
 * 
 * Category : Medium
 * 
 */
public class MultiplyStrings {

	public String multipleNumbersAsString(int num1, int num2) {
		// reverse the string as multiplication starts from index 0
		String s1 = new java.lang.StringBuilder(Integer.toString(num1)).reverse().toString();
		String s2 = new java.lang.StringBuilder(Integer.toString(num2)).reverse().toString();

		int[] d = new int[s1.length() + s2.length()];
		for (int i = 0; i < s1.length(); i++) {
			for (int j = 0; j < s2.length(); j++) {
				d[i + j] += (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
			}
		}
		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		for (int i = 0; i < d.length; i++) {
			int modulo = d[i] % 10;
			int carry = d[i] / 10;
			if (i + 1 < d.length)
				d[i + 1] += carry;
			sb.insert(0, modulo);
		}

		// remove beginning 0
		if (sb.charAt(0) == '0' && sb.length() > 0)
			sb.deleteCharAt(0);

		return sb.toString();
	}

	public String multiplyLargeNumberAsStrings(String num1, String num2) {
		String output = multiply(num1, num2, 0, num1.length() - 1, 0, num2.length() - 1);
		return output;
	}

	private String multiply(String num1, String num2, int start1, int end1, int start2, int end2) {
		if (end1 - start1 == 0 || end2 - start2 == 0) {
			return simpleMultiply(num1.substring(start1, end1 + 1), num2.substring(start2, end2 + 1));
		}

		int mid1 = (start1 + end1) / 2;
		int mid2 = (start2 + end2) / 2;

		int count1 = end1 - mid1;
		int count2 = end2 - mid2;

		String v1 = multiply(num1, num2, start1, mid1, start2, mid2);
		String v2 = multiply(num1, num2, start1, mid1, mid2 + 1, end2);
		String v3 = multiply(num1, num2, mid1 + 1, end1, start2, mid2);
		String v4 = multiply(num1, num2, mid1 + 1, end1, mid2 + 1, end2);

		v1 = append0s(v1, count1 + count2);
		v2 = append0s(v2, count1);
		v3 = append0s(v3, count2);

		v1 = add(v1.toCharArray(), v2.toCharArray());
		v3 = add(v3.toCharArray(), v4.toCharArray());
		return add(v1.toCharArray(), v3.toCharArray());
	}

	private String simpleMultiply(String num1, String num2) {
		String smaller;
		String larger;
		if (num1.length() == 1) {
			smaller = num1;
			larger = num2;
		} else {
			smaller = num2;
			larger = num1;
		}
		int r2 = smaller.charAt(0) - '0';
		if (r2 == 0) {
			return "0";
		}
		int carry = 0;
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = larger.length() - 1; i >= 0; i--) {
			int r1 = larger.charAt(i) - '0';
			int r = r1 * r2 + carry;
			stringBuffer.append(r % 10);
			carry = r / 10;
		}
		if (carry != 0) {
			stringBuffer.append(carry);
		}
		return stringBuffer.reverse().toString();
	}

	private String append0s(String v1, int count) {
		StringBuffer buff = new StringBuffer(v1);
		for (int i = 0; i < count; i++) {
			buff.append("0");
		}
		return buff.toString();
	}

	private String add(char[] num1, char[] num2) {
		int index1 = num1.length - 1;
		int index2 = num2.length - 1;
		int carry = 0;
		StringBuffer buffer = new StringBuffer();
		while (index1 >= 0 && index2 >= 0) {
			int r1 = num1[index1] - '0';
			int r2 = num2[index2] - '0';
			int r = r1 + r2 + carry;
			buffer.append(r % 10);
			carry = r / 10;
			index1--;
			index2--;
		}
		while (index1 >= 0) {
			int r1 = num1[index1] - '0';
			int r = r1 + carry;
			buffer.append(r % 10);
			carry = r / 10;
			index1--;
		}
		while (index2 >= 0) {
			int r2 = num2[index2] - '0';
			int r = r2 + carry;
			buffer.append(r % 10);
			carry = r / 10;
			index2--;
		}
		if (carry != 0) {
			buffer.append(carry);
		}
		return buffer.reverse().toString();
	}

	/**
	 * easiest solution.
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];

				pos[p1] += sum / 10;
				pos[p2] = (sum) % 10;
			}
		}

		java.lang.StringBuilder sb = new java.lang.StringBuilder();
		for (int p : pos)
			if (!(sb.length() == 0 && p == 0))
				sb.append(p);
		return sb.length() == 0 ? "0" : sb.toString();
	}

	public static void main(String args[]) {
		MultiplyStrings ms = new MultiplyStrings();
		System.out.println(ms.multiplyLargeNumberAsStrings("6752716719037375654442652725945722915786612669126862029212",
				"2840271321219335147"));
		System.out.println(ms.multiply("23", "64"));
	}
}
