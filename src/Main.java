import arrays.*;
import binarySearch.*;
import doublyLinkedList.BasicOperations;
import doublyLinkedList.DoublyLinkedList;
import linkedList.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LinkedList list = LinkedListOperations.createLinkedList(Arrays.asList(1,2,3,4,5));
        System.out.println(RemoveNthNodeFromEndOfList.removeNthFromEndOptimal(list, 5));
    }
}
