package com.hackerrank.algo.strings;


import java.util.HashSet;
import java.util.Set;


public class StringUtils {

	/**
	 * Steve has a string, , consisting of lowercase English alphabetic letters. In one operation, he can delete any
	 * pair of adjacent letters with same value. For example, string "aabcc" would become either "aab" or "bcc" after
	 * operation. Steve wants to reduce as much as possible. To do this, he will repeat the above operation as many
	 * times as it can be performed. Help Steve out by finding and printing 's non-reducible form!
	 * 
	 * @param s
	 */
	public void reducedString(String s) {
		System.out.println("input string -->" + s);
		StringBuilder sb = new StringBuilder(s);
		int i = 1; // initial counter
		while (i < sb.length()) {
			if (sb.charAt(i - 1) == sb.charAt(i)) {
				sb.delete(i - 1, i + 1); // alternate would be s = s.substring(0, i - 1) + s.substring(i + 1);
				if (i > 1) {
					i--;
				}
			} else {
				i++;
			}
		}
		if (sb.length() == 0) {
			System.out.println("empty string");
		} else {
			System.out.println("output string -->" + sb.toString());
		}
	}

	public void starcase(int n) {

		StringBuilder sb = new StringBuilder();
		String s = "";
		String sp = "";
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < n - i; j++) {
				sp += " ";
			}
			for (int j = 0; j < i; j++) {
				s = s + "#";

			}
			sb.append(sp).append(s).append("\n");
			s = "";
			sp = "";
		}
		System.out.println(sb.toString());
	}

	/**
	 * Alice wrote a sequence of words in CamelCase as a string of letters, , having the following properties: It is a
	 * concatenation of one or more words consisting of English letters. All letters in the first word are lowercase.
	 * For each of the subsequent words, the first letter is uppercase and rest of the letters are lowercase. Given ,
	 * print the number of words in on a new line.
	 * 
	 * @param input
	 */
	public void camelCase(String input) {
		int wsi = 0;// word start index
		int wei = 0;// word end index
		int count = 0; // count number of words in string
		for (int i = 0; i < input.length(); i++) {
			if (Character.isUpperCase(input.charAt(i))) {
				wei = i - 1;
				System.out.println("word :" + input.substring(wsi, wei + 1));
				count++;
				wsi = i;
			}
		}
		System.out.println("word :" + input.substring(wsi));
		if (!input.isEmpty())
			System.out.println(++count);
	}

    public void marsExploration(String input) {
        String sos = "SOS";
        int c = 0;
        for (int j = 0; j < input.length(); j++) {
            for (int i = 0; i < sos.length(); i++) {
                if (sos.charAt(i) != input.charAt(j))
                    c++;
            }
        }
        System.out.println(c);

    }

    private Set<Integer> weightedUniformString(String input) {
        Set<Integer> set = new HashSet<>();
        int weight = 0;
        char prev = ' ';
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr != prev)
                weight = 0;
            weight += curr - 'a' + 1;
            set.add(weight);
            prev = curr;
        }
        return set;
    }

    private int separateTheNumbers(String input) {
        if (input.charAt(0) == '0')
            return -1;

        for (int length = 1; length * 2 <= input.length(); length++) {
            int firstNumber = Integer.parseInt(input.substring(0, length));
            StringBuilder sb = new StringBuilder();
            int number = firstNumber;
            while (sb.length() < input.length()) {
                sb.append(number);
                number++;
            }
            if (sb.toString().equals(input))
                return firstNumber;
        }
        return -1;
    }

	// public void numberSeparator(String input){
	// for(char c : input.toCharArray()){
	// Integer.parseInt(c);
	// }
	// int length = input.length();
	// for(int i=0;i<length;i++){
	// input.substring(i, endIndex)
	// }
	// }
	//
	public static void main(String[] args) {
		StringUtils su = new StringUtils();
		// su.starcase(8);
		// su.reducedString("aaabccc");
		// su.reducedString("aaabcc");
		// su.reducedString("aaabbcc");
		// su.reducedString("aabbcc");
        // su.camelCase("theMainProblemIsMoney");
        // su.camelCase("the");
        // su.camelCase("");
        su.marsExploration("SOSSPSSQSSOR");
        su.marsExploration("SOSSOT");
	}

}
