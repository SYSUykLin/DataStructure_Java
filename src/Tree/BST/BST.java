package Tree.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BST<E extends Comparable<E>> {
    /**
     * Binary Search Tree
     */

    private class Node {
        public E e;
        public Node left, right;
        public boolean isleft = false;
        public boolean isright = false;

        public Node(E e) {
            this.e = e;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    public boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        } else if (e.equals(node.e)) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    public void lastOrder() {
        lastOrder(root);
        System.out.println();
    }

    private void lastOrder(Node node) {
        if (node == null) {
            return;
        }
        lastOrder(node.left);
        lastOrder(node.right);
        System.out.print(node.e + " ");
    }

    public void levelOrder(){
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()){
            Node node = nodes.remove();
            System.out.print(node.e + " ");
            if (node.left != null){
                nodes.add(node.left);
            }
            if (node.right != null){
                nodes.add(node.right);
            }
        }
        System.out.println();
    }

    public void preOrderNonRecur() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.e + " ");
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    public void inOrderNonRecur() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                Node node1 = stack.pop();
                System.out.print(node1.e + " ");
                node = node1.right;
            }
        }
        System.out.println();

    }

    public void lastOrderNonRecur() {
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                Node node1 = stack.peek();
                if (!node1.isright) {
                    node1.isright = true;
                    node = node1.right;
                } else {
                    node = stack.pop();
                    System.out.print(node.e + " ");
                    node = null;
                }
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.add(28);
        bst.add(16);
        bst.add(30);
        bst.add(13);
        bst.add(22);
        bst.add(29);
        bst.add(42);
        bst.preOrder();
        bst.preOrderNonRecur();
        bst.inOrder();
        bst.inOrderNonRecur();
        bst.lastOrder();
        bst.lastOrderNonRecur();
        bst.levelOrder();
    }

}
