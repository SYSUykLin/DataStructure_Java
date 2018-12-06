package UnionFind;

public interface UnionFind {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
