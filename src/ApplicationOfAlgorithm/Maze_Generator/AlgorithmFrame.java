package ApplicationOfAlgorithm.Maze_Generator;

import javax.swing.*;
import java.awt.*;

public class AlgorithmFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgorithmCanvas algorithmCanvas = new AlgorithmCanvas();
        setContentPane(algorithmCanvas);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private MazeData data;

    public void render(MazeData data) {
        this.data = data;
        repaint();
    }


    public AlgorithmFrame(String title) {

        this(title, 1024, 768);
    }

    private class AlgorithmCanvas extends JPanel {
        public AlgorithmCanvas() {
            super(true);
        }

        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics2D.addRenderingHints(hints);

            int w = canvasWidth / data.getM();
            int h = canvasHeight / data.getN();
            for (int i = 0; i < data.getN(); i++) {
                for (int j = 0; j < data.getM(); j++) {
                    if (data.inMist[i][j]){
                        AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Black);
                    }
                    else if (data.maze[i][j] == MazeData.WALL) {
                        AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Red);
                    } else {
                        AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.White);
                    }
                    AlgorithmHelper.fillRectangle(graphics2D, j * w, i * h, w, h);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
