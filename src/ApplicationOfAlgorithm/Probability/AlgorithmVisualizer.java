package ApplicationOfAlgorithm.Probability;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 20;
    private int[] money;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(String title , int sceneWidth, int sceneHeight) {
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }
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
        while (true){
            frame.render(money);
            AlgorithmHelper.pause(DELAY);
            for (int i = 0; i < money.length; i++) {
                if (money[i] > 0){
                    int j = (int) (Math.random() * money.length);
                    money[i] -= 1;
                    money[j] += 1;
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
