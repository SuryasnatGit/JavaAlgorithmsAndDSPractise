package com.companyprep.amazon;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the
 * list of banned words. It is guaranteed there is at least one word that isn't banned, and that the
 * answer is unique.
 * 
 * Words in the list of banned words are given in lowercase, and free of punctuation. Words in the
 * paragraph are not case sensitive. The answer is in lowercase.
 * 
 * Example:
 * 
 * Input:
 * 
 * paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 * 
 * banned = ["hit"]
 * 
 * Output: "ball"
 * 
 * Explanation:
 * 
 * "hit" occurs 3 times, but it is a banned word. "ball" occurs twice (and no other word does), so
 * it is the most frequent non-banned word in the paragraph. Note that words in the paragraph are
 * not case sensitive, that punctuation is ignored (even if adjacent to words, such as "ball,"), and
 * that "hit" isn't the answer even though it occurs more because it is banned.
 * 
 * 
 * Note:
 * 
 * 1 <= paragraph.length <= 1000. <br/>
 * 0 <= banned.length <= 100. <br/>
 * 1 <= banned[i].length <= 10. <br/>
 * The answer is unique, and written in lowercase (even if its occurrences in paragraph may have
 * uppercase symbols, and even if it is a proper noun.) <br/>
 * paragraph only consists of letters, spaces, or the punctuation symbols !?',;. <br/>
 * There are no hyphens or hyphenated words. <br/>
 * Words only consist of letters, never apostrophes or other punctuation symbols.
 * 
 * 
 * @author M_402201
 *
 */
public class MostCommonWord {

	/**
	 * Time Complexity: O(P + B), where P is the size of paragraph and B is the size of banned.
	 * 
	 * @param paragraph
	 * @param banned
	 * @return
	 */
	public String mostCommonWord(String paragraph, String[] banned) {

		Set<String> bannedWordSet = new HashSet<String>();
		for (String bannedWord : banned) {
			bannedWordSet.add(bannedWord);
		}

		String[] words = paragraph.split(" ");
		Map<String, Integer> wordCount = new HashMap<String, Integer>();
		for (String word : words) {
			word = removePunctuation(word).toLowerCase();
			if (!bannedWordSet.contains(word)) {
				int count = 1;
				if (wordCount.containsKey(word)) {
					count = wordCount.get(word) + 1;
				}
				wordCount.put(word, count);
			}
		}

		int maxCount = Integer.MIN_VALUE;
		String result = null;
		for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
			int count = entry.getValue();
			if (maxCount < count) {
				maxCount = count;
				result = entry.getKey();
			}
		}

		return result;
	}

	private String removePunctuation(String input) {
		// crude way
//		for(char ch : input.toCharArray()) {
//			if(!Character.isLetter(ch))
//				return input.substring(0, input.length() - 1);
//		}
//		return input;

		// better way, reverse the word
		if (!Character.isLetter(input.charAt(input.length() - 1)))
			return input.substring(0, input.length() - 1);

		return input;

	}

	public static void main(String[] args) {
		MostCommonWord mcw = new MostCommonWord();
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = new String[] { "hit" };
		System.out.println(mcw.mostCommonWord(paragraph, banned));
	}

}
