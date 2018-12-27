package ApplicationOfAlgorithm.MoveTheBox;

import ApplicationOfAlgorithm.MoveTheBox.Entity.BackGroundData;

import javax.swing.*;
import java.awt.*;

public class AlgorithmFrame extends JFrame {
    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    private int canvasWidth;
    private int canvasHeight;

    public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        AlgorithmCanvas algorithmCanvas = new AlgorithmCanvas();
        setContentPane(algorithmCanvas);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private BackGroundData data;

    public void render(BackGroundData data) {
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
                    if (data.map[i][j] == BackGroundData.BOX ||
                            data.map[i][j] == BackGroundData.BOX_TARGET) {
                        AlgorithmHelper.putImage(graphics2D, j * w, i * h, data.getBox(i, j).getPictureURL());
                    } else if (data.map[i][j] == BackGroundData.TARGET) {
                        AlgorithmHelper.putImage(graphics2D, j * w, i * h, data.getTarget(i, j).getPictureURL());
                    } else if (data.map[i][j] == BackGroundData.GLASS) {
                        AlgorithmHelper.putImage(graphics2D, j * w, i * h, BackGroundData.glassURL);
                    } else if (data.map[i][j] == BackGroundData.HOME) {
                        AlgorithmHelper.putImage(graphics2D, j * w, i * h, BackGroundData.homeURL);
                    } else if (data.map[i][j] == BackGroundData.Boy ||
                            data.map[i][j] == BackGroundData.BoyLeft ||
                            data.map[i][j] == BackGroundData.BoyRight ||
                            data.map[i][j] == BackGroundData.BoyBack) {
                        AlgorithmHelper.putImage(graphics2D, j * w, i * h, data.getBoy().getPictureURL());
                    }
                }
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
