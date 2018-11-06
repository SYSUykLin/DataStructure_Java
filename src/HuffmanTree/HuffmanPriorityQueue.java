package HuffmanTree;

import Queue.PrioriityQueue;

import java.util.Random;

public class HuffmanPriorityQueue {
    private HuffmanNode [] queue;

    private int nItem;

    private int capacity;

    public HuffmanPriorityQueue(int count){
        queue = new HuffmanNode[count];
        nItem = 0;
        capacity = count;
    }

    public void insert(HuffmanNode data){
        if (nItem == 0){
            queue[nItem] = data;
            nItem ++;
        }else {
            int i = 0;
            for (i = nItem - 1; i >= 0; i--){
                if (data.getCount() > queue[i].getCount()){
                    queue[i+1] = queue[i];
                }else {
                    break;
                }
            }
            queue[i+1] = data;
            nItem ++;
        }
    }

    public HuffmanNode remove(){
        nItem --;
        HuffmanNode temp = queue[nItem];
        queue[nItem] = null;
        return temp;
    }

    public HuffmanNode peekFront(){
        HuffmanNode temp = queue[nItem-1];
        return temp;
    }

    public int size(){
        return nItem;
    }

    public static void main(String args[]){
        HuffmanPriorityQueue huffmanPriorityQueue = new HuffmanPriorityQueue(10);
        huffmanPriorityQueue.insert(new HuffmanNode('c', 20));
        huffmanPriorityQueue.insert(new HuffmanNode('a', 2));
        huffmanPriorityQueue.insert(new HuffmanNode('r', 13));
        huffmanPriorityQueue.insert(new HuffmanNode('w', 16));
        huffmanPriorityQueue.insert(new HuffmanNode('u', 26));
        for (int i = 0; i < 5; i++) {
            System.out.println(huffmanPriorityQueue.remove().getCount());
        }


    }
}
