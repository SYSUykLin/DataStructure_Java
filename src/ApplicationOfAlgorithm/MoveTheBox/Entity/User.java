package ApplicationOfAlgorithm.MoveTheBox.Entity;

import ApplicationOfAlgorithm.MoveTheBox.AlgorithmFrame;
import ApplicationOfAlgorithm.MoveTheBox.AlgorithmHelper;

public class User {
    private BackGroundData backGroundData;
    private int[][] A;
    private int[][] direction = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    AlgorithmFrame jFrame;
    private boolean[][] visited;


    public User(BackGroundData backGroundData, AlgorithmFrame jFrame) {
        this.backGroundData = backGroundData;
        visited = new boolean[backGroundData.getN()][backGroundData.getM()];
        A = new int[backGroundData.getN()][backGroundData.getM()];
        this.jFrame = jFrame;

    }

    private void setData(BackGroundData backGroundData1) {
        this.backGroundData = backGroundData1;
//        for (int i = 0; i < backGroundData.getN(); i++) {
//            for (int j = 0; j < backGroundData.getM(); j++) {
//                if (backGroundData.map[i][j] == BackGroundData.Boy ||
//                        backGroundData.map[i][j] == BackGroundData.BoyBack ||
//                        backGroundData.map[i][j] == BackGroundData.BoyRight ||
//                        backGroundData.map[i][j] == BackGroundData.BoyLeft) {
//                    backGroundData.getBoy().setX(i);
//                    backGroundData.getBoy().setY(j);
//                }
//                if (backGroundData.map[i][j] == BackGroundData.BOX_TARGET ||
//                backGroundData.map[i][j] == )
//            }
//        }
        jFrame.render(backGroundData);
        AlgorithmHelper.pause(40);

    }

    public BackGroundData copy(BackGroundData backGroundData) {
        return new BackGroundData(backGroundData);
    }

    public void play(int x, int y) {
        if (!inArea(x, y)) {
            return;
        }
        visited[x][y] = true;
        BackGroundData temp = copy(backGroundData);
        for (int i = 0; i < 4; i++) {
            int newX = backGroundData.getBoy().getX() + direction[i][0];
            int newY = backGroundData.getBoy().getY() + direction[i][1];
            if (!visited[newX][newY] && backGroundData.move(direction[i], i + 9, backGroundData.getBoy())) {
                setData(backGroundData);
                play(newX, newY);
            }
        }
        setData(temp);
    }

    private boolean inArea(int x, int y) {
        return x < 0 || x >= backGroundData.getM() ||
                y < 0 || y >= backGroundData.getN();
    }

    public void play() {
        int oldX = backGroundData.getBoy().getX();
        int oldY = backGroundData.getBoy().getY();
        for (int i = 0; i < 4; i++) {
            int newX = backGroundData.getBoy().getX() + direction[i][0];
            int newY = backGroundData.getBoy().getY() + direction[i][1];
            if (!visited[newX][newY] && backGroundData.move(direction[i], i + 9, backGroundData.getBoy())) {
                visited[newX][newY] = true;
                play();
            }
        }

    }

    public void G(int x, int y) {

    }

    public void F(int x, int y) {

    }

    public void H(int x, int y) {

    }
}
