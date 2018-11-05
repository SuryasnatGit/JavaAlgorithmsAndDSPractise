

package com.ctci.recuranddynprog;


public class Fibonnaci {

    /**
     * 1,2,3,4,5,6,7,8,9 O(2^n)
     * 
     * @param num
     * @return
     */
    public int recursive(int num) {
        if (num == 0)
            return 0;
        if (num == 1)
            return 1;

        return recursive(num - 1) + recursive(num - 2);
    }

    public static void main(String[] args) {
        Fibonnaci f = new Fibonnaci();
        System.out.println(f.recursive(100));

    }

}
