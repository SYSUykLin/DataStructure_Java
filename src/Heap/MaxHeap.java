package Heap;

import Array.Array;

import java.util.Random;

public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    public MaxHeap() {
        data = new Array<E>();
    }

    public MaxHeap(E[] data){
        this.data = new Array(data);
        for (int i = parent(this.data.getSize() - 1); i >= 0; i --){
            shiftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("No parents when index equal zero!");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }


    private void shiftUp(int index) {
        while (index > 0 && data.get(index).compareTo(data.get(parent(index))) > 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public void add(E e) {
        data.addLast(e);
        shiftUp(data.getSize() - 1);
    }

    public E getMax(){
        return data.get(0);
    }

    public E extractMax() {
        E maxEle = data.getFirst();
        data.swap(0, data.getSize() - 1);
        data.remove(data.getSize() - 1);
        shiftDown(0);
        return maxEle;
    }

    private void shiftDown(int index) {
        while (leftChild(index) < data.getSize()) {
            int j = leftChild(index);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(index);
            }
            if (data.get(index).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(index, j);
            index = j;
        }
    }

    public E replace(E e) {
        E max = data.get(0);
        data.set(0, e);
        shiftDown(0);
        return max;
    }


    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int nu = random.nextInt();
            System.out.print(nu + " ");
            maxHeap.add(nu);
        }
        System.out.println();
        for (int i = 0; i < 20; i++) {
            System.out.print(maxHeap.extractMax() + " ");
        }

        //test for heapify
        Integer[] arr = {1,4,12,6,4,6,3,2};
        maxHeap = new MaxHeap<Integer>(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(maxHeap.extractMax() + " ");
        }
    }
}
