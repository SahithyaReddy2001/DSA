package linkedList;

public class AddTwoNumbers {
    //LeetCode:2
    /*You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Example 1:
    Input: l1 = [2,4,3], l2 = [5,6,4]
    Output: [7,0,8]
    Explanation: 342 + 465 = 807.

    Example 2:
    Input: l1 = [0], l2 = [0]
    Output: [0]

    Example 3:
    Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
    Output: [8,9,9,9,0,0,0,1]

    Constraints:
    The number of nodes in each linked list is in the range [1, 100].
    0 <= Node.val <= 9
    It is guaranteed that the list represents a number that does not have leading zeros.*/


    public static LinkedList addTwoNumbers(LinkedList l1, LinkedList l2) {
        LinkedList temp1 = l1;
        LinkedList temp2 = l2;
        int carry = 0;
        LinkedList ans = new LinkedList(-1, null);
        LinkedList finAns = ans;
        while(temp1 != null || temp2 != null){
            int sum = 0;
            LinkedList temp = null;
            if(temp1 != null && temp2 != null){
                sum = temp2.value + temp1.value + carry;
                temp = new LinkedList(sum%10, null);
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else if (temp1 != null && temp2 == null) {
                sum =  temp1.value + carry;
                temp = new LinkedList(sum%10, null);
                temp1 = temp1.next;
            } else if (temp1 == null && temp2 != null) {
                sum = temp2.value  + carry;
                temp = new LinkedList(sum%10, null);
                temp2 = temp2.next;
            }
            ans.next = temp;
            carry = sum/10;
            ans = ans.next;
        }

        if(carry != 0){
            ans.next = new LinkedList(carry, null);
        }
        return finAns.next;
    }
}
