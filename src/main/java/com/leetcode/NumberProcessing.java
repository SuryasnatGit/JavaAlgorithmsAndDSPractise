

package com.leetcode;


import java.util.HashMap;
import java.util.Map;


public class NumberProcessing {

    /**
     * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999
     * 
     * @return
     */
    public String integerToRoman(int num) {
        String[] symbol = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] value = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        StringBuilder sb = new StringBuilder();
        int k = 0;
        while (num >= 0 && k < value.length) {
            while (num >= value[k]) {
                num -= value[k];
                sb.append(symbol[k]);
            }
            k++;
        }
        return sb.toString();
    }

    public int romanToInteger(String s) {
        Map<String, Integer> roman = new HashMap<>();
        roman.put("M", 1000);
        roman.put("CM", 900);
        roman.put("D", 500);
        roman.put("CD", 400);
        roman.put("C", 100);
        roman.put("XC", 90);
        roman.put("L", 50);
        roman.put("XL", 40);
        roman.put("X", 10);
        roman.put("IX", 9);
        roman.put("V", 5);
        roman.put("IV", 4);
        roman.put("I", 1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            String key = s.substring(i, i + 1);
            if (i + 2 <= s.length() && roman.containsKey(s.substring(i, i + 2))) {
                key = s.substring(i, i + 2);
                i++;
            }
            res += roman.get(key);
        }

        return res;
    }

    public static void main(String[] args) {
        NumberProcessing np = new NumberProcessing();
        String s = np.integerToRoman(1959);
        System.out.println(s);
        System.out.println(np.romanToInteger(s));
    }

}
