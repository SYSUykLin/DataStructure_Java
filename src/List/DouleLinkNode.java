package List;

public class DouleLinkNode {
    private int id;
    private DouleLinkNode Previous;
    private DouleLinkNode Next;

    public DouleLinkNode(int id){
        this.id = id;
        Previous = null;
        Next = null;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public DouleLinkNode getPrevious() {
        return Previous;
    }

    public void setPrevious(DouleLinkNode previous) {
        Previous = previous;
    }

    public DouleLinkNode getNext() {
        return Next;
    }

    public void setNext(DouleLinkNode next) {
        Next = next;
    }


    public void printNode(){
        String s = "";
        if (Previous != null){
            s += ",previous = " + Previous.getId() + " ";
        }
        s += "id = " + id + " ";
        if (Next != null){
            s += ",next = " + Next.getId() + " ";
        }


        System.out.print(s);
    }

    @Override
    public String toString() {
        return "DouleLinkNode{" +
                "id=" + id +
                ", Previous=" + Previous +
                ", Next=" + Next +
                "}";
    }
}
