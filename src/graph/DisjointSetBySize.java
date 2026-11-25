package graph;

import java.util.List;

public class DisjointSetBySize {
    public static int findUltimateParent(int n, int[] arr){
        if(n == arr[n]) return n;
        arr[n] = findUltimateParent(arr[n], arr);
        return arr[n];
    }

    public static void unionByRank(List<List<Integer>> list, int v){
        int[] size = new int[v+1];
        int[] parent = new int[v+1];
        for(int i=0; i<v+1; i++){
            parent[i] = i;
            size[i] = 1;
        }

        for(List<Integer> l: list){
            int n1 = l.getFirst();
            int n2 = l.getLast();

            int upn1 = findUltimateParent(n1, parent);
            int upn2 = findUltimateParent(n2, parent);
            if(upn1 == upn2) continue;

            int sn1 = size[upn1];
            int sn2 = size[upn2];

            if(sn1 < sn2){
                parent[upn1] = upn2;
                size[upn2] += size[upn1];
            }else{
                parent[upn2] = upn1;
                size[upn1] += size[upn2];
            }
        }
    }
}
