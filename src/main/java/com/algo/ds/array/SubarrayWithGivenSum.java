

package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
 */
public class SubarrayWithGivenSum {

    class Pair{
        int start;
        int end;
        
        public String toString(){
            return start + " " + end;
        }
    }

    /**
     * complexity - O(n^2) in worst case.
     * 
     * @param input
     * @param sum
     * @return
     */
    public Pair findSubArray_easy(int[] input, int sum) {
        Pair p = new Pair();
        int len = input.length;
        for (int i = 0; i < len; i++) {
            int arr_sum = input[i];
            for (int j = i + 1; j <= len; j++) {
                if (arr_sum == sum) {
                    int y = j - 1;
                    p.start = i;
                    p.end = y;
                    break;
                }
                if (arr_sum > sum || j == len)
                    break;
                arr_sum += input[j];
            }
        }
        return p;
    }

    /**
     * complexity - O(n)
     * 
     * @param input
     * @param sum
     * @return
     */
    public Pair findSubArray(int input[],int sum){
        int currentSum = 0;
        Pair p = new Pair();
        p.start = 0;
        for(int i=0; i < input.length; i++){
            currentSum += input[i];
            p.end = i;
            if(currentSum == sum){
                return p;
            }else if(currentSum > sum){
                int s = p.start;
                while(currentSum  > sum){
                    currentSum -= input[s];
                    s++;
                }
                p.start = s;
                if(currentSum == sum){
                    return p;
                }
            }
        }
        return null;
    }
    
    public static void main(String args[]){
        SubarrayWithGivenSum sgs = new SubarrayWithGivenSum();
        int input[] = {6,3,9,11,1,3,5};
        System.out.println(sgs.findSubArray(input,15));
        System.out.println(sgs.findSubArray_easy(input, 24));
    }
}
