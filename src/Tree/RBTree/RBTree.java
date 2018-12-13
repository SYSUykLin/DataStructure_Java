package Tree.RBTree;

public class RBTree<T extends Comparable<T>> {
    private class Node<T extends Comparable<T>> {
        public boolean color;
        public T key;
        Node<T> left;
        Node<T> right;
        Node<T> parent;

        public Node(T key, boolean color, Node<T> parent, Node<T> left, Node<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            String s = "" + key + (this.color == RED ? "R" : "B");
            if (this.left != null) {
                s += ("  " + left.key);
            }
            if (this.right != null) {
                s += ("  " + right.key);
            }
            return s;
        }
    }

    private Node<T> root;
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        root = null;
    }

    private Node<T> parentOf(Node<T> node) {
        return node != null ? node.parent : null;
    }

    private boolean colorOf(Node<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(Node<T> node) {
        return ((node != null) && (node.color == RED));
    }

    private boolean isBlack(Node<T> node) {
        return !isRed(node);
    }

    private void setBlack(Node<T> node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    private void setRed(Node<T> node) {
        if (node != null) {
            node.color = RED;
        }
    }

    private void setParent(Node<T> node, Node<T> parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    private void setColor(Node<T> node, boolean color) {
        if (node != null) {
            node.color = color;
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.println(tree);
            inOrder(tree.right);
        }
    }

    public Node<T> search(T key) {
        return search(root, key);
    }

    private Node<T> search(Node<T> node, T key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            return search(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return search(node.right, key);
        } else {
            return node;
        }
    }

    public T minimum() {
        Node<T> p = minimum(root);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private Node<T> minimum(Node<T> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public T maximum() {
        Node<T> p = maximum(root);
        if (p != null) {
            return p.key;
        }
        return null;
    }

    private Node<T> maximum(Node<T> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /*
    find the successor of the current node,
    successor is the smallest over the node which bigger than current node
     */
    public Node<T> successor(Node<T> x) {
        if (x.right != null) {
            return minimum(x.right);
        }

        Node<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /*
    find the predecessor of the current node,
    predecessor is the biggest over the node which smaller than the current node.
     */
    public Node<T> predecessor(Node<T> x) {
        if (x.left != null) {
            return maximum(x.left);
        }
        Node<T> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }

        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node<T> y) {
        Node<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            if (y.parent.left == y) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        x.right = y;
        y.parent = x;
    }

    private void insertFixUp(Node<T> node) {
        Node<T> parent, grandParent;
        while ((parent = parentOf(node)) != null && isRed(parent)) {
            grandParent = parentOf(parent);
            if (parent == grandParent.left) {
                //third condition,the uncle is red
                Node<T> uncle = grandParent.right;
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                }
                //fifth condition,the uncle is black and left child of the current node
                if (parent.right == node) {
                    Node<T> temp;
                    leftRotate(parent);
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                //fourth condiction,same as above but the right child of the current node.
                setBlack(parent);
                setRed(grandParent);
                rightRotate(grandParent);
            } else {
                //third condition,the uncle is red.
                Node<T> uncle = grandParent.left;
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandParent);
                    node = grandParent;
                    continue;
                }
                //fifth condition,the uncle is black,left child
                if (parent.left == node) {
                    Node<T> temp;
                    rightRotate(parent);
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                //fourth condition,the uncle is black,right child
                setBlack(parent);
                setRed(grandParent);
                leftRotate(grandParent);
            }
        }
        setBlack(root);
    }

    private void remove(Node<T> node) {
        Node<T> child, parent;
        boolean color;
        if (node.left != null && node.right != null) {
            Node<T> replacer = node;
            replacer = minimum(replacer.right);
            if (parentOf(node) != null) {
                if (parentOf(node).left == node) {
                    parentOf(node).left = replacer;
                } else {
                    parentOf(node).right = replacer;
                }
            } else {
                this.root = replacer;
            }
            child = replacer.right;
            parent = parentOf(replacer);
            color = colorOf(replacer);
            if (parent == node) {
                parent = replacer;
            } else {
                if (child != null) {
                    setParent(child, parent);
                }
                parent.left = child;
                replacer.right = node.right;
                setParent(node.right, replacer);
            }
            replacer.parent = node.parent;
            replacer.color = node.color;
            replacer.left = node.left;
            node.left.parent = replacer;
            if (color == BLACK) {
                removeFixUp(replacer, parent);
            }
            node = null;
            return;
        }

        if (node.left != null) {
            child = node.left;
        } else {
            child = node.right;
        }
        parent = node.parent;
        color = node.color;
        if (child != null) {
            child.parent = parent;
        }
        if (parent != null) {
            if (parent.left == node) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            this.root = child;
        }

        setColor(child, color);
        if (color == BLACK) {
            removeFixUp(child, parent);
        }
        node = null;
    }

    public void remove(T key) {
        Node<T> node;

        if ((node = search(root, key)) != null)
            remove(node);
    }

    public void insert(T e) {
        Node<T> node = new Node<T>(e, RED, null, null, null);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }

    private void insert(Node<T> node) {
        int cmp;
        Node<T> y = null;
        Node<T> x = root;
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y != null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        } else {
            this.root = node;
        }

        node.color = RED;
        insertFixUp(node);
    }

    private void removeFixUp(Node<T> node, Node<T> par) {
        Node<T> uncle;
        Node<T> parent;
        parent = node == null ? par : node.parent;
        while ((node == null || isBlack(node)) && node != root) {
            if (parent.left == node) {
                uncle = parent.right;
                //the uncle is red, condition three
                if (isRed(uncle)) {
                    setBlack(uncle);
                    setRed(parent);
                    leftRotate(parent);
                    uncle = parent.right;
                }
                //the uncle and his child are all black
                if ((uncle.left == null || isBlack(uncle.left)) &&
                        (uncle.right == null || isBlack(uncle.right))) {
                    setRed(uncle);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    //the uncle is black and red of his child on the left
                    if (uncle.right == null || isBlack(uncle.right)) {
                        setBlack(uncle.left);
                        setRed(uncle);
                        rightRotate(uncle);
                        uncle = parent.right;
                    }
                    setColor(uncle, colorOf(parent));
                    setBlack(parent);
                    setBlack(uncle.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {
                uncle = parent.left;
                if (isRed(uncle)) {
                    setBlack(uncle);
                    setRed(parent);
                    rightRotate(parent);
                    uncle = parent.left;
                }
                if ((uncle.left == null || isBlack(uncle.left)) &&
                        (uncle.right == null || isBlack(uncle.right))) {
                    setRed(uncle);
                    node = parent;
                    parent = parentOf(node);
                } else {
                    if (uncle.left == null || isBlack(uncle.left)) {
                        setBlack(uncle.right);
                        setRed(uncle);
                        leftRotate(uncle);
                        uncle = parent.left;
                    }
                    setColor(uncle, colorOf(parent));
                    setBlack(parent);
                    setBlack(uncle.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }
        if (node != null) {
            setBlack(node);
        }
    }

    public static void main(String[] args) {
        RBTree<Integer> rbTree = new RBTree<>();
        int a[] = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int i = 0; i < a.length; i++) {
            rbTree.insert(a[i]);
        }
        rbTree.inOrder();
        rbTree.remove(30);
        rbTree.inOrder();
    }
}
