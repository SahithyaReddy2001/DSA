import binarySearch.Amazon;
import graph.*;
import recursion.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(2,3));
        list.add(Arrays.asList(3,4));
        list.add(Arrays.asList(2,4));
        list.add(Arrays.asList(1,3));
        System.out.println(Bipartite.isBipartiteDFS(new int[][]{{1},{0,2,3},{1,3},{1,2}}));
    }

}
