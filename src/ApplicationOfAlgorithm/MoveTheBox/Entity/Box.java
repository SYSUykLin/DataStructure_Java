package ApplicationOfAlgorithm.MoveTheBox.Entity;

public class Box extends GameObject {
    public Box(int x, int y, int state) {
        super(x, y, state);
        if (state == BackGroundData.BOX) {
            setPictureURL(BackGroundData.boxURL);
        } else if (state == BackGroundData.BOX_TARGET) {
            setPictureURL(BackGroundData.boxTargetURL);
        }
    }

}
