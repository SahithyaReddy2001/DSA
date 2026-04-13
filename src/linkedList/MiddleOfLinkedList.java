package linkedList;

public class MiddleOfLinkedList {
    //LeetCode:876
    /*Given the head of a singly linked list, return the middle node of the linked list.
    If there are two middle nodes, return the second middle node.

    Example 1:
    Input: head = [1,2,3,4,5]
    Output: [3,4,5]
    Explanation: The middle node of the list is node 3.

    Example 2:
    Input: head = [1,2,3,4,5,6]
    Output: [4,5,6]
    Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.*/

    //BruteForce Solution
    //Iterate over the array and find its length
    //Find the mid with help of length
    //Once mid found iterate over it again return the mid
    //TC: O(N)+O(N/2)
    public static LinkedList middleNode(LinkedList head) {
        int length = 0;
        LinkedList temp = head;
        while(temp != null){
            length++;
            temp = temp.next;
        }

        int mid = (length/2)+1;
        int count=0;
        temp = head;
        while(count<=mid){
            count++;
            if(count==mid){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }


    //Optimal
    //Algorithm: Tortoise and Hair
    //In this approach we will use 2 pointers
    //slow pointer and fast pointer
    //we will move slow pointer by 1 step and fast pointer by 2 steps
    //by the time fast pointer moves to the end of the LL or moves out of LL we will be pointing to mid
    //return the node at mid pointer
    //TC: O(N/2)
    public static LinkedList middleNodeOptimal(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
