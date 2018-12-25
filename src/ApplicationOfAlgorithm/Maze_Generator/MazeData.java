package ApplicationOfAlgorithm.Maze_Generator;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] inMist;

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }


    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    private int entranceX, entranceY;
    private int exitX, exitY;
    private int N;

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    private int M;

    public MazeData(int N, int M) {
        this.N = N;
        this.M = M;
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = N - 1;
        maze = new char[N][M];
        visited = new boolean[N][M];
        inMist = new boolean[N][M];
        if (N % 2 == 0 || M % 2 == 0) {
            throw new IllegalArgumentException("N or M must be an odd number");
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
                visited[i][j] = false;
                inMist[i][j] = true;
            }
        }
        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public void openMinst(int x, int y) {
        if (inArea(x, y)) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (inArea(i, j)){
                        inMist[i][j] = false;
                    }
                }
            }
        }
    }

}
