package Graph.Version1;

public interface Graph {
    void addEdge(int v, int w);
    boolean hasEdge(int v, int w);
    int V();
    int E();
    void show();
    interface adjIterator{
        int begin();
        int next();
        boolean end();
    }
}
