

package com.algo.string.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algo.ds.trie.Trie;


/**
 * https://leetcode.com/problems/anagrams/. <br/>
 * Given an array of words, print all anagrams together. For example, if the given array is {“cat”, “dog”, “tac”, “god”,
 * “act”}, then output may be “cat tac act dog god”.
 */
public class GroupAnagramsTogether {

    /**
     * A simple method is to create a Hash Table. Calculate the hash value of each word in such a way that all anagrams
     * have the same hash value. Populate the Hash Table with these hash values. Finally, print those words together
     * with same hash values. A simple hashing mechanism can be modulo sum of all characters. With modulo sum, two
     * non-anagram words may have same hash value. This can be handled by matching individual characters.
     * 
     * @param strs
     * @return
     */
    public Collection<List<String>> groupAnagrams_hashing(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();
        for(String s : strs){
            int hash = ascii(s);
            if (map.containsKey(hash)) {
                map.get(hash).add(s);
            }
            else {
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(hash, l);
            }
        }
        System.out.println(map);
        System.out.println(map.values());
        return map.values();
    }

    private int ascii(String str) {
        int m = 10;
        int i, sum;
        for (i = 0, sum = 0; i < str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum % m;
    }

    /**
     * If there are m strings and each string is of n characters, then time complexity is O(m * n log n) in best case.
     * Space complexity - O(m)
     * 
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams_usingHashMap(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<List<String>>();
        
        int listIndex = 0;
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> anagramGroup = new HashMap<>();
        
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (anagramGroup.containsKey(sorted)) {
                int index = anagramGroup.get(sorted);
                List<String> listResult = result.get(index);    
                listResult.add(str);
            } else {
                List<String> resultList = new ArrayList<>();
                resultList.add(str);
                result.add(listIndex, resultList);
                anagramGroup.put(sorted, listIndex);
                listIndex++;
            }
        }
        return result;
    }

    private Trie trie = new Trie();

    /**
     * Trie data structure can be used for a more efficient solution. Insert the sorted order of each word in the trie.
     * Since all the anagrams will end at the same leaf node. We can start a linked list at the leaf nodes where each
     * node represents the index of the original array of words. Finally, traverse the Trie. While traversing the Trie,
     * traverse each linked list one line at a time. Following are the detailed steps. 1) Create an empty Trie 2) One by
     * one take all words of input sequence. Do following for each word …a) Copy the word to a buffer. …b) Sort the
     * buffer …c) Insert the sorted buffer and index of this word to Trie. Each leaf node of Trie is head of a Index
     * list. The Index list stores index of words in original sequence. If sorted buffe is already present, we insert
     * index of this word to the index list. 3) Traverse Trie. While traversing, if you reach a leaf node, traverse the
     * index list. And print all words using the index obtained from Index list.
     * 
     * @param args
     */
    public void groupAnagrams_trie(String[] args) {
        for (int i = 0; i < args.length; i++) {
            char[] buffer = args[i].toCharArray();
            Arrays.sort(buffer);
            trie.insert(new String(buffer));
        }

    }

    public static void main(String args[]) {
        String str[] = { "cat", "dog", "tac", "god", "act" };
        GroupAnagramsTogether pat = new GroupAnagramsTogether();
        // System.out.println(pat.groupAnagrams_usingHashMap(str));
        pat.groupAnagrams_hashing(str);
    }
}
