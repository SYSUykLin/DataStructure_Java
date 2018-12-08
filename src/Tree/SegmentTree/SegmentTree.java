package Tree.SegmentTree;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l >= r) {
            tree[treeIndex] = data[l];
            return;
        } else {
            int leftTreeIndex = leftChild(treeIndex);
            int rightTreeIndex = rightChild(treeIndex);
            int mid = l + (r - l) / 2;
            buildSegmentTree(leftTreeIndex, l, mid);
            buildSegmentTree(rightTreeIndex, mid + 1, r);
            tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);
        }
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illgel!");
        }
        return data[index];
    }

    public E getFromTree(int index) {
        if (index < 0 || index >= 4 * data.length) {
            throw new IllegalArgumentException("Index is illgel!");
        }
        return tree[index];
    }

    public int getSize() {
        return data.length;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("permater is illgel!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int left = leftChild(treeIndex);
        int right = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return query(right, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(left, l, mid, queryL, queryR);
        } else {
            return merger.merger(query(left, l, mid, queryL, mid), query(right, mid + 1, r, mid + 1, queryR));
        }
    }

    public void show() {
        for (int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(arr, (a, b) -> a + b);
        segmentTree.show();
        System.out.println(segmentTree.query(2, 5));
    }
}
