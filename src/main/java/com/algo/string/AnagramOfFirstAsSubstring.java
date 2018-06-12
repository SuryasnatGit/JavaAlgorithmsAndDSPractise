

package com.algo.string;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * http://www.careercup.com/question?id=5389078581215232
 * Given two strings tells if anagram of first is substring of another
 * Keep map of characters in array1 and keep checking if array2 has these characters.
 * main string : a b a c a b b and looking for a a b b c when 3rd a is encountered
 * we move index to second a and start from there.
 * 
 * Another idea is to keep a sorted linklist of string in comparison. Whenever a new character
 * is to be added remove last character from linklist and add this new one.
 */
public class AnagramOfFirstAsSubstring {

    /**
     * Method 1- brute force. <br/>
     * Get all substrings of str2(O(N^2)). for each substring of str2, check if its an anagram(O(M)). total complexity -
     * O(N^2 * M)
     * 
     * @param str1
     * @param str2
     * @return
     */
    public boolean isAnagramSubString_bruteForce(char str1[], char str2[]) {
        // find all substrings of str2. complexity - O(N^2)
        int l2 = str2.length;
        for (int i = 0; i < l2; i++) {
            for (int j = i + 1; j <= l2; j++) {
                String subStr = new String(str2).substring(i, j);
                // System.out.println("SubString ->" + subStr);
                if (isAnagram(str1, subStr.toCharArray()))
                    return true;
            }
        }
        return false;
    }

    /**
     * O(M) complexity. it is assumed that the characters are stored using 8 bit and there can be 256 possible
     * characters.
     * 
     * @param s1
     * @param s2
     * @return
     */
    private boolean isAnagram(char[] s1, char[] s2) {
        if (s1.length != s2.length)
            return false;

        int[] count1 = new int[256]; // ascii chars
        Arrays.fill(count1, 0);
        int[] count2 = new int[256]; // ascii chars
        Arrays.fill(count2, 0);

        for (int i = 0; i < s1.length && i < s2.length; i++) {
            count1[s1[i]]++;
            count2[s2[i]]++;
        }

        for (int i = 0; i < 256; i++) {
            if (count1[i] != count2[i])
                return false;
        }
        return true;
    }

    public boolean isAnagramSubString(char str1[], char str2[]) {
        int index = 0;
        int curLen = 0;
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        for (int i = 0; i < str1.length; i++) {
            incrementCount(str1[i], count);
        }
        Map<Character, Integer> currentCount = new HashMap<Character, Integer>();
        Map<Character, Integer> pos = new HashMap<Character, Integer>();
        while (index < str2.length) {
            if (containsAndUpdate(currentCount, count, str2[index], pos, index)) {
                index++;
                curLen++;
            } else {
                Integer p = pos.get(str2[index]);
                if (p != null) {
                    curLen = index - p;
                    index = p;
                } else {
                    index++;
                }
                currentCount.clear();
                pos.clear();
            }
            if (curLen == str1.length) {
                return true;
            }
        }
        return false;
    }

    private boolean containsAndUpdate(Map<Character, Integer> currentCount,
            Map<Character, Integer> count, Character ch,
            Map<Character, Integer> pos, int index) {
        if (count.containsKey(ch)) {
            if(currentCount.containsKey(ch)) {
                if (currentCount.get(ch) < count.get(ch)) {
                    if (currentCount.get(ch) == 1) {
                        pos.put(ch, index);
                    }
                    currentCount.put(ch, currentCount.get(ch) + 1);
                    return true;
                }
            }else{
                currentCount.put(ch, 1);
                pos.put(ch,index);
                return true;
            }
        }
        return false;
    }

    private void incrementCount(Character ch, Map<Character, Integer> count) {
        if (count.containsKey(ch)) {
            int c = count.get(ch);
            count.put(ch, c + 1);
        }
        else {
            count.put(ch, 1);
        }
    }
    
    /**
     * Using sorting: We can sort array of strings so that all anagrams come together. Then print all anagrams by
     * linearly traversing the sorted array. The time complexity of this solution is O(mnLogn) (We would be doing
     * O(nLogn) comparisons in sorting and a comparison would take O(m) time)
     * 
     * @param str1
     * @param str2
     * @return
     */
    public boolean isAnagramSubString_sorting(char str1[], char str2[]) {
        // find all substrings of str2. complexity - O(n^2) ( n is length of string)
        int len1 = str1.length;
        int len2 = str2.length;
        for (int i = 0; i < len2; i++) {
            for (int j = i + 1; j <= len2; j++) {
                // Step 1 - find substring of str2
                char subStr[] = new String(str2).substring(i, j).toCharArray();
                // If length of both strings is not same, then they cannot be anagram
                if (len1 != subStr.length)
                    continue;

                // Step 2 -Sort both the strings. complexity - best case is O(nlogn) . worst case is O(n^2) where n is
                // length of string
                Arrays.sort(str1);
                Arrays.sort(subStr);

                // System.out.println("str1 :" + new String(str1));
                // System.out.println("subStr :" + new String(subStr));

                // Step 3 - compare sorted strings. Complexity - O(n)
                if (new String(str1).equals(new String(subStr)))
                    return true;
            }
        }
        return false;
    }

    public static void main(String args[]){
        char str1[] = "aaabccde".toCharArray();
        char str2[] = "tbcdaacaaecbd".toCharArray();
        AnagramOfFirstAsSubstring ana = new AnagramOfFirstAsSubstring();
        System.out.println(ana.isAnagramSubString_bruteForce(str1, str2));
        System.out.println(ana.isAnagramSubString(str1, str2));
        System.out.println(ana.isAnagramSubString_sorting(str1, str2));
    }
}
