

package com.ctci.sortnsearch;


/**
 * Sparse Search: Given a sorted array of strings that is interspersed with empty strings, write a method to find the
 * location of a given string. EXAMPLE Input: ball,{"at", "", "", "", "ball", "" , "", "car", "", "", "dad"} Output: 4.
 * Complexity - O(n)
 */
public class SparseSearch {

    public static void main(String[] args) {
        String[] strs = new String[] { "at", "", "", "", "ball", "", "", "car", "", "", "dad" };
        SparseSearch ss = new SparseSearch();
        System.out.println(ss.search(strs, "ball"));
        System.out.println(ss.search(strs, "car"));
    }

    public int search(String[] strings, String str) {
        if (strings == null || str == null || str.isEmpty())
            return -1;

        return search(strings, str, 0, strings.length - 1);
    }

    private int search(String[] strings, String str, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) / 2;

        // if middle is empty find closest non empty string
        if (strings[mid].isEmpty()) {
            int left = mid - 1;
            int right = mid + 1;
            while (true) {
                if (left < start && right > end) { // if out of bounds
                    return -1;
                }
                else if (right <= end && !strings[right].isEmpty()) { // check right
                    mid = right;
                    break;
                }
                else if (left >= start && !strings[left].isEmpty()) { // check left
                    mid = left;
                    break;
                }
            }
        }

        // perform binary search
        if (str.equals(strings[mid])) { // found it
            return mid;
        }
        else if (strings[mid].compareTo(str) < 0) {// search in right
            return search(strings, str, mid + 1, end);
        }
        else {// search left
            return search(strings, str, start, mid - 1);
        }
    }

}
