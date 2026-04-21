package linkedList;

public class AddOneToANumber {
    // 9->9->9
    //O/P: 1->0->0->0


    //TC: O(N)
    //SC: O(N)
    public static void addOneBrute(LinkedList list){
        if(list==null) return;
        int length = 0;
        int num =0;
        LinkedList temp = list;
        while(temp != null){
            num = num*10 + temp.value;
            temp = temp.next;
            length++;
        }
        num+=1;
        temp = list;
        String numString = String.valueOf(num);
        if(numString.length() != length){
            LinkedList dummy = new LinkedList(numString.charAt(0)-'0', null);
            dummy.next = list;
            for(int i=1; i<numString.length(); i++){
                temp.value = numString.charAt(i)-'0';
                temp = temp.next;
            }
            list = dummy;
        }else{
            for(int i=0; i<numString.length(); i++){
                temp.value = numString.charAt(i)-'0';
                temp = temp.next;
            }
        }
    }

    public static LinkedList reverseLL(LinkedList list){
        LinkedList prev = null;
        LinkedList curr = list;
        while(curr != null){
            LinkedList next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public static LinkedList addOneOptimal(LinkedList list){
        LinkedList reversed = reverseLL(list);
        LinkedList temp = reversed;
        int carry = 1;
        LinkedList last = null;
        while(temp != null){
            int val = temp.value+carry;
            temp.value = val%10;
            carry = val/10;
            last=temp;
            temp = temp.next;
        }

        if(carry != 0){
            last.next = new LinkedList(carry, null);
        }
        list = reverseLL(reversed);
        return list;
    }



    //TODO: Recursive IMPL


}

