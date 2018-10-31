package List;



public class LinkNode {
    private int id;
    private LinkNode next;

    public int getId() {
        return id;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode node) {
        this.next = node;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LinkNode(int id){
        super();
        this.id = id;
        this.next = null;
    }

    public void printNode(){
        String s = "id = " + id + " ";
        if (next != null){
            s += ",next = " + next.getId() + " ";
        }
        System.out.print(s);
    }
}
