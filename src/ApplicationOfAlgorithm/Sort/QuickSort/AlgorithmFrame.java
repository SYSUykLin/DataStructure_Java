package ApplicationOfAlgorithm.Sort.QuickSort;

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
        setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private QuickSortData data;

    public void render(QuickSortData data) {
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
            int w = canvasWidth / data.N();
            for (int i = 0; i < data.N(); i ++){
                if (i >= data.l && i <= data.Index-1){
                    AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Green);
                }if (i >= data.Index+1 && i <= data.r){
                    AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Black);
                }if(i == data.Index){
                    AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Red);
                }
                else if(i < data.l || i > data.r)
                    AlgorithmHelper.setColor(graphics2D, AlgorithmHelper.Grey);

                AlgorithmHelper.fillRectangle(graphics2D, i*w, canvasHeight-data.get(i), w-1, data.get(i));
            }

        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
