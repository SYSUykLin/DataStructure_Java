package ApplicationOfAlgorithm.Sort.Insertion;

public class InsertionSortData {
    private int[] numbers;
    public int orderIndex = -1;
    public int currentIndex = -1;

    public InsertionSortData(int N, int randomBound) {
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

    public void swap(int i, int j){
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }


}

