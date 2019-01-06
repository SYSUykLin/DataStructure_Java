package ApplicationOfAlgorithm.Fractal;

public class FractalData {
    private int depth = 1;
    public double splitAngle = 0;
    public FractalData(int depth){
        this.depth = depth;
    }
    public FractalData(int depth, double splitAngle){
        this.depth = depth;
        this.splitAngle = splitAngle;
    }
    public int getDepth(){
        return depth;
    }

    public void setDepth(int depth){
        this.depth = depth;
    }
}
