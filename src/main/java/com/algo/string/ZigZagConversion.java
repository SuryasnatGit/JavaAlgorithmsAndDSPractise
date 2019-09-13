package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
   A P L S I I G
   Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a number of rows:
 * 
 * string convert(string s, int numRows); Example 1:
 * 
 * Input: s = "PAYPALISHIRING", numRows = 3 Output: "PAHNAPLSIIGYIR" Example 2:
 * 
 * Input: s = "PAYPALISHIRING", numRows = 4 Output: "PINALSIGYAHRPI" 
 * 
 * Explanation:
 * 
 * P     I    N
   A   L S  I G
   Y A   H R
   P     I
 * 
 * 
 * @author surya
 *
 */
public class ZigZagConversion {

	/**
	 * Approach 1: Sort by Row:
	 * 
	 * Intuition:
	 * 
	 * By iterating through the string from left to right, we can easily determine which row in the
	 * Zig-Zag pattern that a character belongs to.
	 * 
	 * Algorithm
	 * 
	 * We can use \text{min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) lists to represent the
	 * non-empty rows of the Zig-Zag Pattern.
	 * 
	 * Iterate through ss from left to right, appending each character to the appropriate row. The
	 * appropriate row can be tracked using two variables: the current row and the current direction.
	 * 
	 * The current direction changes only when we moved up to the topmost row or moved down to the
	 * bottommost row.
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(n)O(n), where n == \text{len}(s)n==len(s) Space Complexity: O(n)O(n)
	 * 
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
	}
	
	/**
	 * Approach 2: Visit by Row
	 * 
	 * Intuition:
	 * 
	 * Visit the characters in the same order as reading the Zig-Zag pattern line by line.
	 * 
	 * Algorithm:
	 * 
	 * Visit all characters in row 0 first, then row 1, then row 2, and so on...
	 * 
	 * For all whole numbers kk,
	 * 
	 * Characters in row 00 are located at indexes k \; (2 \cdot \text{numRows} - 2)k(2⋅numRows−2)
	 * Characters in row \text{numRows}-1numRows−1 are located at indexes k \; (2 \cdot \text{numRows} -
	 * 2) + \text{numRows} - 1k(2⋅numRows−2)+numRows−1 Characters in inner row ii are located at indexes
	 * k \; (2 \cdot \text{numRows}-2)+ik(2⋅numRows−2)+i and (k+1)(2 \cdot \text{numRows}-2)-
	 * i(k+1)(2⋅numRows−2)−i.
	 * 
	 * Complexity Analysis
	 * 
	 * Time Complexity: O(n)O(n), where n == \text{len}(s)n==len(s). Each index is visited once. Space
	 * Complexity: O(n)O(n). For the cpp implementation, O(1)O(1) if return string is not considered
	 * extra space.
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert1(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}
