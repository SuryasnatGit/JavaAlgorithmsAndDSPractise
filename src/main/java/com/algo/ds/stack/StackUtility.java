package com.algo.ds.stack;

public class StackUtility {

	public String reverseString(String sourceStr){
		int n = sourceStr.length();
		StackAsArray stackAsArray = new StackAsArray(n);
		for(int i=0;i<n;i++){
			char c = sourceStr.charAt(i);
			stackAsArray.push(c);
		}
		String finalStr = "";
		while(!stackAsArray.isEmpty()){
			char c = stackAsArray.pop();
			finalStr+=c;
		}
		return finalStr;
	}
	
	public void bracketChecker(String sourceStr){
		int n = sourceStr.length();
		StackAsArray  array = new StackAsArray(n);
		for(int i=0;i<n;i++){
			char c = sourceStr.charAt(i);
			switch(c){
				case '{':
				case '[':
				case '(':
					array.push(c);
					break;
				case '}':
				case ']':
				case ')':
					if(!array.isEmpty()){
						char ch = array.pop();
						if((c=='}' && ch!='{') || (c==']' && ch!='[') && (c==')' && ch!='(')){
							System.out.println("Error "+c+"at "+i);
						}
					}else{
						System.out.println("Error "+c+"at "+i);
					}
					break;
				default:
					break;
			}
		}
		if(!array.isEmpty()){
			System.out.println("Missing right delimiter");
		}
	}
}
