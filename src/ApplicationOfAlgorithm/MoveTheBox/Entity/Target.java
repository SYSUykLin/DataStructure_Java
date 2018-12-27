package ApplicationOfAlgorithm.MoveTheBox.Entity;

public class Target extends GameObject {
    public Target(int x, int y, int state) {
        super(x, y, state);
        setPictureURL(BackGroundData.targetURL);
    }
}
