package com.algo.ds.hashtable;

public class HashFunction {

	private int arraySize;

	/**
	 * for example if input is "cats"
	 * the key can be = 27^3*c + 27^2 *a + 27^1*t + 27^0*s
	 * index = key % arraySize
	 * 
	 * this operation has 2 multiplication and 1 addition. Not much efficient
	 * 
	 * @param input
	 * @return 
	 */
	public int hashFunction1(String input){
		int hashVal = 0;
		int pow27 = 1;
		for(int i = input.length()-1;i>=0;i--){ // right to left
			int s = input.charAt(i) - 96;
			hashVal += pow27 * s;
			pow27 *= 27;
		}
		return hashVal % arraySize;
	}
	
	/**
	 * Horner's equation
	 *(((var4*n + var3)*n + var2)*n + var1)*n + var0
	 *here n = 27
	 *
	 *this operation has 1 multiplication and 1 addition. Better soln than 1st.
	 *But problem is longer string will cause the hashVal to increase beyond the int or long boundry limit.
	 *It's not the final index which is too big. It's the intermediate key value..
	 *
	 * @param key
	 * @return
	 */
	public int hashFunction2(String key){
		int hashVal = key.charAt(0) - 96;
		for(int i=1;i<key.length();i++){ // left to right
			int ch = key.charAt(i) - 96;
			hashVal = hashVal * 27 + ch;
		}
		return hashVal % arraySize;
	}
	
	/**
	 * Optimal soln - Add the modulo operation in each step of the iteration.
	 * @return
	 */
	public int hashFunction3(String key){
		int hashVal = 0;
		for(int i=0;i<key.length();i++){ // left to right
			int ch = key.charAt(i) - 96;
			hashVal = (hashVal * 27 + ch) % arraySize;
		}
		return hashVal;
	}
	
}
