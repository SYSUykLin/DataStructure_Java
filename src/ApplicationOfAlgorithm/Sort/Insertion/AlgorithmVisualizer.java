package ApplicationOfAlgorithm.Sort.Insertion;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 40;
    private InsertionSortData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight, int N) {

        data = new InsertionSortData(N, sceneHeight);
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
        setData(-1, -1);
        for (int i = 0; i < data.N(); i++) {
            setData(i, i);
            for (int j = i; j > 0 && data.get(j) < data.get(j - 1); j--) {
                data.swap(j, j - 1);
                setData(i+1, j-1);
            }
            setData(i, -1);
        }
        setData(data.N(), -1);
    }

    private void setData(int orderIndex, int currentIndex){
        data.orderIndex = orderIndex;
        data.currentIndex = currentIndex;
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
