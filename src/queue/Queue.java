package queue;

public class Queue {
    public int[] arr = new int[10];
    int start = -1;
    int end = -1;

    public void push(int num){
        if(start == -1) start ++;
        end++;
        arr[end] = num;
    }

    public int top(){
        if(start == -1) return -1;
        return arr[start];
    }

    public int size(){
        return end-start+1;
    }

    public int pop(){
        int num = arr[start];
        arr[start] = 0;
        start++;
        return num;
    }
}
