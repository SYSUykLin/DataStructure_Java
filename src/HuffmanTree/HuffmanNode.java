package HuffmanTree;

public class HuffmanNode {
    private char c;
    private int count;
    private HuffmanNode leftNode;
    private HuffmanNode rightNode;

    public HuffmanNode(char c, int count){
        this.c = c;
        this.count = count;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public HuffmanNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HuffmanNode leftNode) {
        this.leftNode = leftNode;
    }

    public HuffmanNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HuffmanNode rightNode) {
        this.rightNode = rightNode;
    }
}
