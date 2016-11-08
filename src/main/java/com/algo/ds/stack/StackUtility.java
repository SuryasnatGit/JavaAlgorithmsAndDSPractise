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

    /**
     * better impl of bracket match checker using linked list as stack.
     * 
     * @param expression
     * @return
     */
    public boolean isMatched(String expression) {
        String opening = "({[";
        String closing = ")}]";
        Stack<Character> stack = new LinkedListStack<>();
        for (char c : expression.toCharArray()) {
            if (opening.indexOf(c) != -1) {
                stack.push(c);
            } else if (closing.indexOf(c) != -1) {
                if (stack.isEmpty())
                    return false;
                if (closing.indexOf(c) != opening.indexOf(stack.pop()))
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isHtmlMatched(String html) {
        Stack<String> stack = new LinkedListStack<>();
        int start = html.indexOf("<");
        while (start != -1) {
            int end = html.indexOf(">", start + 1);
            if (end == -1)
                return false;
            String tag = html.substring(start + 1, end);
            if (!tag.contains("/")) {
                stack.push(tag);
            } else {
                if (stack.isEmpty())
                    return false;
                if (!tag.substring(1).equals(stack.pop()))
                    return false;
            }
            start = html.indexOf("<", end + 1);
        }
        return stack.isEmpty();
    }
}
