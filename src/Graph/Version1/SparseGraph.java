package Graph.Version1;

import java.util.Random;
import java.util.Vector;

//use matrix to store
public class SparseGraph implements Graph {
    private int N;
    private int E;
    private Vector<Vector<Integer>> Graph;
    private boolean directed;

    public SparseGraph(int npoint, boolean directed) {
        this.N = npoint;
        this.directed = directed;
        Graph = new Vector<>();
        E = 0;
        for (int i = 0; i < N; i++) {
            Graph.addElement(new Vector<>());
            for (int j = 0; j < N; j++) {
                Graph.get(i).addElement(0);
            }
        }
    }

    public void addEdge(int v, int w){
        if (hasEdge(v, w)){
            return;
        }
        Graph.get(v).set(w, 1);
        if (!directed){
            Graph.get(w).set(v, 1);
        }
        E++;
    }

    public int V(){
        return N;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        return Graph.get(v).get(w) == 1;
    }

    public void show(){
        System.out.println(Graph);
    }

    class adjIterator{
        private int index;
        private int s;
        private SparseGraph G;
        public adjIterator(SparseGraph graph, int v){
            this.G = graph;
            this.s = v;
            this.index = -1;
        }

        public int begin(){
            return next();
        }

        public int next(){
            for (index ++; index <G.Graph.get(s).size(); index++){
                if (G.Graph.get(s).get(index) == 1){
                    return index;
                }
            }
            return -1;
        }

        public boolean end(){
            return index >= G.Graph.get(s).size();
        }
    }

    public static void main(String args[]) {
        SparseGraph sparseGraph = new SparseGraph(10, false);
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            int v = random.nextInt(10);
            int w = random.nextInt(10);
            sparseGraph.addEdge(v, w);
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            SparseGraph.adjIterator adjInterator = sparseGraph.new adjIterator(sparseGraph, i);
            for (int w = adjInterator.begin(); !adjInterator.end(); w = adjInterator.next()){
                System.out.print(w + " ");
            }
            System.out.println();
        }

    }
}
