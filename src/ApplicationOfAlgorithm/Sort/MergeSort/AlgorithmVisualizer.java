package ApplicationOfAlgorithm.Sort.MergeSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 40;
    private MergeData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight, int N) {
        data = new MergeData(N, sceneHeight);
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(title, sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void setData(int l, int r, int merge){
        data.l = l;
        data.r = r;
        data.mergeIndex = merge;
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private void run() {
        setData(-1, -1, -1 );
        Merge(0, data.N()-1);
        setData(0, data.N()-1, -1);
    }

    private void Merge(int l, int r) {
        if (l >= r) {
            return;
        }
        setData(l, r, -1);
        int mid = (l + r) / 2;
        Merge(l, mid);
        Merge(mid + 1, r);
        merge(l, r, mid);
    }

    private void merge(int l, int r, int mid) {
        int[] array = new int[r - l + 1];
        for (int i = l; i <= r; i++) {
            array[i - l] = data.get(i);
        }
        int i = l, j = mid + 1;
        int index = l;
        while (i <= mid && j <= r) {
            if (array[i - l] < array[j - l]) {
                data.set(index, array[i - l]);
                i++;
                index++;
            } else {
                data.set(index, array[j - l]);
                j++;
                index++;
            }
            setData(l, r, index);
        }
        if (i <= mid) {
            for (int k = i; k <= mid; k++) {
                data.set(index, array[k - l]);
                index++;
                setData(l, r, index);
            }
        } else if (j <= r) {
            for (int k = j; k <= r; k++) {
                data.set(index, array[k - l]);
                index++;
                setData(l, r, index);
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
