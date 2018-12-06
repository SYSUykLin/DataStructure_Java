package Array;


public class Array<E> {
    private E[] data;
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addLast(E e){
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    public void add(int index, E e){
        if (size == data.length){
            resize(2 * data.length);
        }
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index must be in range[0, size]");
        }
        for (int i = size-1; i >= index; i --) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    public void swap(int i, int j){
        if (i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("i or j is illgal!!!");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public E get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index must be in range[0, size]");
        }
        return data[index];
    }

    public void set(int index, E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index must be in range[0, size]");
        }
        data[index] = e;
    }

    public E remove(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index must be in range[0, size]");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i ++){
            data[i-1] = data[i];
        }
        size --;
        return ret;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        stringBuilder.append('[');
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }
}
