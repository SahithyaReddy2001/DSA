import graph.*;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1,3));
//        list.add(Arrays.asList(0,2,3));
//        list.add(Arrays.asList(1,6));
//        list.add(Arrays.asList(0,4));
//        list.add(Arrays.asList(3,5));
//        list.add(Arrays.asList(4,6));
//        list.add(Arrays.asList(2,5,7,8));
//        list.add(Arrays.asList(6,8));
//        list.add(Arrays.asList(6,7));
//        list.add(Arrays.asList());


        list.add(Arrays.asList(1,2));
        list.add(Arrays.asList(0,3));
        list.add(Arrays.asList(0,1));
        list.add(Arrays.asList(0,2));
        list.add(Arrays.asList(1,3));

//        System.out.println(FindEventualSafeStates.eventualSafeNodes(list.stream()
//                .map(subList -> subList.stream().mapToInt(Integer::intValue).toArray())
//                .toArray(int{}{}::new)));
//
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>(List.of(
                new ArrayList<>(List.of(
                        new ArrayList<>(List.of(1, 1)),
                        new ArrayList<>(List.of(2, 6))
                )),
                new ArrayList<>(List.of(
                        new ArrayList<>(List.of(0, 1)),
                        new ArrayList<>(List.of(2, 3))
                )),
                new ArrayList<>(List.of(
                        new ArrayList<>(List.of(0, 6)),
                        new ArrayList<>(List.of(1, 3))
                ))
        ));
        System.out.println(KruskalsAlgorithm.kruskalsMST(4, new int[][] {{0, 1, 6}, {0, 2, 3}, {1, 3, 9}, {0, 3, 1}, {2, 3, 6}}));
    }


}
