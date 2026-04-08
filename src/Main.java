import arrays.LeftRotateArrayByDElements;
import arrays.RemoveDuplicatesFromSortedArray;
import binarySearch.*;
import linkedList.LinkedListOperations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedListOperations.createLinkedList(Arrays.asList(1,2,3,4,5));
        LinkedListOperations.printLinkedList();
        LinkedListOperations.insertAtStart(0);
        LinkedListOperations.printLinkedList();
        LinkedListOperations.insertAtLast(10);
        LinkedListOperations.printLinkedList();
    }
}
