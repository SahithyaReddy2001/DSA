package stack;

import java.util.Stack;

public class InfixToPostfix {


    /*
    * priorities are as follows : ^ -> 3, (* /) -> 2, (+ -) -> 1
    * if char is A-Z or a-z or 0-9 add to string
    * if char is ( add to string
    * if char is ) pop all elements till ( and add to string
    * if char is ^ * / + -  if priority of peek element is >= than char the pop it and add to string else push char to stack
    * */
    public static String infixToPostFixConversion(String infix){
        StringBuilder postFix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
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
                while(!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)){
                    postFix.append(stack.pop());
                }
                stack.push(c);
            }
            i++;
        }
        while(!stack.isEmpty()){
            postFix.append(stack.pop());
        }
        return postFix.toString();
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
