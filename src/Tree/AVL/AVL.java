package Tree.AVL;

public class AVL<K extends Comparable<K>, V> {
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public int height;
        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = right = null;
            height = 1;
        }
    }
    private Node root;
    private int size;
    public AVL(){
        root = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    private int getHeight(Node node){
        if (node == null){
            return 0;
        }else {
            return node.height;
        }
    }
    public void add(K key, V value){
        root = add(root, key, value);
    }
    public Node add(Node node, K key, V value){
        if (node == null){
            size ++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) > 0){
            node.right = add(node.right, key, value);
        }else if(key.compareTo(node.key) < 0){
            node.left = add(node.left, key, value);
        }else {
            node.value = value;
        }
    }
}
