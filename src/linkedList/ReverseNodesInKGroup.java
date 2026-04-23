package linkedList;

public class ReverseNodesInKGroup {
    //LeetCode: 25
    /*Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
    k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
    You may not alter the values in the list's nodes, only nodes themselves may be changed.

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]

    Example 2:
    Input: head = [1,2,3,4,5], k = 3
    Output: [3,2,1,4,5]

    Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000

    Follow-up: Can you solve the problem in O(1) extra memory space?*/

    //SC: O(1)
    //TC: 0(2N)
    public static LinkedList reverseKGroup(LinkedList head, int k) {
        LinkedList temp = head;
        LinkedList next = null;
        LinkedList prev = null;
        while(temp != null){
            LinkedList kth = findKth(temp, k);
            if(kth == null){
                if(temp == head){
                    return head;
                }
                prev.next = temp;
                break;
            }else{
                next = kth.next;
                kth.next = null;
                LinkedList dummy = reverse(temp);
                if(temp == head){
                    head = dummy;
                }else{
                    prev.next = dummy;
                }
                prev = temp;
                temp = next;
            }
        }
        return head;
    }

    public static LinkedList findKth(LinkedList list, int k){
        LinkedList temp = list;
        while(temp != null && k>1){
            temp = temp.next;
            k--;
        }
        return temp;
    }

    public static LinkedList reverse(LinkedList list){
        LinkedList temp = list;
        LinkedList prev = null;
        while(temp != null){
            LinkedList next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }
}
