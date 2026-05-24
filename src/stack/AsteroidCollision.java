package stack;

import java.util.Stack;

public class AsteroidCollision {
    //LeetCode:735
    public static int[] asteroidCollision(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for(int i: arr){
            if(i>=0) stack.push(i);
            else{
                while(!stack.isEmpty() && stack.peek()>=0 && stack.peek() < -1*i){
                    stack.pop();
                }
                if(!stack.isEmpty() && stack.peek()>=0 && stack.peek() == -1*i){
                    stack.pop();
                }else if(stack.isEmpty() || stack.peek() <0){
                    stack.push(i);
                }
            }
        }
        int[] ansArr = new int[stack.size()];
        for(int i=ansArr.length-1; i>=0; i--){
            ansArr[i] = stack.pop();
        }
        return ansArr;
    }
}
