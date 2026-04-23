package doublyLinkedList;

import java.util.List;

public class BasicOperations {
    public static DoublyLinkedList doublyLinkedList = new DoublyLinkedList();


    public static DoublyLinkedList createDoublyLinkedList(List<Integer> list){
        doublyLinkedList = new DoublyLinkedList(list.getFirst());
        DoublyLinkedList temp = doublyLinkedList;
        for(int i=1; i<list.size(); i++){
            DoublyLinkedList doublyLinkedList1 = new DoublyLinkedList(list.get(i));
            temp.next = doublyLinkedList1;
            doublyLinkedList1.previous = temp;
            temp = doublyLinkedList1;
        }
        return doublyLinkedList;
    }

    public static void insertNodeAtHead(Integer value){
       DoublyLinkedList doublyLinkedList1 = new DoublyLinkedList(value);
       doublyLinkedList1.next = doublyLinkedList;
       doublyLinkedList.previous = doublyLinkedList1;
       doublyLinkedList = doublyLinkedList1;
    }

    public static void deleteNode(Integer value){
       DoublyLinkedList temp = doublyLinkedList;
       while(temp != null){
           if(temp.value == value) {
               if (temp.previous == null) {
                   doublyLinkedList = temp.next;
                   doublyLinkedList.previous = null;
               } else {
                   temp.previous.next = temp.next;
                   if (temp.next != null) {
                       temp.next.previous = temp.previous;
                   }
               }
           }
           temp = temp.next;
       }
    }

    public static void reverse(){
       DoublyLinkedList temp = doublyLinkedList;
        DoublyLinkedList newHead = null;
       while(temp != null){
           DoublyLinkedList t = temp.next;
           temp.next = temp.previous;
           temp.previous = t;
           newHead = temp;
           temp = temp.previous;
       }
       doublyLinkedList = newHead;
    }
}
