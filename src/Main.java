import linkedList.LinkedListOperations;
import linkedList.ReverseLinkedList;
import linkedList.StartingPointInLL;
import stack.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(RemoveKDigits.removeKdigits("10200",1));
    }
    public static int countKthRoots(int l, int r, int k) {
        int count =0;
        for(int i=0; i<=r/k; i++){
            int product =1;
            for(int j=0; j<k; j++){
                product *= i;
            }
            if(product >= l && product<=r) count++;
        }
        return count;
    }
}
