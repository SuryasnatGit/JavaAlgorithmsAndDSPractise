package com.algo.ds.amazon;

public class HashFunctions {

	public int hashFunction1(String key){
		int arraySize = 0;
		int hashVal = 0;
		int pow27 = 1; // 1(27^0),27(27^1),27*27(27^2).. etc
		for(int j=key.length() - 1;j>=0;j--){
			int letter = key.charAt(j) - 96;
			hashVal = pow27 * letter;
			pow27 *= 27;
		}
		return hashVal % arraySize;
	}
	
}
