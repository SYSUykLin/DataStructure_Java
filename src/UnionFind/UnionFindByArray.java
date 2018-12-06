package UnionFind;

public class UnionFindByArray implements UnionFind {

    private int[] id;

    public UnionFindByArray(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    private int find(int p){
        if (p < 0 || p > id.length){
            throw new IllegalArgumentException("p is out of range!");
        }
        return id[p];
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = id[p];
        int qID = id[q];
        if (pID == qID){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID){
                id[i] = qID;
            }
        }
    }
}
