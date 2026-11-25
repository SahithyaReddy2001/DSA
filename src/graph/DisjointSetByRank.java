package graph;

import java.util.List;

public class DisjointSetByRank {
    public static int findUltimateParent(int n, int[] arr){
        if(n == arr[n]) return n;
        arr[n] = findUltimateParent(arr[n], arr);
        return arr[n];
    }

    public static void unionByRank(List<List<Integer>> list, int v){
        int[] rank = new int[v+1];
        int[] parent = new int[v+1];
        for(int i=0; i<v+1; i++){
            parent[i] = i;
        }

        for(List<Integer> l: list){
            int n1 = l.getFirst();
            int n2 = l.getLast();

            int upn1 = findUltimateParent(n1, parent);
            int upn2 = findUltimateParent(n2, parent);
            if(upn1 == upn2) continue;

            int rn1 = rank[upn1];
            int rn2 = rank[upn2];

            if(rn1 < rn2){
                parent[upn1] = upn2;
            } else if (rn2 < rn1) {
                parent[upn2] = upn1;
            }else{
                parent[upn1] = upn2;
                rank[upn2]++;
            }
        }
    }
}
