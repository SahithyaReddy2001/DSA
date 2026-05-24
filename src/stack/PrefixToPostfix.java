package stack;

import java.util.Stack;

public class PrefixToPostfix {
    /*
     * we will iterate from last
     * If it's an operand push it into the stack
     * else pop top 2 elements and put top1 top2 operator in stack
     * */
    public static String prefixToPostfixConversion(String prefix) {
        Stack<String> stack = new Stack<>();
        int i =prefix.length()-1;
        while(i >= 0){
            char c = prefix.charAt(i);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9'){
                stack.push(String.valueOf(c));
            }else{
                String one = stack.pop();
                String two = stack.pop();
                stack.push( one + two + c);
            }
            i--;
        }
        return stack.pop();
    }
}
