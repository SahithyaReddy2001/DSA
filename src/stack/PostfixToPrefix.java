package stack;

import java.util.Stack;

public class PostfixToPrefix {
    /*
     * If it's an operand push it into the stack
     * else pop top 2 elements and put operator top2 top1 in stack
     * */
    public static String postfixToPrefixConversion(String postfix) {
        Stack<String> stack = new Stack<>();
        int i =0;
        while(i < postfix.length()){
            char c = postfix.charAt(i);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9'){
                stack.push(String.valueOf(c));
            }else{
                String one = stack.pop();
                String two = stack.pop();
                stack.push(c + two + one);
            }
            i++;
        }
        return stack.pop();
    }
}
