package ApplicationOfAlgorithm.Mine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgorithmVisualizer {
    public static int blockSize = 32;
    private int DELAY = 40;
    private MineSweeperData data;
    private AlgorithmFrame frame;

    public AlgorithmVisualizer(int N, int M, int number) {
        data = new MineSweeperData(N, M, number);
        int sceneWidth = M * blockSize;
        int sceneHeight = N * blockSize;
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("MineSweeper", sceneWidth, sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());
            frame.addKeyListener(new AlgoKeyListener());
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
        setData(false, -1, -1);
    }

    private void setData(boolean isLeft, int x, int y) {
        if (data.inArea(x, y)) {
            if (isLeft) {
                if (data.isMine(x, y)) {
                    System.out.println("Game Over!");
                    JOptionPane.showMessageDialog(frame, "Loss", "Alert", JOptionPane.ERROR_MESSAGE);
                } else {
                    data.open(x, y);
                }
                data.open[x][y] = true;
            } else {
                data.flags[x][y] = !data.flags[x][y];
            }
        }
        if (data.isWin()){
            JOptionPane.showMessageDialog(frame, "Win", "Alert", JOptionPane.DEFAULT_OPTION);
        }
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {
            event.translatePoint(-(int) (frame.getBounds().width - frame.getCanvasWidth()), -(int) (frame.getBounds().height - frame.getCanvasHeight()));
            Point point = event.getPoint();
            int w = frame.getCanvasWidth() / data.getM();
            int h = frame.getCanvasHeight() / data.getN();
            int x = point.y / h;
            int y = point.x / w;
            if (SwingUtilities.isLeftMouseButton(event)) {
                setData(true, x, y);
            } else if (SwingUtilities.isRightMouseButton(event)) {
                setData(false, x, y);
            }
        }

    }
}
