package ApplicationOfAlgorithm.MoveTheBox.Entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BackGroundData {
    private int N;
    private int M;
    public static int width;
    public static int height;

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public static final String basicURL = "src/ApplicationOfAlgorithm/MoveTheBox/imgs/";
    public static final String homeURL = basicURL + "home.GIF";
    public static final String glassURL = basicURL + "glass.GIF";
    public static final String targetURL = basicURL + "target.GIF";
    public static final String boxURL = basicURL + "box.GIF";
    public static final String boxTargetURL = basicURL + "Box_target.GIF";
    public static final String boyFrontURL = basicURL + "humanFront.GIF";
    public static final String boyLeftURL = basicURL + "humanLeft.GIF";
    public static final String boyRightURL = basicURL + "humanRight.GIF";
    public static final String boyBackURL = basicURL + "humanBack.GIF";
    public static final int HOME = 0;
    public static final int GLASS = 1;
    public static final int TARGET = 2;
    public static final int BOX_TARGET = 3;
    public static final int BOX = 4;
    public static final int Boy = 5;
    public static final int BoyLeft = 6;
    public static final int BoyRight = 7;
    public static final int BoyBack = 8;
    public static final int LEFT = 9;
    public static final int UP = 10;
    public static final int RIGHT = 11;
    public static final int DOWN = 12;
    private Boy boy;

    public ArrayList<Box> getBox() {
        return box;
    }

    private ArrayList<Box> box;

    public ArrayList<Target> getTargets() {
        return targets;
    }

    private ArrayList<Target> targets;
    public int[][] map;

    public BackGroundData(BackGroundData backGroundData){
        this.map = backGroundData.map;
        this.N = backGroundData.getN();
        this.M = backGroundData.getM();
        this.boy = backGroundData.getBoy();
        this.box = backGroundData.getBox();
        this.targets = backGroundData.getTargets();
        width = N;
        height = M;
    }

    public BackGroundData(String mapURL) {
        box = new ArrayList<>();
        targets = new ArrayList<>();
        readTxt(basicURL + mapURL);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == BOX) {
                    box.add(new Box(i, j, BOX));
                } else if (map[i][j] == Boy) {
                    boy = new Boy(i, j, Boy);
                } else if (map[i][j] == TARGET) {
                    targets.add(new Target(i, j, TARGET));
                }
            }

        }
    }

    public Box getBox(int i, int j) {
        for (int k = 0; k < box.size(); k++) {
            if (box.get(k).getX() == i && box.get(k).getY() == j) {
                return box.get(k);
            }
        }
        return null;
    }

    public Target getTarget(int i, int j) {
        for (int k = 0; k < targets.size(); k++) {
            if (targets.get(k).getX() == i && targets.get(k).getY() == j) {
                return targets.get(k);
            }
        }
        return null;
    }

    public boolean isWin() {
        for (int i = 0; i < box.size(); i++) {
            if (box.get(i).getState() != BackGroundData.BOX_TARGET){
                return false;
            }
        }
        return true;
    }

    public Boy getBoy() {
        return boy;
    }

    public boolean isBox(int x, int y) {
        return getBox(x, y) != null;
    }

    public boolean move(int[] direction, int direct, GameObject gameObject) {

        //if object is boy, we need to transfor the direction
        if (gameObject instanceof Boy){
            Boy boy = (Boy) gameObject;
            map[gameObject.getX()][gameObject.getY()] = boy.transForDir(direct);
        }

        //前面啥都没有
        //前面箱子
        //前面是房子

        int newX = gameObject.getX() + direction[0];
        int newY = gameObject.getY() + direction[1];
        if (!gameObject.inArea(newX, newY)) {
            return false;
        } else {
            //nothing
            if (isEmpty(newX, newY)) {
                gameObject.move(direction, this, direct);
                return true;
            }

            //box in the front
            if (map[newX][newY] == BackGroundData.BOX ||
                    map[newX][newY] == BackGroundData.BOX_TARGET) {
                if (gameObject instanceof Box) {
                    return false;
                } else {
                    if (move(direction, direct, getBox(newX, newY))) {
                        gameObject.move(direction, this, direct);
                        return true;
                    }
                    return false;
                }
            }

            if (map[newX][newY] == BackGroundData.HOME) {
                return false;
            }
        }
        return false;
    }

    public boolean isEmpty(int x, int y) {
        if (map[x][y] == BackGroundData.TARGET ||
                map[x][y] == BackGroundData.GLASS) {
            return true;
        }
        return false;
    }

    public boolean isTarget(int x, int y) {
        return getTarget(x, y) != null;
    }

    public void readTxt(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException("file is not exist!");
        }
        FileInputStream fileInputStream = null;
        Scanner scanner = null;
        try {
            fileInputStream = new FileInputStream(file);
            scanner = new Scanner(fileInputStream);
            String num = scanner.nextLine();
            String [] nm = num.split("\\s+");
            this.N = Integer.parseInt(nm[0]);
            this.M = Integer.parseInt(nm[1]);
            width = N;
            height = M;
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < M ; j++) {
                    map[i][j] = Integer.parseInt(line.charAt(j) + "");
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("file io stream is fail!");
        } finally {
            scanner.close();
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
