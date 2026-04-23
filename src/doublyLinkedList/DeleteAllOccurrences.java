package doublyLinkedList;

public class DeleteAllOccurrences {
    public static DoublyLinkedList deleteAllOccurrences(DoublyLinkedList head, int k) {
        DoublyLinkedList temp = head;
        while(temp != null){
            if(temp.value == k){
                if (temp == head) {
                    head = temp.next;
                }
                if(temp.previous != null){
                    temp.previous.next = temp.next;
                }
                if(temp.next != null){
                    temp.next.previous = temp.previous;
                }
            }
            temp = temp.next;
        }
        return head;
    }
}
