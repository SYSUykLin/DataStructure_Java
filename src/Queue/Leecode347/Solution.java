package Queue.Leecode347;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {

    private class Freq implements Comparable<Freq>{

        int e, freq;

        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq){
                return -1;
            }
            else if (this.freq > another.freq){
                return 1;
            }else {
                return 0;
            }
        }

    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for(int key: map.keySet()){
            if(priorityQueue.size() < k)
                priorityQueue.add(new Freq(key, map.get(key)));
            else if(map.get(key) > priorityQueue.peek().freq){
                priorityQueue.remove();
                priorityQueue.add(new Freq(key, map.get(key)));
            }
        }
        List<Integer> linkedList = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            linkedList.add(priorityQueue.remove().e);
        }
        return linkedList;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        Solution solution = new Solution();
        System.out.println(solution.topKFrequent(nums, 2));
    }

}