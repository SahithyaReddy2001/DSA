package linkedList;

import java.util.ArrayList;
import java.util.List;

public class OddEvenLinkedList {
    //LeetCode: 328
    /*Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.
    The first node is considered odd, and the second node is even, and so on.
    Note that the relative order inside both the even and odd groups should remain as it was in the input.
    You must solve the problem in O(1) extra space complexity and O(n) time complexity.

    Example 1:
    Input: head = [1,2,3,4,5]
    Output: [1,3,5,2,4]

    Example 2:
    Input: head = [2,1,3,5,6,4,7]
    Output: [2,3,6,7,1,5,4]

    Constraints:
    The number of nodes in the linked list is in the range [0, 104].
    -106 <= Node.val <= 106*/

    //BruteForce
    //Iterate over the elements and save them in a list by categorizing them into even and odd
    //TC:O(N)+0(N)
    //SC:O(N)
    public static LinkedList oddEvenList(LinkedList head) {
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        LinkedList temp = head;
        int count = 1;
        while(temp != null){
            if(count %2 != 0){
                oddList.add(temp.value);
            }else{
                evenList.add(temp.value);
            }
            temp = temp.next;
            count++;
        }

        temp = head;
        for (Integer value : oddList) {
            temp.value = value;
            temp = temp.next;
        }

        for (Integer integer : evenList) {
            temp.value = integer;
            temp = temp.next;
        }
        return head;
    }



    //Optimal
    // Repoint the links
    // 1->2->3->4->5->6
    //  1  2  3  4  5  6
    //  |-----|-----|
    //     |-----|-----|
    //at last 5 will be pointed to 2
    // TC:O(N)
    //SC:O(1)
    public static LinkedList oddEvenListOptimal(LinkedList head) {
        if(head == null || head.next == null){
            return head;
        }
        LinkedList slow = head;
        LinkedList fast = head.next;
        LinkedList temp = head.next;
        while(fast != null && fast.next != null){
            slow.next = slow.next.next;
            slow = slow.next;

            fast.next = fast.next.next;
            fast = fast.next;
        }
        slow.next = temp;
        return head;
    }
}
