package com.algo.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * References
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Example 1:
 * 
 * Input: "abcabcbb" Output: 3 Explanation: The answer is "abc", with the length of 3.
 * 
 * Example 2:
 * 
 * Input: "bbbbb" Output: 1 Explanation: The answer is "b", with the length of 1.
 * 
 * Example 3:
 * 
 * Input: "pwwkew" Output: 3 Explanation: The answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 */
public class LongestSubstringWithoutRepetingCharacter {

	/**
	 * Using sliding window approach:
	 * 
	 * By using HashSet as a sliding window, checking if a character in the current can be done in
	 * O(1)O(1).
	 * 
	 * A sliding window is an abstract concept commonly used in array/string problems. A window is a
	 * range of elements in the array/string which usually defined by the start and end indices, i.e.
	 * [i, j)[i,j) (left-closed, right-open). A sliding window is a window "slides" its two boundaries
	 * to the certain direction. For example, if we slide [i, j)[i,j) to the right by 11 element, then
	 * it becomes [i+1, j+1)[i+1,j+1) (left-closed, right-open).
	 * 
	 * Back to our problem. We use HashSet to store the characters in current window [i, j)[i,j) (j =
	 * ij=i initially). Then we slide the index jj to the right. If it is not in the HashSet, we slide
	 * jj further. Doing so until s[j] is already in the HashSet. At this point, we found the maximum
	 * size of substrings without duplicate characters start with index ii. If we do this for all ii, we
	 * get our answer.
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : O(2n) = O(n). In the worst case each character will be visited twice by i and
	 * j.
	 * 
	 * Space complexity : O(min(m, n)). Same as the previous approach. We need O(k) space for the
	 * sliding window, where k is the size of the Set. The size of the Set is upper bounded by the size
	 * of the string n and the size of the charset/alphabet m.
	 * 
	 * @param s
	 * @return
	 */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> uniqueSet = new HashSet<>();
        int maxSize = 0;
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            if(!uniqueSet.contains(s.charAt(i))) {
                uniqueSet.add(s.charAt(i));
                if(uniqueSet.size() > maxSize) {
                    maxSize = uniqueSet.size();
                }
            } else {
                while (s.charAt(start) != s.charAt(i)) {
                    uniqueSet.remove(s.charAt(start));
                    start++;
                }
                start++;
            }
        }
        return maxSize;
    }
    
	/**
	 * The above solution requires at most 2n steps. In fact, it could be optimized to require only n
	 * steps. Instead of using a set to tell if a character exists or not, we could define a mapping of
	 * the characters to its index. Then we can skip the characters immediately when we found a repeated
	 * character.
	 * 
	 * The reason is that if s[j]s[j] have a duplicate in the range [i, j)[i,j) with index j&#x27;j ′ ,
	 * we don't need to increase ii little by little. We can skip all the elements in the range [i,
	 * j&#x27;][i,j ′ ] and let ii to be j&#x27; + 1j ′ +1 directly.
	 * 
	 * Complexity Analysis
	 * 
	 * Time complexity : O(n). Index j will iterate n times.
	 * 
	 * Space complexity (HashMap) : O(min(m, n)). Same as the previous approach
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring_optimised(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>(); // current index of character
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

    public static void main(String args[]){
        LongestSubstringWithoutRepetingCharacter lsw = new LongestSubstringWithoutRepetingCharacter();
        System.out.println(lsw.lengthOfLongestSubstring("ABCDECAMNCZB"));
    }
}
