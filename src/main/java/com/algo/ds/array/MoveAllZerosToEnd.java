

package com.algo.ds.array;


/**
 * {0,0,1,2,0,5,6,7,0} becomes 125670000
 * 
 * @author ctsuser1
 */
public class MoveAllZerosToEnd {

    public void moveZeros(int arr[]){
        int slow =0;
        int fast =0;
		// accumulates all non 0s in the beginning of the array
        while(fast < arr.length){
            if(arr[fast] == 0){
                fast++;
				continue; // moves to the next iteration.
            }
            arr[slow] = arr[fast];
            slow++;
            fast++;
        }
		// accumulates all 0s at the end of the array
        while(slow < arr.length){
            arr[slow++] = 0;
        }
    }
    
    public static void main(String args[]){
        MoveAllZerosToEnd maz  = new MoveAllZerosToEnd();
        int arr[] = {0,0,1,2,0,5,6,7,0};
        maz.moveZeros(arr);
        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}
