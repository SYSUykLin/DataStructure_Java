package ApplicationOfAlgorithm.Maze_Generator;

public class Position {

    private int x, y;
    public Position pre;

    public Position(int x, int y, Position pre){
        this.x = x;
        this.y = y;
        this.pre = pre;
    }

    public Position(int x, int y){
        this(x, y, null);
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
