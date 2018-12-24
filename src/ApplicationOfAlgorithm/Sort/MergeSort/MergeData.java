package ApplicationOfAlgorithm.Sort.MergeSort;

public class MergeData {
    private int[] numbers;
    public int l, r;
    public int mergeIndex;

    public MergeData(int N, int randomBound) {
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = (int) (Math.random() * randomBound) + 1;
            //System.out.println(numbers[i]);
        }
    }


    public int N(){
        return numbers.length;
    }

    public int get(int index){
        if (index < 0 || index >= numbers.length){
            throw new IllegalArgumentException("index is illgel!");
        }
        return numbers[index];
    }

    public void set(int index, int num){
        if (index < 0 || index >= numbers.length){
            throw new IllegalArgumentException("index is illgel!");
        }
        numbers[index] = num;
    }

    public void swap(int i, int j){
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }

}
