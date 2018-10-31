package List;

public class SingleLinkList {
    private LinkNode firstNode;
    private int count;

    public SingleLinkList() {
        firstNode = null;
        count = 0;
    }

    public void insertFirst(int id) {
        LinkNode linkNode = new LinkNode(id);
        linkNode.setNext(firstNode);
        firstNode = linkNode;
        count++;
    }

    public void insertNode(int id, int index) {
        if (index < 0 || index > count + 1) {
            throw new IllegalArgumentException("index must be in range[0, N+1]");
        }
        LinkNode linkNode = new LinkNode(id);
        if (index == 1) {
            linkNode.setNext(firstNode);
            firstNode = linkNode;
            count++;
            return;
        }
        LinkNode p = firstNode, q = p;
        for (int i = 0; i < index - 1; i++) {
            q = p;
            p = p.getNext();
        }
        q.setNext(linkNode);
        linkNode.setNext(p);
        count++;
    }

    public LinkNode removeNode(int index) {
        LinkNode p = firstNode, q = p, a = p;
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("index must be in range[0, N]");
        }
        if (index == 1) {
            p = firstNode;
            firstNode = firstNode.getNext();
            count--;
            return p;
        }
        for (int i = 0; i < index - 1; i++) {
            a = p;
            p = p.getNext();
            q = p.getNext();
        }
        a.setNext(q);
        return p;

    }

    public LinkNode find(int id) {
        LinkNode linkNode = firstNode;
        while (linkNode != null){
            if (linkNode.getId() == id){
                return linkNode;
            }
            linkNode = linkNode.getNext();
        }
        return null;
    }

    public void display() {
        LinkNode linkNode = firstNode;
        while (linkNode != null) {
            linkNode.printNode();
            linkNode = linkNode.getNext();
        }
        System.out.println();
    }

    public int count(){
        return count;
    }


    public static void main(String args[]) {
        SingleLinkList singleLinkList = new SingleLinkList();
        for (int i = 0; i < 10; i++) {
            singleLinkList.insertFirst(i);
        }
        singleLinkList.display();
        singleLinkList.insertNode(15, 11);
        singleLinkList.removeNode(5);
        singleLinkList.display();
    }
}
