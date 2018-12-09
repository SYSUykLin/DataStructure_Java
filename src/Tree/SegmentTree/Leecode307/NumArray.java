package Tree.SegmentTree.Leecode307;

public class NumArray {
    private SegementTree segementTree;

    public NumArray(int[] nums) {
        segementTree = new SegementTree(nums);
    }


    public void update(int i, int val) {
        segementTree.set(i, val);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        // System.out.println(numArray.sumRange(0, 2));
    }

    public int sumRange(int i, int j) {
        if (segementTree == null) {
            return 0;
        }
        return segementTree.query(i, j);
    }
}

class SegementTree {
    int[] data;
    int[] tree;

    public SegementTree(int[] nums) {
        data = new int[nums.length];
        tree = new int[4 * nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        buildSegmentTree();
    }

    public void buildSegmentTree() {
        if (data.length == 0) {
            return;
        }
        buildSegmentTree(0, 0, data.length - 1);
//        for (int i = 0; i < tree.length; i++) {
//            System.out.print(tree[i] + " ");
//        }
//        System.out.println();
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l >= r) {
            tree[treeIndex] = data[l];
            return;
        }
        int mid = l + (r - l) / 2;
        int left = leftChild(treeIndex);
        int right = rightChild(treeIndex);
        buildSegmentTree(left, l, mid);
        buildSegmentTree(right, mid + 1, r);
        tree[treeIndex] = tree[left] + tree[right];
    }

    public int query(int queryL, int queryR) {
        if (queryL > queryR) {
            throw new IllegalArgumentException("Error!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    private int query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        } else {
            int mid = l + (r - l) / 2;
            int left = leftChild(treeIndex);
            int right = rightChild(treeIndex);
            if (queryL >= mid + 1) {
                return query(right, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(left, l, mid, queryL, queryR);
            } else {
                return query(left, l, mid, queryL, mid) + query(right, mid + 1, r, mid + 1, queryR);
            }
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    public void set(int index, int e) {
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, int e) {
        if (l == r) {
            tree[treeIndex] = e;
        } else {
            int mid = l + (r - l) / 2;
            int left = leftChild(treeIndex);
            int right = rightChild(treeIndex);
            if (index > mid) {
                set(right, mid + 1, r, index, e);
            } else {
                set(left, l, mid, index, e);
            }
            tree[treeIndex] = tree[left] + tree[right];
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */