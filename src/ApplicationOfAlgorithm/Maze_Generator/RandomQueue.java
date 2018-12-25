package ApplicationOfAlgorithm.Maze_Generator;

import java.util.ArrayList;
import java.util.LinkedList;

public class RandomQueue<E> {
    private LinkedList<E> queue;

    public RandomQueue() {
        queue = new LinkedList<E>();
    }

    public void add(E e) {
        if (Math.random() < 0.5){
            queue.addFirst(e);
        }else {
            queue.addLast(e);
        }
    }

    public E remove() {
        if (queue.size() == 0) {
            throw new IllegalArgumentException("no elements!");
        }
//        int randomIndex = (int) (Math.random() * queue.size());
//        E Ele = queue.get(randomIndex);
//        queue.set(randomIndex, queue.get(queue.size() - 1));
//        queue.remove(queue.size() - 1);
//        return Ele;
        if (Math.random() < 0.5){
            return queue.removeFirst();
        }else {
            return queue.removeLast();
        }
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }
}
