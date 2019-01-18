

package com.leetcode;


/**
 * Reverse digits of an integer. Example1: x = 123, return 321 Example2: x = -123, return -321 .<br/>
 * Have you thought about this? Here are some good questions to ask before coding. Bonus points for you if you have
 * already thought through this! If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of
 * 1000000003 overflows. How should you handle such cases? For the purpose of this problem, assume that your function
 * returns 0 when the reversed integer overflows. to protect your code from overflow, when met multiply operation, use
 * divide operation to check first.
 * 
 * @author ctsuser1
 */
public class ReverseInteger {

    public int reverse(int num) {
        int res = 0;
        boolean sign = num > 0;
        int x = Math.abs(num);
        while (x > 0) {
            // to prevent overflow, use divide instead of multiple operation to check first
            if (res > Integer.MAX_VALUE / 10)
                return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return sign ? res : -res;
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(345345345));

    }

}
