package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphRepresentation {
    //For all Graphs we have n = total nodes, m= total edges(optional), List<List<Integer>> = actual edges
    // we have to iterate over the list<list> and save them into matrix or list according to the requirement

    public static int[][] matrixRepresentation(List<List<Integer>> list, int nodes){
        int[][] matrix = new int[nodes+1][nodes+1];
        for(List<Integer> l: list){
            int i = l.getFirst();
            int j = l.getLast();
            matrix[i][j] = 1;
            matrix[j][i] = 1;
        }
        return matrix;
    }


    public static int[][] matrixRepresentationOfWeightedGraph(List<List<Integer>> list, int nodes){
        int[][] matrix = new int[nodes+1][nodes+1];
        for(List<Integer> l: list){
            int i = l.getFirst();
            int j = l.get(1);
            int k = l.getLast();
            matrix[i][j] = k;
            matrix[j][i] = k;
        }
        return matrix;
    }


    public static List<List<Integer>> listRepresentation(List<List<Integer>> list, int nodes){
        List<List<Integer>> l = new ArrayList<>();
        for(int i = 0; i<=nodes; i++){
            l.add(new ArrayList<>());
        }
        for(List<Integer> temp: list){
            int i = temp.getFirst();
            int j = temp.getLast();
            l.get(i).add(j);
            l.get(j).add(i);
        }
        return l;
    }

    public static List<List<List<Integer>>> listRepresentationOfWeightedGraph(List<List<Integer>> list, int nodes){
        List<List<List<Integer>>> l = new ArrayList<>();
        for(int i = 0; i<=nodes; i++){
            l.add(new ArrayList<>());
        }
        for(List<Integer> temp: list){
            int i = temp.getFirst();
            int j = temp.get(1);
            int k = temp.getLast();
            l.get(i).add(Arrays.asList(j, k));
            l.get(j).add(Arrays.asList(i, k));
        }
        return l;
    }
}
