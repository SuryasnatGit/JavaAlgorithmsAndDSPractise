package com.ctci.sortnsearch;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CTCI - 10.2
 * 
 * Group Anagrams: Write a method to sort an array of strings so that all the
 * anagrams are next to each other.
 * 
 * @author ctsuser1
 */
public class GroupAnagrams {

    /**
     * Complexity - O(n log n)
     */
    private class AnagramComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return sortChars(o1).compareTo(sortChars(o2));
        }
    }

    private String sortChars(String input) {
        char[] chArr = input.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }

    public void groupAnagrams_comparator(String[] inputArr) {
        Arrays.sort(inputArr, new AnagramComparator());
    }

    /**
     * this is a modification of bucket sort.
     * 
     * @param inputArr
     */
    public void groupAnagrams_hashing(String[] inputArr) {
        Map<String, List<String>> map = new HashMap<>();
        
        for(String s : inputArr) {
            String key = sortChars(s);
            List<String> list = null;
            if (map.containsKey(key)) {
                list = map.get(key);
            }
            else {
                list = new ArrayList<>();
            }
            list.add(s);
            map.put(key, list);
        }

        // convert the hashmap to array
        int index = 0;
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            for (String t : list) {
                inputArr[index] = t;
                index++;
            }
        }
    }

    public static void main(String[] args) {
        String[] array = { "apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee" };
        System.out.println(Arrays.toString(array));
        GroupAnagrams ga = new GroupAnagrams();
        // ga.groupAnagrams_comparator(array);
        // System.out.println(Arrays.toString(array));
        ga.groupAnagrams_hashing(array);
        System.out.println(Arrays.toString(array));
    }

}
