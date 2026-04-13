import arrays.FindMissingAndRepeatingNumber;
import arrays.LeftRotateArrayByDElements;
import arrays.MajorityElementsGreaterThanNBy2;
import arrays.RemoveDuplicatesFromSortedArray;
import binarySearch.*;
import doublyLinkedList.BasicOperations;
import doublyLinkedList.DoublyLinkedList;
import linkedList.LinkedList;
import linkedList.LinkedListOperations;
import linkedList.MiddleOfLinkedList;
import linkedList.ReverseLinkedList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList list = LinkedListOperations.createLinkedList(Arrays.asList(1,2,3,4));
        ReverseLinkedList.recursiveReverseListOptimal(list);
    }
}
