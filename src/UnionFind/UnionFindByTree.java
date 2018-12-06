package UnionFind;

public class UnionFindByTree implements UnionFind {

    private int[] parent;
    private int[] sz;
    private int[] rank;

    public UnionFindByTree(int size) {
        parent = new int[size];
        sz = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
            rank[i] = 1;
        }
    }


    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of range!");
        }
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else {
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }

//        if (sz[pRoot] < sz[qRoot]) {
//            parent[pRoot] = qRoot;
//            sz[qRoot] += sz[pRoot];
//        }else {
//            parent[qRoot] = pRoot;
//            sz[pRoot] += sz[qRoot];
//        }
    }
}
