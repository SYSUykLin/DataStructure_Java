package Graph.Version1.WeightTree;

import Graph.Version1.Graph;

public class Edge {
    private int a;
    private int b;
    private double weight;

    public Edge(int v, int w, int weight){
        a = v;
        b = w;
        this.weight = weight;
    }

    public int Other(int v){
        if (v != a && v != b){
            throw new IllegalArgumentException("v mush be exited!!!");
        }
        return v == a ? b : a;
    }


}
