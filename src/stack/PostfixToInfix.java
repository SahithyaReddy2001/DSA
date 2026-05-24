package stack;

import java.util.Stack;

public class PostfixToInfix {

    /*
    * If it's an operand push it into the stack
    * else pop top 2 elements and put the operator in between those 2 operands but it should be top2 - top1 not top1-top2
    * */
    public static String postfixToInfixConversion(String postFix) {
        Stack<String> stack = new Stack<>();
        int i = 0;
        while(i < postFix.length()){
            char c = postFix.charAt(i);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9'){
                stack.push(String.valueOf(c));
            }else{
                String one = stack.pop();
                String two = stack.pop();
                stack.push("(" + two + c + one + ")");
            }
            i++;
        }
        return stack.pop();
    }
}