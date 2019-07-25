

package com.hackerrank;


import java.util.HashMap;
import java.util.Map;


public class ArrayProblems {

    /**
     * https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem
     * 
     * @param c
     * @return
     */
    public int jumpingOnClouds(int[] c) {
        int count = -1;
        for (int i = 0; i < c.length; i++, count++) {
            if (i < (c.length - 2) && c[i + 2] == 0)
                i++;
        }
        return count;
    }

    /**
     * https://www.hackerrank.com/challenges/equality-in-a-array/problem
     * 
     * @param input
     * @return
     */
    public int equalizeTheArray(int[] input) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int i : input) {
            if (map.containsKey(i)) {
                int val = map.get(i) + 1;
                map.put(i, val);
                if (max < val)
                    max = val;
            }
            else {
                map.put(i, 1);
            }
        }
        System.out.println(map);
        return input.length - max;
    }

    public static void main(String[] args) {
        ArrayProblems ap = new ArrayProblems();
        int[] input = new int[] { 1, 2, 3, 3, 3 };
        System.out.println(ap.equalizeTheArray(input));
    }

}
