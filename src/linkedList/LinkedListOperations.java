package linkedList;

import java.util.List;

public class LinkedListOperations {
    public static LinkedList head = new LinkedList();

    public static LinkedList createLinkedList(List<Integer> list){
        LinkedList head = new LinkedList();
        head.setValue(list.getFirst());
        LinkedList temp = head;
        for(int i=1; i<list.size(); i++){
            temp.setNext(new LinkedList(list.get(i), null));
            temp = temp.getNext();
        }
        return head;
    }

    public static void printLinkedList(){
        LinkedList temp = head;
        while(temp != null){
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public static void findLength(){
        LinkedList temp = head;
        int count = 0;
        while(temp!=null){
            count++;
            temp = temp.getNext();
        }
        System.out.println(count);
    }

    public static void search(int i){
        LinkedList temp = head;
        while (temp!=null){
            if(temp.getValue() == i){
                System.out.println(i + " found");
                return;
            }
            temp = temp.getNext();
        }
        System.out.println(i + " not found");

    }

    public static void insertAtStart(int i){
        head = new LinkedList(i, head);
    }

    public static void insertAtLast(int i){
        LinkedList temp = head;
        LinkedList temp2 = temp;
        while(temp != null){
            temp = temp.getNext();
            if(temp != null){
                temp2 = temp;
            }
        }
        LinkedList l = new LinkedList(i, null);
        temp2.setNext(l);
    }

}
