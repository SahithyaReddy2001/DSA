import arrays.*;
import binarySearch.*;
import doublyLinkedList.BasicOperations;
import doublyLinkedList.DeleteAllOccurrences;
import doublyLinkedList.DoublyLinkedList;
import linkedList.*;
import sorting.MergeSort;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList list = LinkedListOperations.createLinkedList(Arrays.asList(1,2,3,4,5));
        RotateLLByKEle.rotateRight(list, 2);
    }
}
