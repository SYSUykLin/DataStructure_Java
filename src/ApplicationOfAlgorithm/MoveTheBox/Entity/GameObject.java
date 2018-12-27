package ApplicationOfAlgorithm.MoveTheBox.Entity;

public class GameObject {
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public boolean inArea(int x, int y) {
        if (x < 0 || x >= BackGroundData.width ||
                y < 0 || y >= BackGroundData.height) {
            return false;
        }
        return true;
    }

    private int x;
    private int y;
    private int state;
    private String pictureURL;

    public GameObject(int x, int y, int state) {
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void move(int[] d, BackGroundData data, int direction) {
        if (getX() + d[0] < 0 ||
                getX() + d[0] >= BackGroundData.width ||
                getY() + d[1] < 0 || getY() + d[1] >= BackGroundData.height) {
            return;
        }
        int oldX = getX();
        int oldY = getY();
        int newX = getX() + d[0];
        int newY = getY() + d[1];


        //old place state
        if (data.isTarget(oldX, oldY)) {
            data.map[oldX][oldY] = BackGroundData.TARGET;
        } else {
            data.map[oldX][oldY] = BackGroundData.GLASS;
        }

        //object state
        if (this instanceof Box) {
            setX(newX);
            setY(newY);
            if (data.map[newX][newY] == BackGroundData.TARGET) {
                this.setState(BackGroundData.BOX_TARGET);
                this.setPictureURL(BackGroundData.boxTargetURL);
            } else {
                this.setState(BackGroundData.BOX);
                this.setPictureURL(BackGroundData.boxURL);
            }
        } else if (this instanceof Boy) {
            setX(newX);
            setY(newY);
            if (direction == BackGroundData.LEFT) {
                this.setState(BackGroundData.BoyLeft);
                this.setPictureURL(BackGroundData.boyLeftURL);
            } else if (direction == BackGroundData.UP) {
                this.setState(BackGroundData.BoyBack);
                this.setPictureURL(BackGroundData.boyBackURL);
            } else if (direction == BackGroundData.RIGHT) {
                this.setState(BackGroundData.BoyRight);
                this.setPictureURL(BackGroundData.boyRightURL);
            } else if (direction == BackGroundData.DOWN) {
                this.setState(BackGroundData.Boy);
                this.setPictureURL(BackGroundData.boyFrontURL);
            }
        }

        //new place state
        data.map[newX][newY] = this.getState();
    }

}
