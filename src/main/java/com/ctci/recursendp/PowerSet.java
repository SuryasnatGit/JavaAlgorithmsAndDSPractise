

package com.ctci.recursendp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Power Set: Write a method to return all subsets of a set.
 * 
 * @author ctsuser1
 */
public class PowerSet {

    /**
     * Assuming that we're going to be returning a list of subsets, then our best case time is actually the total number of
     * elements across all of those subsets. There are 2nsubsets and each of the n elements will be contained in half of the
     * subsets (which 2n-1subsets). Therefore, the total number of elements across all of those subsets is n * 2^n-1. We
     * will not be able to beat 0 (n * 2^n) in space or time complexity.
     * 
     * @return
     */
    public List<List<Object>> getSubSets_recursion(List<Object> input, int index) {
        List<List<Object>> result;
        if (input.size() == index) { // base case. add empty set
            result = new ArrayList<>();
            result.add(new ArrayList<>());
        }
        else {
            result = getSubSets_recursion(input, index + 1);
            Object item = input.get(index);
            List<List<Object>> moreSubSets = new ArrayList<>();
            for (List<Object> subSet : result) {
                List<Object> newSubSet = new ArrayList<>();
                newSubSet.addAll(subSet);
                newSubSet.add(item);
                moreSubSets.add(newSubSet);
            }
            result.addAll(moreSubSets);
        }
        return result;
    }

    /**
     * Recall that when we're generating a set, we have two choices for each element: (1) the element is in the set (the
     * "yes" state) or (2) the element is not in the set (the "no" state).This means that each subset is a sequence of yeses
     * I nos-e.g., "yes, yes, no, no, yes, no" This gives us 2npossible subsets. How can we iterate through all possible
     * sequences of"yes"I"no" states for all elements? If each "yes" can be treated as a 1 and each "no" can be treated as a
     * 0, then each subset can be represented as a binary string. Generating all subsets, then, really just comes down to
     * generating all binary numbers (that is, all integers). We iterate through all numbers from e to 2n(exclusive) and
     * translate the binary representation of the numbers into a set. Easy!
     * 
     * @param input
     * @return
     */
    public List<List<Integer>> getSubSets_combinatorics(List<Integer> input) {
        List<List<Integer>> result = new ArrayList<>();
        int max = 1 << input.size(); // 2^n
        for (int i = 0; i < max; i++) {
            List<Integer> subSets = convertIntToSet(input, i);
            result.add(subSets);
        }
        return result;
    }

    private List<Integer> convertIntToSet(List<Integer> input, int x) {
        List<Integer> subSets = new ArrayList<>();
        int index = 0;
        for (int i = x; i > 0; i >>= 1) { // means k = k / 2^n = k / 2^1
            if ((i & 1) == 1) {
                subSets.add(input.get(index));
            }
            index++;
        }
        return subSets;
    }

    public static void main(String[] args) {
        PowerSet ps = new PowerSet();
        List<Object> input = Arrays.asList("a", "b", "c");
        List<List<Object>> res = ps.getSubSets_recursion(input, 0);
        res.forEach(o -> System.out.println(o));
        int i = 1;
        i = i >> 1;
        System.out.println(i);
        i = i << 1;
        System.out.println(i);
        List<List<Integer>> res1 = ps.getSubSets_combinatorics(Arrays.asList(3, 4, 6, 7));
        res1.forEach(o -> System.out.println(o));
    }

}
