package linkedList;

public class LinkedList {
    int value;
    LinkedList next;

    public LinkedList(){

    }

    public LinkedList(int value, LinkedList next) {
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public LinkedList getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(LinkedList next) {
        this.next = next;
    }

}
