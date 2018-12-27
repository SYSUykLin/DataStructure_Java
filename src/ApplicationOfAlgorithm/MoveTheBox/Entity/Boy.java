package ApplicationOfAlgorithm.MoveTheBox.Entity;

public class Boy extends GameObject {
    public Boy(int x, int y, int state) {
        super(x, y, state);
        if (state == BackGroundData.Boy) {
            setPictureURL(BackGroundData.boyFrontURL);
        } else if (state == BackGroundData.BoyLeft) {
            setPictureURL(BackGroundData.boyLeftURL);
        } else if (state == BackGroundData.BoyRight) {
            setPictureURL(BackGroundData.boyRightURL);
        } else if (state == BackGroundData.BoyBack) {
            setPictureURL(BackGroundData.boyBackURL);
        }
    }

    public int transForDir(int direct){
        if (direct == BackGroundData.UP){
            setState(BackGroundData.BoyBack);
            setPictureURL(BackGroundData.boyBackURL);
        }else if (direct == BackGroundData.LEFT){
            setState(BackGroundData.BoyLeft);
            setPictureURL(BackGroundData.boyLeftURL);
        }else if (direct == BackGroundData.RIGHT){
            setState(BackGroundData.BoyRight);
            setPictureURL(BackGroundData.boyRightURL);
        }else if (direct == BackGroundData.DOWN){
            setState(BackGroundData.Boy);
            setPictureURL(BackGroundData.boyFrontURL);
        }
        return getState();
    }

}
