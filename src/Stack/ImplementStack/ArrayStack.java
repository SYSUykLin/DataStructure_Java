package Stack.ImplementStack;

import Array.Array;

public class ArrayStack<E> implements Stack<E> {
    Array<E> array;
    public ArrayStack(int capacity){
        array = new Array<E>(capacity);
    }
    public ArrayStack(){
        array = new Array<E>();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.remove(array.getSize()-1);
    }

    @Override
    public E peek() {
        return array.getLast();
    }
}
