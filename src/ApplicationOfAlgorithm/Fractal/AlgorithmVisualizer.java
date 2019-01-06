package ApplicationOfAlgorithm.Fractal;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 40;
    private FractalData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int sceneWidth, int sceneHeight, int maxDepth, double splitAngle){
        data = new FractalData(maxDepth, splitAngle);
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Tree", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });

    }

    public AlgorithmVisualizer(int maxDepth, int side) {
        data = new FractalData(maxDepth);
        //int sceneWidth = (int) Math.pow(2, maxDepth);
        //int sceneHeight = (int) Math.pow(2, maxDepth);
        //int sceneWidth = (int) Math.pow(3, maxDepth);
        //int sceneHeight = (int) Math.pow(3, maxDepth);

        int sceneWidth = 3*side;
        int sceneHeight = side;
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Fractal", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });

    }

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight) {

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
        setData(data.getDepth());
    }

    public void setData(int depth) {
        data.setDepth(depth);
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() >= '0' &&
                    keyEvent.getKeyChar() <= '9') {
                int depth = keyEvent.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
