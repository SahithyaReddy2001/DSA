package linkedList;

import java.util.Stack;

public class ReverseLinkedList {
    //LeetCode:206
    /*Given the head of a singly linked list, reverse the list, and return the reversed list.
    Example 1:
    Input: head = [1,2,3,4,5]
    Output: [5,4,3,2,1]

    Example 2:
    Input: head = [1,2]
    Output: [2,1]

    Example 3:
    Input: head = []
    Output: []

    Constraints:
    The number of nodes in the list is the range [0, 5000].
    -5000 <= Node.val <= 5000*/

    //BruteForce
    //Store the values in stack
    //save the values by poping from stack
    //TC: O(2N)
    //SC: O(N)
    public static LinkedList reverseList(LinkedList head) {
        LinkedList temp = head;
        Stack<Integer> stack = new Stack<>();
        while(temp != null){
            stack.push(temp.value);
            temp = temp.next;
        }
        temp = head;
        while(temp != null){
            temp.value = stack.pop();
            temp = temp.next;
        }
        return head;
    }


    //Optimal
    //TC:O(N)
    public LinkedList reverseListOptimal(LinkedList head) {
        LinkedList temp = head;
        LinkedList prev = null;
        while(temp != null){
            LinkedList front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }


    //Optimal
    //TC:O(N)
    public static LinkedList recursiveReverseListOptimal(LinkedList head) {
        if(head == null || head.next == null)
            return head;
        LinkedList newHead= recursiveReverseListOptimal(head.next);
        LinkedList front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }
}
