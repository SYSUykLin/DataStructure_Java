package Graph.Version1;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BFS {
    private Graph graph;
    private int s;
    private boolean [] marked;

    public BFS(Graph graph, int s){
        this.graph = graph;
        this.s = s;

        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            marked[i] = false;
        }

        Queue<Integer> queue = new LinkedBlockingQueue<>();
        queue.clear();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()){
            int v = queue.remove();
            System.out.print(v + " ");
            Graph.adjIterator adjIterator;
            if (graph instanceof DenseGraph)
                adjIterator = new DenseGraph.adjIterator((DenseGraph) graph, v);
            else
                adjIterator = new SparseGraph.adjIterator((SparseGraph) graph, v);
            for (int w = adjIterator.begin(); !adjIterator.end() ; w = adjIterator.next()) {
                if (!marked[w]){
                    queue.add(w);
                    marked[w] = true;
                }
            }
        }

    }

     public static void main(String args[]){
         SparseGraph denseGraph = new SparseGraph(7, false);
         denseGraph.addEdge(0, 1);
         denseGraph.addEdge(0, 2);
         denseGraph.addEdge(0, 6);
         denseGraph.addEdge(0, 5);
         denseGraph.addEdge(5, 3);
         denseGraph.addEdge(5, 4);
         denseGraph.addEdge(3, 4);
         denseGraph.addEdge(4, 6);
         BFS bfs = new BFS(denseGraph, 0);
     }
}
