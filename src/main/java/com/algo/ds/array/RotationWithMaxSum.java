package com.algo.ds.array;


import java.util.Arrays;


/**
 * Given an input array find which rotation will give max sum of i * arr[i]
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
 * 
 * Example :
 * Input: arr[] = {1, 20, 2, 10}
 * Output: 72
 * We can 72 by rotating array twice.
 * {2, 10, 1, 20}
 * 20*3 + 1*2 + 10*1 + 2*0 = 72

 * Input: arr[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
 * Output: 330
 * We can 330 by rotating array 9 times.
 * {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
 * 0*1 + 1*2 + 2*3 ... 9*10 = 330
 * 
 * Date 12/30/2015
 * @author Tushar Roy
 *
 */
public class RotationWithMaxSum {

    /*
     * O(n*n)
     */
    int simpleSolution(int[] input) {
        // find all rotations one by one
        int maxSum = 0;
        for (int i = 1; i <= input.length; i++) { // number of rotations. O(n)
            // rotate array by 1 step
            rotateArray(input, 1);
            System.out.println(Arrays.toString(input));
            // calculate sum after this rotation
            int sum = 0;
            for (int j = 0; j < input.length; j++)
                sum += (j * input[j]);
            System.out.println(sum);
            if (sum > maxSum)
                maxSum = sum;
        }
        return maxSum;
    }

    /**
     * O(n)
     * 
     * @param input
     * @param count
     */
    private void rotateArray(int[] input, int count) {
        for (int i = 0; i < count; i++) {
            for (int j = input.length - 1; j > 0; j--) {
                int temp = input[j];
                input[j] = input[j - 1];
                input[j - 1] = temp;
            }
        }
    }


    /**
     * Time Complexity: O(n) Auxiliary Space: O(1)
     * 
     * @param input
     * @return
     */
    int maxSum(int input[]) {
        int arrSum = 0;
        int rotationSum = 0;
        for (int i =0; i < input.length; i++) {
            arrSum += input[i];
            rotationSum += i*input[i];
        }

        int maxRotationSum = rotationSum;

        for (int i = 1; i < input.length; i++) {
            rotationSum += input.length*input[i - 1] - arrSum;
            maxRotationSum = Math.max(maxRotationSum, rotationSum);
        }
        return maxRotationSum;
    }

    public static void main(String args[]) {
        int input[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        RotationWithMaxSum rms = new RotationWithMaxSum();
        System.out.println(rms.maxSum(input));
        System.out.println(rms.simpleSolution(input));
    }
}
