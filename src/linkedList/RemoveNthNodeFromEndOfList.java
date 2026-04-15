package linkedList;

public class RemoveNthNodeFromEndOfList {
    //LeetCode: 19
    /*Given the head of a linked list, remove the nth node from the end of the list and return its head.
    Example 1:
    Input: head = [1,2,3,4,5], n = 2
    Output: [1,2,3,5]

    Example 2:
    Input: head = [1], n = 1
    Output: []

    Example 3:
    Input: head = [1,2], n = 1
    Output: [1]

    Constraints:
    The number of nodes in the list is sz.
    1 <= sz <= 30
    0 <= Node.val <= 100
    1 <= n <= sz
    Follow up: Could you do this in one pass?*/



    //BruteForce
    //Iterate over the linked list and find its length
    //Once length for iterate to length-n element
    //point its next to its next -> next
    // TC:O(2N)
    // SC: O(N)
    public static LinkedList removeNthFromEnd(LinkedList head, int n) {
        int count = 0;
        LinkedList temp = head;
        while(temp != null){
            count++;
            temp = temp.next;
        }
        int index = count -n;
        if(index == 0){
            return head.next;
        }
        temp = head;
        count = 1;
        while(count < index){
            temp = temp.next;
            count++;
        }
        if(temp != null && temp.next != null){
            temp.next = temp.next.next;
        }
        return head;
    }


    //Optimal
    //first move fast pointer by n element
    // after that move slow pointer and fast pointer by 1 step
    // once fast pointer moves end
    // repoint slow.next to slow.next.next
    //TC: O(N)
    //SC:O(1)
    public static LinkedList removeNthFromEndOptimal(LinkedList head, int n) {
        LinkedList slow = head;
        LinkedList fast = head;
        for(int i = 0; i<n; i++){
            fast = fast.next;
        }
        if(fast == null){
            return head.next;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}
