package Queue;

import java.util.Random;

public class PrioriityQueue {

    private int [] queue;

    private int nItem;

    private int capacity;

    public PrioriityQueue(int count){
        queue = new int[count];
        nItem = 0;
        capacity = count;
    }

    public void insert(int data){
        if (nItem == 0){
            queue[nItem] = data;
            nItem ++;
        }else {
            int i = 0;
            for (i = nItem - 1; i >= 0; i--){
                if (data > queue[i]){
                    queue[i+1] = queue[i];
                }else {
                    break;
                }
            }
            queue[i+1] = data;
            nItem ++;
        }
    }

    public int remove(){
        int temp = queue[0];
        for (int i = 0; i < nItem-2; i++){
            queue[i] = queue[i+1];
        }
        nItem --;
        return temp;
    }

    public int peekFront(){
        if (nItem == 0){
            System.err.println("PriorityQueue is empty!!!");
        }
        int temp = queue[0];
        return temp;
    }

     public static void main(String args[]){
        PrioriityQueue prioriityQueue = new PrioriityQueue(10);
        Random random = new Random();
        for (int i = 0; i < 8; i++){
            int num = random.nextInt();
            prioriityQueue.insert(num);
        }

         for (int i = 0; i < 8; i++) {
             System.out.println(prioriityQueue.remove());
         }
     }

}
