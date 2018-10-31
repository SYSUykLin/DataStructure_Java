package Graph.Version1;
public class DFS {
    private Graph graph;
    private int s;
    private boolean [] marked;
    public DFS(Graph g, int v){
        this.graph = g;
        this.s = v;
        marked = new boolean[g.V()];
        for (int i = 0; i < g.V(); i++) {
            marked[i] = false;
        }
        dfs(s);
    }


    public void dfs(int v){
        marked[v] = true;
        System.out.print(v + " ");
        Graph.adjIterator adjIterator;
        if (graph instanceof DenseGraph)
            adjIterator = new DenseGraph.adjIterator((DenseGraph) graph, v);
        else
            adjIterator = new SparseGraph.adjIterator((SparseGraph) graph, v);
        for (int w = adjIterator.begin(); !adjIterator.end() ; w = adjIterator.next()) {
            if (!marked[w])
                dfs(w);
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
        DFS dfs = new DFS(denseGraph, 0);
     }
}
