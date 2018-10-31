package Graph.Version1;

import java.util.Random;
import java.util.Vector;
//use list to store
public class DenseGraph implements Graph {
    private int N;
    private int E;
    private Vector<Vector<Integer>> Graph;
    private boolean directed;

    public DenseGraph(int npoint, boolean diercted) {
        Graph = new Vector<>();
        this.N = npoint;
        for (int i = 0; i < N; i++) {
            Graph.addElement(new Vector<>());
        }
        this.directed = diercted;
        this.E = 0;
    }

    public void addEdge(int v, int w) {
        if (v < 0 || v >= N) {
            throw new IllegalArgumentException("v or w must be in range[0, N-1]!!!");
        }
        if (w < 0 || w >= N) {
            throw new IllegalArgumentException("v or w must be in range[0, N-1]!!!");
        }

        if (hasEdge(v, w)) {
            return;
        }

        Graph.get(v).addElement(w);
        if (!directed) {
            Graph.get(w).addElement(v);
        }
        E++;

    }

    public boolean hasEdge(int v, int w) {
        for (int i = 0; i < Graph.get(v).size(); i++) {
            if (Graph.get(v).get(i) == w) {
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

    public void show() {
        System.out.println(Graph);
    }

    class adjIterator {
        private int s;
        private DenseGraph G;
        private int index;

        public adjIterator(DenseGraph graph, int v) {
            this.s = v;
            G = graph;
        }

        public int begin() {
            if (!G.Graph.get(s).isEmpty()) {
                return G.Graph.get(s).get(index);
            }
            return -1;
        }

        public int next() {
            index++;
            if (index < G.Graph.get(s).size()) {
                return G.Graph.get(s).get(index);
            }
            return -1;
        }

        public boolean end() {
            return index >= G.Graph.get(s).size();
        }
    }

    public static void main(String args[]) {
        Random random = new Random();
        DenseGraph denseGraph = new DenseGraph(10, false);
        for (int i = 0; i < 15; i++) {
            int v = random.nextInt(10);
            int w = random.nextInt(10);
            denseGraph.addEdge(v, w);
        }
        denseGraph.show();
        for (int i = 0; i < 10; i++) {
            System.out.print(i + ": ");
            DenseGraph.adjIterator adjInterator = denseGraph.new adjIterator(denseGraph, i);
            for (int w = adjInterator.begin(); !adjInterator.end(); w = adjInterator.next()){
                System.out.print(w + " ");
            }
            System.out.println();
        }
    }
}
