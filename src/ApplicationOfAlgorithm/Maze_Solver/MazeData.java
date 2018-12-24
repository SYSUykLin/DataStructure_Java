package ApplicationOfAlgorithm.Maze_Solver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class MazeData {
    private char[][] maze;
    private int N, M;
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public boolean[][] visited;
    public boolean[][] path;
    public LinkedList<Position> paths = new LinkedList<>();

    public boolean containsPaths(int x, int y){
        for (Position position : paths){
            if (position.getX() == x && position.getY() == y){
                return true;
            }
        }
        return false;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    private int entanceX;

    public int getEntanceX() {
        return entanceX;
    }

    public int getEntanceY() {
        return entanceY;
    }

    private int entanceY;
    private int exitX, exitY;

    public MazeData(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("filename can't be null!");
        }
        Scanner scanner = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                throw new IllegalArgumentException("File is not exist!");
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
            String nm = scanner.nextLine();
            String[] nmC = nm.trim().split("\\s+");
            N = Integer.parseInt(nmC[0]);
            M = Integer.parseInt(nmC[1]);
            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                String line = scanner.nextLine();
                if (line.length() != M) {
                    throw new IllegalArgumentException("Message of file is not completed!");
                }
                for (int j = 0; j < M; j++) {
                    maze[i][j] = line.charAt(j);
                    visited[i][j] = false;
                    path[i][j] = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        entanceX = 1;
        entanceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public char getMaze(int i, int j) {
        if (!inArea(i, j)) {
            throw new IllegalArgumentException("out of range!");
        }
        return maze[i][j];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public int N() {
        return N;
    }

    public int M() {
        return M;
    }
}
