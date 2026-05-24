package stack;

public class Stack {
    public int[] arr = new int[10];
    int top = -1;
     public void push(int num){
         top++;
         arr[top] = num;
     }

     public int top(){
         if(top == -1) return -1;
         return arr[top];
     }

     public int size(){
         return top+1;
     }

     public int pop(){
         int num = arr[top];
         arr[top] = 0;
         top--;
         return num;
     }
}
