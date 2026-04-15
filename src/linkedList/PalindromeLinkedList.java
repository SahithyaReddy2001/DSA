package linkedList;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {
    //LeetCode:
    /*Given the head of a singly linked list, return true if it is a palindrome or false otherwise.

            Example 1:
    Input: head = [1,2,2,1]
    Output: true

    Example 2:
    Input: head = [1,2]
    Output: false

    Constraints:
    The number of nodes in the list is in the range [1, 105].
    0 <= Node.val <= 9

    Follow up: Could you do it in O(n) time and O(1) space?*/


    //BruteForce
    //Iterate over the linkedlist and save the values in List
    //check if array is palindrome or not
    //TC: O(N)+O(N/2)
    //SC: O(N)
    public static boolean isPalindrome(LinkedList head) {
        LinkedList temp = head;
        List<Integer> list = new ArrayList<>();
        while(temp != null){
            list.add(temp.value);
            temp = temp.next;
        }
        int start = 0;
        int end = list.size()-1;
        while(start<=end){
            if(!list.get(start).equals(list.get(end)))
                return false;
            start++;
            end--;
        }
        return true;
    }



    //Optimal
    //Find the mid of the linked list
    //and reverse the linked list from mid
    //now put slow pointer at head and fast pointer at mid.
    //compare the values until slow or fast pointers become null
    // if any value mismatch is found return false
    //else true
    //SC:O(1)
    //TC:(3N/2)
    public static boolean isPalindromeOptimal(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedList curr = slow.next;
        LinkedList prev = null;
        while(curr != null){
            LinkedList temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        slow.next = prev;

        fast = slow.next;
        slow = head;
        while(slow!=null && fast != null){
            if(slow.value != fast.value){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;

    }
}
