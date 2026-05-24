package stack;

import linkedList.LinkedList;

public class StackLL {
    public LinkedList linkedList = new LinkedList();
    public int size = 0;

    public void push(int num){
        if(size==0) linkedList.setValue(num);
        else{
            LinkedList list = new LinkedList();
            list.setValue(num);
            list.setNext(linkedList);
            linkedList = list;
        }
        size++;
    }

    public int top(){
        if(size == 0) return -1;
        return linkedList.getValue();
    }

    public int size(){
        return size;
    }

    public int pop(){
        int val = linkedList.getValue();
        if(size == 1) {
            size--;
            linkedList = new LinkedList();
        }else {
            linkedList = linkedList.getNext();
            size--;
        }
        return val;
    }

}
