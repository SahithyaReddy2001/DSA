package linkedList;


public class RotateLLByKEle {
    //LeetCode: 61
    /*Given the head of a linked list, rotate the list to the right by k places.

    Example 1:
    Input: head = [1,2,3,4,5], k = 2
    Output: [4,5,1,2,3]

    Example 2:
    Input: head = [0,1,2], k = 4
    Output: [2,0,1]

    Constraints:
    The number of nodes in the list is in the range [0, 500].
    -100 <= Node.val <= 100
    0 <= k <= 2 * 109*/



    //TC: O(3N)
    //SC:O(1)
    public static LinkedList rotateRight(LinkedList head, int k) {
        int length = 0;
        LinkedList temp = head;
        while(temp != null){
            temp = temp.next;
            length++;
        }
        k = k%length;

        if(k==0) return head;

        int index = length - k;

        temp = head;
        LinkedList start = temp;
        while(index > 1){
            temp = temp.next;
            index--;
        }

        LinkedList second = temp.next;
        temp.next = null;
        second = reverse(second);
        temp = reverse(start);
        start.next = second;
        head = reverse(temp);
        return head;
    }

    public static LinkedList reverse(LinkedList head){
        LinkedList curr = head;
        LinkedList prev = null;
        while(curr != null){
            LinkedList next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    //TC: 0(N) + O(N-K)
    //SC:O(1)
    public static LinkedList rotateRightOptimal(LinkedList head, int k) {
        int length = 0;
        LinkedList temp = head;
        LinkedList prev = null;
        while(temp != null){
            prev = temp;
            temp = temp.next;
            length++;
        }
        k = k%length;

        if(k==0) return head;

        prev.next = head;

        int index = length - k;

        temp = head;
        while(index > 1){
            temp = temp.next;
            index--;
        }

        head = temp.next;
        temp.next = null;
        return head;
    }
}
