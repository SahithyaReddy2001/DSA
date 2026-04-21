package linkedList;

public class SortLinkedListOf0s1s2s {

    //Give a linked list of 0s 1s and 2s
    //sort them

    //TC: O(2N)
    public static void sortBrute(LinkedList head){
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        LinkedList temp = head;
        while(temp != null){
            if(temp.value == 0){
                zeros++;
            }else if(temp.value == 1){
                ones++;
            }else{
                twos++;
            }
            temp = temp.next;
        }
        temp = head;
        while(zeros > 0){
            temp.value = 0;
            temp = temp.next;
            zeros--;
        }
        while(ones > 0){
            temp.value = 1;
            temp = temp.next;
            ones--;
        }
        while(twos > 0){
            temp.value = 2;
            temp = temp.next;
            twos--;
        }
    }


    //SC: O(1)
    //TC: O(N)
    public static void sortOptimal(LinkedList head){
        LinkedList dummyZero = new LinkedList(-1, null);
        LinkedList dummyOne = new LinkedList(-1, null);
        LinkedList dummyTwo = new LinkedList(-1, null);
        LinkedList temp = head;
        LinkedList zero = dummyZero;
        LinkedList one = dummyOne;
        LinkedList two = dummyTwo;
        while(temp != null){
            if(temp.value == 0){
                zero.next = temp;
                zero = zero.next;
            }else if(temp.value == 1){
                one.next = temp;
                one = one.next;
            }else{
                two.next = temp;
                two = two.next;
            }
            temp = temp.next;
        }
        zero.next = dummyOne.next;
        one.next = dummyTwo.next;
        two.next = null;
        head = dummyZero.next;
    }
}
