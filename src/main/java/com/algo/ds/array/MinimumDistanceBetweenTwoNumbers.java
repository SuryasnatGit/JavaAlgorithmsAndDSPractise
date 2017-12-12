

package com.algo.ds.array;

/**
 * http://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/
 */
public class MinimumDistanceBetweenTwoNumbers {

    /**
     * time complexity - O(n^2)
     * 
     * @param input
     * @param x
     * @param y
     * @return
     */
    public int minDistance_simple(int[] input, int x, int y){
        int minDist = Integer.MAX_VALUE;
        for(int i=0;i<input.length;i++){
            for(int j=i+1;j<input.length;j++){
                if (((input[i] == x && input[j] == y) || (input[j] == x && input[i] == y)) && minDist > Math.abs(i - j))
                    minDist = Math.abs(i - j);
            }
        }
        return minDist;
    }

    /**
     * complexity - O(n)
     * 
     * @param input
     * @param x
     * @param y
     * @return
     */
    public int minDistance(int input[],int x, int y){
        int prev = -1;
        int prevFound = -1;
        int min = Integer.MAX_VALUE;
        for(int i=0; i < input.length; i++){
            if(input[i] == x){
                if(prevFound == -1){
                    prevFound = x;
                    prev = i;
                }else if(prevFound == x){
                    prev = i;
                }else{
                    min = min > i - prev ? i -prev : min;
                    prev = i;
                    prevFound = x;
                }
            }else if(input[i] == y){
                if(prevFound == -1){
                    prevFound = y;
                    prev = i;
                }else if(prevFound == y){
                    prev =i;
                }else{
                    min = min > i - prev ? i -prev : min;
                    prevFound = y;
                    prev = i;
                }
            }
        }
        return min;
    }
    
    public static void main(String args[]){
        MinimumDistanceBetweenTwoNumbers mdb = new MinimumDistanceBetweenTwoNumbers();
        int input[] = {6,4,1,5,6,9,10,4,6,6};
        System.out.println(mdb.minDistance(input, 5, 6));
        System.out.println(mdb.minDistance_simple(input, 4, 9));
    }
}
