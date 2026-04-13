package doublyLinkedList;

public class DoublyLinkedList {
    int value;
    DoublyLinkedList previous;
    DoublyLinkedList next;

    public DoublyLinkedList(int value){
        this.value = value;
    }

    public DoublyLinkedList(){
    }

    public DoublyLinkedList(int value, DoublyLinkedList previous, DoublyLinkedList next){
        this.value = value;
        this.previous = previous;
        this.next = next;
    }


}
