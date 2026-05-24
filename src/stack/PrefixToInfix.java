package stack;

import java.util.Stack;

public class PrefixToInfix {
    /*
     * we will iterate from the back this time
     * If it's an operand push it into the stack
     * else pop top 2 elements and put the operator in between those 2 operands, but it should be top1 - top2 not top2-top1
     * */
    public static String prefixToInfixConversion(String prefix) {
        java.util.Stack<String> stack = new Stack<>();
        int i = prefix.length()-1;
        while(i >= 0){
            char c = prefix.charAt(i);
            if(c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c >= '0' && c <= '9'){
                stack.push(String.valueOf(c));
            }else{
                String one = stack.pop();
                String two = stack.pop();
                stack.push("(" + one + c + two + ")");
            }
            i--;
        }
        return stack.pop();
    }
}
