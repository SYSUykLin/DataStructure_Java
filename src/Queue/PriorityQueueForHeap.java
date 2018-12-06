package Queue;

import Heap.MaxHeap;

public class PriorityQueueForHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueueForHeap(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.getMax();
    }
}
