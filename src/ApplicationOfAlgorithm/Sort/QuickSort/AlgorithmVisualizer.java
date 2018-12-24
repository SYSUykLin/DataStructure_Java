package ApplicationOfAlgorithm.Sort.QuickSort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 40;
    private QuickSortData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight, int N) {

        data = new QuickSortData(N, sceneHeight);
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(title, sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData(-1, -1, -1);
        QuickSort(0, data.N() - 1);
        setData(0, data.N() - 1, -1);
    }

    private void QuickSort(int l, int r) {
        if (l >= r) {
            return;
        }
        setData(l, r, -1);
        int mid = partition(l, r);
        QuickSort(l, mid - 1);
        QuickSort(mid + 1, r);
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private int partition(int l, int r) {
        int v = data.get(l);
        int i = l + 1;
        int j = r;
        setData(l, r, l);
        while (true) {
            while (i <= r && data.get(i) < v) {
                i++;
            }
            while (j >= l + 1 && data.get(j) > v) {
                j--;
            }
            if (i > j) {
                break;
            }
            data.swap(i, j);
            setData(l, r, l);
            i++;
            j--;
        }
        data.swap(j, l);
        setData(l, r, j);
        return j;
    }

    private void setData(int l, int r, int index) {
        data.l = l;
        data.r = r;
        data.Index = index;
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
