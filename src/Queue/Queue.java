package Queue;

public class Queue {
    private int [] queue;
    private int front;
    private int end;
    private int nItem;

    public Queue(int count){
        front = 0;
        end = 0;
        nItem = count;
        queue = new int[count];
    }

    public void insert(int data){
        if ((end+1)%nItem == front){
            System.err.println("Queue is Full");
        }else {
            queue[end] = data;
            end = (end+1)%nItem;
        }
    }

    public void remove(){
        if ((end-front + nItem)%nItem == 0){
            System.err.println("Queue is empty!");
        }else {
            front = (front + 1)%nItem;
        }
    }

    public int peekFront(){
        return queue[front];
    }

    public boolean isEmpty(){
        return (end-front + nItem)%nItem == 0;
    }

    public boolean isFull(){
        return (++end)%nItem == front;
    }

    public int count(){
        return (end-front + nItem)%nItem;
    }

     public static void main(String args[]){
        Queue queue = new Queue(11);
        for (int i = 0;i < 10; i++){
            queue.insert(i);
        }
        System.out.println(queue.peekFront());
        queue.remove();
        System.out.println(queue.peekFront());
        queue.remove();
        queue.insert(13);
     }
}
