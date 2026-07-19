package recursion;


import java.util.Stack;

public class SortStackUsingRecursion {
    /*You are given a stack of integers. Your task is to sort the stack in descending order using recursion,
    such that the top of the stack contains the greatest element. You are not allowed
    to use any loop-based sorting methods (e.g., quicksort, mergesort). You may only use recursive operations
     and the standard stack operations (push, pop, peek/top, and isEmpty).*/

    public static void sortStack(Stack<Integer> stack){
        if(stack.isEmpty()) return;
        int top = stack.pop();
        sortStack(stack);
        insert(stack, top);
    }

    public static void insert(Stack<Integer> stack, int ele){
        if(stack.isEmpty() || stack.peek() <= ele){
            stack.push(ele);
            return;
        }
        int top = stack.pop();
        insert(stack, ele);
        stack.push(top);
    }
}
