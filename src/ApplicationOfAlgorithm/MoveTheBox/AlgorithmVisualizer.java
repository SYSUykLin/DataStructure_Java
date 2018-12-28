package ApplicationOfAlgorithm.MoveTheBox;

import ApplicationOfAlgorithm.MoveTheBox.Entity.BackGroundData;
import ApplicationOfAlgorithm.MoveTheBox.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class AlgorithmVisualizer {
    private int DELAY = 40;
    private BackGroundData data;
    private AlgorithmFrame frame;
    private int round = 0;
    private int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    private User user;

    public AlgorithmVisualizer(String url) {
        data = new BackGroundData(url);
        int sceneWidth = 30 * BackGroundData.height;
        int sceneHeight = 30 * BackGroundData.width;
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(url, sceneWidth, sceneHeight);
            user = new User(data, frame);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
//        setData();
//        user.play(data.getBoy().getX(), data.getBoy().getY());
        while (true) {
            setData();
//            user.play();
            if (data.isWin()){
                JOptionPane.showMessageDialog(frame, "Win", "Alert", JOptionPane.DEFAULT_OPTION);
                String a = "map" + (++round)%4 + ".txt";
                data = new BackGroundData(a);
                int sceneWidth = 30 * BackGroundData.height;
                int sceneHeight = 30 * BackGroundData.width;
                frame.setVisible(false);
                frame = new AlgorithmFrame(a, sceneWidth, sceneHeight);
                frame.addKeyListener(new AlgoKeyListener());
                frame.addMouseListener(new AlgoMouseListener());
            }
        }
    }

    private void setData() {
        frame.render(data);
        AlgorithmHelper.pause(DELAY);

    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'a') {
                data.move(direction[0], BackGroundData.LEFT, data.getBoy());
            } else if (e.getKeyChar() == 's') {
                data.move(direction[1], BackGroundData.DOWN, data.getBoy());
            } else if (e.getKeyChar() == 'd') {
                data.move(direction[2], BackGroundData.RIGHT, data.getBoy());
            } else if (e.getKeyChar() == 'w') {
                data.move(direction[3], BackGroundData.UP, data.getBoy());
            }else if (e.getKeyChar() == ' '){
                data = new BackGroundData("map" + round + ".txt");
                int sceneWidth = 30 * BackGroundData.height;
                int sceneHeight = 30 * BackGroundData.width;
                frame.setVisible(false);
                frame = new AlgorithmFrame("map" + round + ".txt", sceneWidth, sceneHeight);
                frame.render(data);
                frame.addKeyListener(new AlgoKeyListener());
                frame.addMouseListener(new AlgoMouseListener());
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
