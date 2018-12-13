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
            return "" + key + (this.color == RED ? "R" : "B");
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

    private void setRed(Node<T> node){
        if (node != null){
            node.color = RED;
        }
    }

    private void setParent(Node<T> node, Node<T> parent){
        if (node != null){
            node.parent = parent;
        }
    }

    private void setColor(Node<T> node, boolean color){
        if (node != null){
            node.color = color;
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node<T> tree){
        if (tree != null){
            inOrder(tree.left);
            System.out.println(tree);
            inOrder(tree.right);
        }
    }

    public Node<T> search(T key){
        return search(root, key);
    }

    private Node<T> search(Node<T> node, T key){
        if (node == null){
            return null;
        }
        else if (key.compareTo(node.key) < 0){
            return search(node.left, key);
        }else if (key.compareTo(node.key) > 0){
            return search(node.right, key);
        }else {
            return node;
        }
    }
}
