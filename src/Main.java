import arrays.*;
import binarySearch.*;
import doublyLinkedList.BasicOperations;
import doublyLinkedList.DoublyLinkedList;
import linkedList.*;
import sorting.MergeSort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList list = LinkedListOperations.createLinkedList(Arrays.asList(2,4,3));
        LinkedList list1 = LinkedListOperations.createLinkedList(Arrays.asList(5,6,4));
        AddTwoNumbers.addTwoNumbers(list, list1);
    }
}
