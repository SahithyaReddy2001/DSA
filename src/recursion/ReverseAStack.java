package recursion;

import java.util.Stack;

public class ReverseAStack {
    /*
    * You are given a stack of integers. Your task is to reverse the stack using recursion. You may only use standard stack operations (push, pop, top/peek, isEmpty).
    * You are not allowed to use any loop constructs or additional data structures like arrays or queues.
    * */

    public static void sortStack(Stack<Integer> stack){
        if(stack.isEmpty()) return;
        int top = stack.pop();
        sortStack(stack);
        insert(stack, top);
    }

    public static void insert(Stack<Integer> stack, int ele){
        if(stack.isEmpty()){
            stack.push(ele);
            return;
        }
        int top = stack.pop();
        insert(stack, ele);
        stack.push(top);
    }
}
