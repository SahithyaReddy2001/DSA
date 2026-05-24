package stack;

import java.util.Stack;

public class InfixToPrefix {
    /*
     * Reverse the string and replace ( with ) and ) with (
     * do infix to postfix conversion only change in fifth step
     * 1) priorities are as follows : ^ -> 3, (* /) -> 2, (+ -) -> 1
     * 2) if char is A-Z or a-z or 0-9 add to string
     * 3) if char is ( add to string
     * 4) if char is ) pop all elements till ( and add to string
     * 5) if char is ^ * / + -  if priority od peek element is > than char the pop it and add to string else push char to stack
     *
     * Reverse the answer string
     * */
    public static String infixToPreFixConversion(String infix){
        int start = 0;
        StringBuilder reverse= new StringBuilder();
        while(start < infix.length()){
            reverse.insert(0, (infix.charAt(start) == '(' ? ')' : infix.charAt(start) == ')' ? '(' : infix.charAt(start)));
            start++;
        }
        infix = reverse.toString();
        StringBuilder postFix = new StringBuilder();
        java.util.Stack<Character> stack = new Stack<>();
        int i =0;
        while(i<infix.length()){
            char c = infix.charAt(i);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9'){
                postFix.append(c);
            }else if (c == '('){
                stack.push(c);
            }else if(c== ')'){
                while(!stack.empty() && stack.peek() != '('){
                    postFix.append(stack.pop());
                }
                stack.pop();
            }else{
                while (!stack.isEmpty() && getPriority(stack.peek()) > getPriority(c)) {
                    postFix.append(stack.pop());
                }
                stack.push(c);
            }
            i++;
        }
        while(!stack.isEmpty()){
            postFix.append(stack.pop());
        }
        return postFix.reverse().toString();
    }

    public static int getPriority(char c){
        if(c == '^'){
            return 3;
        }else if(c == '*' || c == '/'){
            return 2;
        }else if(c == '+' || c == '-'){
            return 1;
        }
        return -1;
    }
}
