

package com.algo.string;


import java.util.HashMap;
import java.util.Map;


/**
 * Date 04/15/2016 Given a dictionary of words and a word, tell if there is unique abbrreviation of this word in the
 * dictionary. Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given
 * abbreviation. A string such as â€œwordâ€? contains only the following valid abbreviations: ["word", "1ord", "w1rd",
 * "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"] Notice that only the
 * above abbreviations are valid abbreviations of the string â€œwordâ€?. Any other string is not a valid abbreviation of
 * â€œwordâ€?. Note: Assume s contains only lowercase letters and abbr contains only lowercase letters and digits. Example
 * 1: Given s = "internationalization", abbr = "i12iz4n": Return true. Example 2: Given s = "apple", abbr = "a2e":
 * Return false.
 * 
 * @author Tushar Roy
 */
public class ValidWordAbbreviation {

    private final Map<String, Map<Integer, Integer>> map = new HashMap<>();

    public ValidWordAbbreviation(String[] dictionary) {
        for (String str : dictionary) {
            String key = "";
            int len = 0;
            if (str.length() > 0) {
                key = str.charAt(0) + "" + str.charAt(str.length() - 1);
                len = str.length() - 2;
            }
            Map<Integer, Integer> innerMap = map.get(key);
            if (innerMap == null) {
                innerMap = new HashMap<>();
                map.put(key, innerMap);
            }
            Integer count = innerMap.get(len);
            if (count == null) {
                count = 0;
            }
            innerMap.put(len, count + 1);
        }
    }

    /**
     * wrong logic.. see other method below.
     * 
     * @param word
     * @return
     */
    public boolean isUnique(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        String key = "";
        int len = 0;
        if (word.length() > 0) {
            key = word.charAt(0) + "" + word.charAt(word.length() - 1);
            len = word.length() - 2;
        }
        Map<Integer, Integer> set = map.get(key);
        if (set == null) {
            return true;
        }
        Integer count = set.get(len);
        return count == null || count == 1;
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (abbr.charAt(j) == '0')
                return false;
            if (Character.isDigit(abbr.charAt(j))) {
                int end = j;
                while (end < abbr.length() && Character.isDigit(abbr.charAt(end))) {
                    end++;
                }
                int count = Integer.parseInt(abbr.substring(j, end));
                i += count;
                j = end;
            }
            else {
                if (word.charAt(i++) != abbr.charAt(j++))
                    return false;
            }
        }
        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        String[] strArr = { "internationalization" };
        ValidWordAbbreviation abbr = new ValidWordAbbreviation(strArr);
        System.out.println(abbr.isUnique("i18n"));
        System.out.println(abbr.isUnique("i17n"));
        System.out.println(abbr.validWordAbbreviation("internationalization", "i18n"));
        System.out.println(abbr.validWordAbbreviation("internationalization", "i17n"));
        System.out.println(abbr.validWordAbbreviation("internatioan", "i10n"));
    }
}
