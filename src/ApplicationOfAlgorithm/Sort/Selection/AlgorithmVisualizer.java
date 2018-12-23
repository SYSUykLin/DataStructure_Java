package ApplicationOfAlgorithm.Sort.Selection;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 10;
    private SelectionSortData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight, int N) {

        data = new SelectionSortData(N, sceneHeight);
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
        data.setData(0,-1,-1);
        frame.render(data);
        AlgorithmHelper.pause(DELAY);

        for (int i = 0; i < data.N(); i++) {
            int midIndex = i;
            data.setData(i, -1, midIndex);
            frame.render(data);
            AlgorithmHelper.pause(DELAY);

            for (int j = i+1; j < data.N(); j++) {
                data.setData(i, j, midIndex);
                frame.render(data);
                AlgorithmHelper.pause(DELAY);

                if (data.get(j) < data.get(midIndex)){
                    midIndex = j;
                    data.setData(i, j, midIndex);
                    frame.render(data);
                    AlgorithmHelper.pause(DELAY);

                }
            }
            data.swap(i, midIndex);
            data.setData(i+1, -1, -1);
            frame.render(data);
            AlgorithmHelper.pause(DELAY);
        }
        data.setData(data.N(), -1,-1);
        frame.render(data);
        AlgorithmHelper.pause(DELAY);

    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
