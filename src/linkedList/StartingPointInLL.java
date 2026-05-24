package linkedList;

import java.util.HashMap;
import java.util.Map;

public class StartingPointInLL {
    //LeetCode:142

    /*Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
    Do not modify the linked list.

    Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.

    Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.

    Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.

    Constraints:
    The number of the nodes in the list is in the range [0, 104].
    -105 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.

    Follow up: Can you solve it using O(1) (i.e. constant) memory?*/


    //BruteForce
    //Iterate over linked list and maintain the count hashMap
    //Once the count is 2 return the node
    //TC:O(N)
    //SC:O(N)
    public LinkedList detectCycleBrute(LinkedList head) {
        LinkedList temp = head;
        Map<LinkedList, Integer> map = new HashMap<>();
        while(temp != null){
            if(map.containsKey(temp)){
                return temp;
            }
            map.put(temp, 1);
            temp = temp.next;
        }
        return null;
    }



    //Optimal Solution
    //Step 1: Detect the loop by moving slow pointer by 1 step and fast by 2 steps
    //        where slow and fast meet there will is cycle
    //Step 2: Once cycle fount place slow pointer at head and fast let it be where it is
    //        Now move slow by 1 and fast by 1
    //        once slow == fast that's the starting point
    public static LinkedList detectCycle(LinkedList head) {
        LinkedList slow = head;
        LinkedList fast = head;
        boolean isLoop = false;
        while(fast != null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                slow = head;
                isLoop = true;
                break;
            }
        }
        if(isLoop){
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
        return null;
    }
}
