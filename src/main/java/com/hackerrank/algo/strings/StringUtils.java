package com.hackerrank.algo.strings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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

	/**
	 * Since we are limited to the alphabet which we know to be constant we can come up with a better solution We can
	 * iterate through every character pair which is (26 * 25) / 2 = 325 pairs for each of those iterations we will run
	 * through the string checking if it fits the pattern and return the largest.<br/>
	 * Time Complexity: O(n) <br/>
	 * Space Complexity: O(1)
	 * 
	 * @param input
	 */
	private void twoCharacters(String input) {
		int maxPattern = 0; // keep a running max
		if (input.length() == 0) {
			System.out.println(maxPattern);
			System.exit(0);
		}

		// An unlabeled break statement terminates the innermost switch, for, while, or do-while statement, but a
		// labeled break terminates an outer statement. The following program, BreakWithLabelDemo, is similar to the
		// previous program, but uses nested for loops to search for a value in a two-dimensional array. When the value
		// is found, a labeled break terminates the outer for loop (labeled "search"):

		for (int i = 0; i < 26; i++) {
			nextLetter: // hmm interesting. java label :-)
			for (int j = i + 1; j < 26; j++) {
				char one = (char) ('a' + i);
				char two = (char) ('a' + j);
				char lastSeen = '\u0000'; // null character
				int patternSize = 0;
				for (char letter : input.toCharArray()) {
					if (letter == one || letter == two) {
						if (letter == lastSeen) { // if duplicate found
							continue nextLetter;
						}
						// if not a duplicate
						patternSize++;
						lastSeen = letter;
					}
				} // end of loop for string input
				maxPattern = (patternSize > maxPattern) ? patternSize : maxPattern;
			}
		}
		System.out.println("maxPattern :" + maxPattern);
	}

	/**
	 * You have two strings, and . Find a string, , such that:
	 * 
	 * can be expressed as where is a non-empty substring of and is a non-empty substring of . is a palindromic string.
	 * The length of is as long as possible. For each of the pairs of strings ( and ) received as input, find and print
	 * string on a new line. If you're able to form more than one valid string , print whichever one comes first
	 * alphabetically. If there is no valid answer, print instead.<br/>
	 * https://www.hackerrank.com/challenges/challenging-palindromes/problem
	 * 
	 * <br/>
	 * brute force solution. but this fails for very long strings.. WIP
	 * 
	 * @param a
	 * @param b
	 */
	private void challengingPalindromes(String a, String b) {
		int la = a.length();
		List<String> ssa = new ArrayList<>(); // to contain all substrings of a
		int lb = b.length();
		List<String> ssb = new ArrayList<>(); // to contain all substrings of b
		// find each substring of a. O(A^2)
		int index = 0;
		for (int i = 0; i < la; i++) {
			for (int j = i + 1; j <= la; j++) {
				ssa.add(index++, a.substring(i, j));
			}
		}
		index = 0;
		// System.out.println(ssa);
		// find each substring of b. O(B^2)
		for (int i = 0; i < lb; i++) {
			for (int j = i + 1; j <= lb; j++) {
				ssb.add(index++, b.substring(i, j));
			}
		}
		// System.out.println(ssb);
		// combine both lists to find the largest palindrome string
		int maxLength = 0;
		List<String> res = new ArrayList<>();
		for (String sa : ssa) {
			for (String sb : ssb) {
				String s = sa + sb;
				// System.out.println(s);
				if (isPalindrome(s) && s.length() >= maxLength) {
					maxLength = s.length();
					res.add(s);
				}
			}
		}
		// System.out.println(res);
		res.sort(new Comparator<String>() {
			// reverse comparison to keep the longest palindrome string in the first element in the list
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() > o2.length())
					return -1;
				else if (o1.length() < o2.length())
					return 1;
				else
					return o1.compareTo(o2);
			}
		});
		// System.out.println(res);
		System.out.println((!res.isEmpty() && res.get(0) != null) ? res.get(0) : -1);
	}

	private boolean isPalindrome(String s) {
		int l = s.length();
		// empty string considered as palindrome
		if (l == 0)
			return true;

		return isPalindromeRec(s, 0, l - 1);
	}

	private boolean isPalindromeRec(String s, int start, int end) {
		// if there is only 1 char
		if (start == end)
			return true;

		// if first and last char do not match
		if (s.charAt(start) != s.charAt(end))
			return false;

		// if there are more then 2 characters check if middle substring is also palindrome or not.
		if (start < end + 1)
			return isPalindromeRec(s, start + 1, end - 1);

		return true;
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
		// su.marsExploration("SOSSPSSQSSOR");
		// su.marsExploration("SOSSOT");
		// String s1 =
		// "ezfnjymgqtjnmstbadgdsrxvntnacwljnkgchtjeaoivfcindgxipmrjuqmmcpntpotplodjhijxqpogjmzipygacfdjgmewechuebxvcbnakszzcxkozxwavzgmesrvysonomhvufezislfntgncspthcpneyminpbjildobozfirvcgdratdpmmpkujcywvtzkdytzyfejbytsobvudvutfueveevgrqnxjiwpkrvllsjxmqhotlnpgjxkjnobxfqodlyiqsisdeuwqmntxouzdtisgutdafostmwticvncjwldpknuodmfksusaqpsoosgpiveyxipfklmhypdxpdncpgaswpycoxsuxasqduojpblctcyvyxldcgzevedvxiwinfppkjbtifuuapickknwxxjmjmtxlpfalxdgepmekaxijuphqfafrnezyldokwcnzenhpibktlfuxjfmeqajmvopbhuslnnnlmkmoteceiwbytjhhxqnkuazevswrkaofggfrnapciuoexqogscugzspwuvzkyrdfkhixcaqctfwadewpqksxxvqiigvjjpagvqikuojlwhfyztu";
		String s2 = "beabeefeab";
		String s3 = "a";
		// su.twoCharacters(s1);
		// su.twoCharacters(s2);
		// su.twoCharacters(s3);
		// su.challengingPalindromes("bac", "bac");
		// su.challengingPalindromes("abc", "def");
		// su.challengingPalindromes("jdfh", "fds");
		su.challengingPalindromes(
				"piiylotxanmkcljvyycmrtscndzivghxaigwxskrksqjokvncectsfxpgyorkufsaaciqgncvxtghwtpbnfskrmpzcymxugwjdixjrijytxlcasqpdljzlnxvpsbjqekrunuehmhngyumgjhboeobqnlifeskivabbaeputxuurwpowhfusacejxomosqbomleugdhzlvtzvjkzpfzyczivpioarkaxawrpxyzosglxxlpwnwnlzfjdldrffudmywwpjtlkxmoirwvolekxpmyhdyjaeqwknfpapuegircdowkuseybnbrcwscmlugzfkovxysypzekeedcemnkihrylizmakzwcdhqdxfkrvsqfhcliubnuwcjkdgpoynppagpzhmmovfuradzempdwjfvvtgmsalswwbvjahwerctoezqyyzosxhijgkzkspppvvljvedhurqyxrlhtezgphnbpuhgkmrclivkhnztoxsejnoepwpajuqrwzxnowqxqpjbxzsybtyafrqtleckzuxdlwouwijcghhgnsowijqphemojmzaieorvnbnswiuuyytmutivrdupcguvsgngmqwwskxgldlvdrmmaqhczctldsryaygnndfgjgnualnzrulirvgrhhpsozmstvdjcsmaachwigjscnapsumxpzlllvjjfoamemxuutilacvkeccfezywbftnbwyjtybgrjtqckytlhdzzuwqkcgesmfucyqpjlqxsjrjsnfuwbdcdqsajdimlrcvtjcxoettgxxjjbcnwunuuyeruotkwxkxctoveuneccenuevotcxkxwktoureyuunuwncbjjxxgtteoxcjtvcrlmidjasqdcdbwufnsjrjsxqljpqycufmsegcityhewgmvcakcxunuxogdvurumqogwljlfhysyjvvefpkbyibzeqfjhzymhdkdzueqdhnyvqtunonrxjnfbrukwlfitfmovpqn",
				"rohznpyfcozugjybluvniqukmswcwsriwfqnhotbayaeqvyphrkwutqicjowqmqneitncvuazznjojvltijkfxgmuuxqaypftsrhsifdbppevhqedzpwomzrsdejwfsuuxekqwuzzdhltykcqtjrgbytjywbntfbwyzefccekvcalituuxmemaofjjvlllzpxmuspancsjgiwhcaamscjdvtsmzosphhrgvrilurznlaungjgfdnngyayrsdltczchqammrdvldlgxkswwqmgngsvugcpudrvitumtyyuuiwsnbnvroeiazmjomehpqjiwosnghhgcjiwuowldxuzkceltqrfaytbyszxbjpqxqwonxzwrqujapwpeonjesxotznhkvilcrmkghupbnhpgzethlrxyqruhdevjlvvpppskzkgjihxsozyyqzeotcrewhajvbwwslasmgtvvfjwdpmezdarufvommhzpgappnyopgdkjcwunbuilchfqsvrkfxdqhdcwzkamzilyrhiknmecdeekezpysyxvokfzgulmcswcrbnbyesukwodcrigeupapfnkwqeajydhympxkelovwriomxkltjpwwymduffrdldjfzlnwnwplxxlgsozyxprwaxakraoipvizcyzfpzkjvztvlzhdguelmobqsomoxjecasufhwopwruuxtupeabbaviksefilnqboeobhjgmuygnhmheunurkeqjbspvxnlzjldpqsaclxtyjirjxahdfhfrjsxyukjfmruwpjajjcvjbkxptgqcyxdcvaajmdhkbaaxqvzigybwniufucucbztnisvhqszvktilnagluptrwdgtcdzjjxcnwesqxffbxyuqmqvydzsruhxevqymjurmjnilnrkeepdsyysvbaeksozxfnzxewjrvcboxljnxjdxgfhtgrdxohnjtiynspkwgtn");
	}

}
