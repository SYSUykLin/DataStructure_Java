package Graph.Version1.WeightTree;

import java.util.Vector;

public class GraphWithWeight {
    private int N;
    private int E;
    private Vector<Vector<Edge>> Graph;
    private boolean directed;

    public GraphWithWeight(int npoint, boolean diercted) {
        Graph = new Vector<>();
        this.N = npoint;
        for (int i = 0; i < N; i++) {
            Graph.addElement(new Vector<>());
        }
        this.directed = diercted;
        this.E = 0;
    }

    public void addEdge(int v, int w, int weight) {
        if (v < 0 || v >= N) {
            throw new IllegalArgumentException("v or w must be in range[0, N-1]!!!");
        }
        if (w < 0 || w >= N) {
            throw new IllegalArgumentException("v or w must be in range[0, N-1]!!!");
        }

        if (hasEdge(v, w)) {
            return;
        }

        Edge e = new Edge(v, w, weight);

        Graph.get(v).addElement(e);
        if (!directed) {
            e = new Edge(w, v, weight);
            Graph.get(w).addElement(e);
        }
        E++;

    }

    public boolean hasEdge(int v, int w) {
        for (int i = 0; i < Graph.get(v).size(); i++) {
            if (Graph.get(v).get(i).Other(v) == w) {
                return true;
            }
        }
        return false;
    }

    public int V() {
        return N;
    }

    public int E() {
        return E;
    }

    public void show(){
        System.out.println(Graph);
    }

    class adjIterator{
        private int s;
        private GraphWithWeight G;
        private int index;

        adjIterator(int v, GraphWithWeight Graph){
            index = v;
            G = Graph;
            index = 0;
        }

        Edge begin(){
            return G.Graph.get(s).get(index);
        }

        Edge next(){
            index ++;
            if (index < G.Graph.get(s).size()){
                return G.Graph.get(s).get(index);
            }
            return null;
        }

        boolean end(){
            return index >= G.Graph.get(s).size();
        }
    }
}
