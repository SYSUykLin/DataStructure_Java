package List;

import java.util.function.DoublePredicate;

public class FirstLastList {
    private int count;
    private DouleLinkNode firstNode;

    public FirstLastList() {
        count = 0;
        firstNode = null;
    }

    public void insertFirst(int id) {
        DouleLinkNode douleLinkNode = new DouleLinkNode(id);
        DouleLinkNode p = firstNode;
        if (p != null)
            p.setPrevious(douleLinkNode);
        douleLinkNode.setNext(firstNode);
        firstNode = douleLinkNode;
        count++;
    }

    public void insetNode(int id, int index) {
        if (index < 0 || index > count + 1) {
            throw new IllegalArgumentException("index must be in range[0, N+1]");
        }
        if (index == 1) {
            insertFirst(id);
            count++;
            return;
        }
        DouleLinkNode douleLinkNode = new DouleLinkNode(id);
        DouleLinkNode p, q;
        p = q = firstNode;
        for (int i = 0; i < index - 1; i++) {
            q = p;
            p = p.getNext();
        }
        douleLinkNode.setNext(p);
        q.setNext(douleLinkNode);
        douleLinkNode.setPrevious(q);
        count++;
    }

    public DouleLinkNode removeNode(int index) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("index must be in range[0, N]");
        }
        DouleLinkNode p = firstNode;

        if (index == 1) {
            firstNode = firstNode.getNext();
            firstNode.setPrevious(null);
            count--;
            return p;
        }

        for (int i = 0; i < index - 1; i++) {
            p = p.getNext();
        }
        DouleLinkNode q, l;
        q = p.getPrevious();
        l = p.getNext();
        q.setNext(l);
        l.setPrevious(q);
        count--;
        return p;
    }

    public void display() {
        DouleLinkNode linkNode = firstNode;
        while (linkNode != null) {
            linkNode.printNode();
            linkNode = linkNode.getNext();
        }
        System.out.println();
    }

    public static void main(String args[]) {
        FirstLastList singleLinkList = new FirstLastList();
        for (int i = 0; i < 10; i++) {
            singleLinkList.insertFirst(i);
        }
        singleLinkList.display();
        singleLinkList.insetNode(15, 5);
        singleLinkList.removeNode(1);
        singleLinkList.display();
    }
}
