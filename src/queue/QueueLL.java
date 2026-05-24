package queue;

import linkedList.LinkedList;

public class QueueLL {
    LinkedList linkedList = new LinkedList();
    LinkedList start = null;
    LinkedList end = null;
    int size = 0;

    public void push(int num){
        if(size==0){
            linkedList.setValue(num);
            start = linkedList;
            end = linkedList;
        }
        else{
            LinkedList list = new LinkedList();
            list.setValue(num);
            list.setNext(linkedList);
            linkedList.setNext(list);
            end = list;
        }
        size++;
    }

    public int top(){
        if(size == 0) return -1;
        return end.getValue();
    }

    public int size(){
        return size;
    }

    public int pop(){
        int val = size == 0? -1 : linkedList.getValue();
        if(size == 1) {
            size--;
            linkedList = new LinkedList();
            start = null;
            end = null;
        }else {
            linkedList = linkedList.getNext();
            start = linkedList;
            size--;
        }
        return val;
    }
}
