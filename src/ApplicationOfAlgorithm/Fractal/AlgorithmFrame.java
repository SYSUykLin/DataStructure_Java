package ApplicationOfAlgorithm.Fractal;

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

    private FractalData data;

    public void render(FractalData data) {
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

            //drawFractal(graphics2D, 0, 0, canvasWidth, canvasHeight, 0);
            //drawFractal_Triangle(graphics2D, 0, canvasHeight, canvasWidth, 0);
            int side = canvasWidth / 3;
            //drawSnow(graphics2D, 0, canvasHeight - 3, canvasWidth, 0, 0);
            drawTree(graphics2D, canvasWidth / 2, canvasHeight, canvasHeight, 90, 0);
        }

        public void drawFractal(Graphics2D graphics2D, int x, int y, int w, int h, int depth) {
//            if (depth == data.getDepth()) {
//                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Indigo);
//                AlgorithmHelper.fillRectangle(graphics2D, x, y, w, h);
//                return;
//            }
//            if (w <= 1 || h <= 1) {
//                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Indigo);
//                AlgorithmHelper.fillRectangle(graphics2D, x, y, Math.max(w, 1), Math.max(h, 1));
//                return;
//            }
//            int w_3 = w / 3;
//            int h_3 = h / 3;
//            drawFractal(graphics2D, x + w_3, y, w_3, h_3, depth + 1);
//            drawFractal(graphics2D, x, y + h_3, w_3, h_3, depth + 1);
//            drawFractal(graphics2D, x + w_3, y + h_3, w_3, h_3, depth + 1);
//            drawFractal(graphics2D, x + 2* w_3, y + h_3, w_3, h_3, depth + 1);
//            drawFractal(graphics2D, x +  w_3, y + 2 * h_3, w_3, h_3, depth + 1);
// -------------------------------------------------------------------
            int w_3 = w / 3;
            int h_3 = h / 3;
            if (depth == data.getDepth()) {
                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Amber);
                AlgorithmHelper.fillRectangle(graphics2D, x + w / 3, y + h / 3, w / 3, h / 3);
                return;
            }
            if (w <= 1 || h <= 1) {
                return;
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Amber);
                        AlgorithmHelper.fillRectangle(graphics2D, x + w / 3, y + h / 3, w / 3, h / 3);
                    } else {
                        drawFractal(graphics2D, x + i * w_3, y + j * h_3, w_3, h_3, depth + 1);
                    }
                }
            }
        }

        public void drawFractal_Triangle(Graphics2D graphics2D, int Ax, int Ay, int size, int depth) {
            if (size <= 1) {
                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Lime);
                AlgorithmHelper.fillRectangle(graphics2D, Ax, Ay, 1, 1);
                return;
            }
            int Bx = Ax + size;
            int By = Ay;
            int h = (int) (Math.sin(60.0 * Math.PI / 180.0) * size);
            int Cx = Ax + size / 2;
            int Cy = Ay - h;
            if (depth == data.getDepth()) {
                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Cyan);
                AlgorithmHelper.fillTriangle(graphics2D, Ax, Ay, Bx, By, Cx, Cy);
                return;
            }
            int AB_centerX = (Ax + Bx) / 2;
            int AB_centerY = (Ay + By) / 2;

            int AC_centerX = (Ax + Cx) / 2;
            int AC_centerY = (Ay + Cy) / 2;

            int BC_centerX = (Bx + Cx) / 2;
            int BC_centerY = (By + Cy) / 2;

            drawFractal_Triangle(graphics2D, Ax, Ay, size / 2, depth + 1);
            drawFractal_Triangle(graphics2D, AC_centerX, AC_centerY, size / 2, depth + 1);
            drawFractal_Triangle(graphics2D, AB_centerX, AB_centerY, size / 2, depth + 1);
        }

        public void drawSnow(Graphics2D graphics2D, double x1, double y1, double side, double angle, int depth) {
            if (side <= 0) {
                return;
            }
            if (depth == data.getDepth()) {
                double x2 = x1 + side * Math.cos(angle * Math.PI / 180.0);
                double y2 = y1 - side * Math.sin(angle * Math.PI / 180.0);
                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.DeepOrange);
                AlgorithmHelper.drawLine(graphics2D, x1, y1, x2, y2);
                return;
            }
            double side_3 = side / 3;
            double x2 = x1 + side_3 * Math.cos(angle * Math.PI / 180.0);
            double y2 = y1 - side_3 * Math.sin(angle * Math.PI / 180.0);
            drawSnow(graphics2D, x1, y1, side_3, angle, depth + 1);

            double x3 = x2 + side_3 * Math.cos((angle + 60.0) * Math.PI / 180.0);
            double y3 = y2 - side_3 * Math.sin((angle + 60.0) * Math.PI / 180.0);
            drawSnow(graphics2D, x2, y2, side_3, angle + 60, depth + 1);

            double x4 = x3 + side_3 * Math.cos((angle - 60.0) * Math.PI / 180.0);
            double y4 = y3 - side_3 * Math.sin((angle - 60.0) * Math.PI / 180.0);
            drawSnow(graphics2D, x3, y3, side_3, angle - 60, depth + 1);

            drawSnow(graphics2D, x4, y4, side_3, angle, depth + 1);
            return;
        }

        public void drawTree(Graphics2D graphics2D, double x1, double y1, double side, double angle, int depth) {
            double side_2 = side / 2;
            if (side_2 <= 0) {
                return;
            }
            if (depth == data.getDepth()) {
                double x2 = x1 - side * Math.cos(angle * Math.PI / 180.0);
                double y2 = y1 - side * Math.sin(angle * Math.PI / 180.0);
                AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Green);
                AlgorithmHelper.drawLine(graphics2D, x1, y1, x2, y2);
                return;
            }
            double x2 = x1 - side / 2 * Math.cos(angle * Math.PI / 180.0);
            double y2 = y1 - side / 2 * Math.sin(angle * Math.PI / 180.0);
            AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Brown);
            AlgorithmHelper.drawLine(graphics2D, x1, y1, x2, y2);
            drawTree(graphics2D, x2, y2, side / 2, angle + data.splitAngle / 2, depth + 1);
            drawTree(graphics2D, x2, y2, side / 2, angle + data.splitAngle / 4, depth + 1);
            drawTree(graphics2D, x2, y2, side / 2, angle - data.splitAngle / 2, depth + 1);
            drawTree(graphics2D, x2, y2, side / 2, angle - data.splitAngle / 4, depth + 1);

            return;
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
