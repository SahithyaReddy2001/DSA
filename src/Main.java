import CompanyBased.Visa;
import binarySearch.Amazon;
import binarySearch.PayPal;
import graph.*;
import recursion.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(3));
//        list.add(Arrays.asList(5,4));
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList());
//        list.add(Arrays.asList(2,6));
//        list.add(Arrays.asList(1,3));
//        list.add(Arrays.asList(2,4));
//        list.add(Arrays.asList(3,5));
//        list.add(Arrays.asList(6,4));
//        list.add(Arrays.asList(1,5,7));
//        list.add(Arrays.asList(6));

        list.add(Arrays.asList());
        list.add(Arrays.asList());
        list.add(Arrays.asList(3));
        list.add(Arrays.asList(1));
        list.add(Arrays.asList(0,1));
        list.add(Arrays.asList(0,2));
        System.out.println(FindEventualSafeStates.eventualSafeNodesBfs(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }

}
