package ApplicationOfAlgorithm.Mine;

public class MineSweeperData {
    public static final String basicURL = "src/ApplicationOfAlgorithm/Mine/resources/";
    public static final String blockImageURL = basicURL + "block.png";
    public static final String flagImageURL = basicURL + "flag.png";
    public static final String mineImageURL = basicURL + "mine.png";

    public static String numberImageURL(int num) {
        if (num < 0 || num > 8) {
            throw new IllegalArgumentException("out of range!");
        }
        return basicURL + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;
    public boolean[][] open;
    public boolean[][] flags;
    private int[][] numbers;

    public MineSweeperData(int N, int M, int mineNumber) {
        if (N <= 0 || M <= 0) {
            throw new IllegalArgumentException("N or M is invalid!");
        }
        if (mineNumber <= 0 || mineNumber >= N * M) {
            throw new IllegalArgumentException("too much mine!");
        }
        this.N = N;
        this.M = M;
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];
        generateMines(mineNumber);
        calculateNumbers();
    }

    private void calculateNumbers() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (mines[i][j]) {
                    numbers[i][j] = -1;
                }
                numbers[i][j] = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (inArea(k, l) && mines[k][l]) {
                            numbers[i][j]++;
                        }
                    }
                }
            }

        }
    }

    public int getNumber(int i, int j) {
        if (inArea(i, j)) {
            return numbers[i][j];
        } else {
            return -1;
        }
    }

    private void generateMines(int number) {
//        for (int i = 0; i < number; i++) {
//            while (true) {
//                int x = (int) (Math.random() * N);
//                int y = (int) (Math.random() * M);
//                if (!mines[x][y]) {
//                    mines[x][y] = true;
//                    break;
//                }
//            }
//        }
        for (int i = 0; i < number; i++) {
            int x = i / M;
            int y = i % M;
            mines[x][y] = true;
        }
        int swapTime = N * M;
        for (int j = swapTime - 1; j >= 0; j--) {
            int x1 = j / M;
            int y1 = j % M;
            int rangeNumber = (int) (Math.random() * (j + 1));
            int x2 = rangeNumber / M;
            int y2 = rangeNumber % M;
            swap(x1, y1, x2, y2);
        }

    }

    private void swap(int x1, int y1, int x2, int y2) {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean isMine(int x, int y) {
        if (!inArea(x, y)) {
            throw new IllegalArgumentException("x or y is not corrent!");
        }
        return mines[x][y];
    }

    public void open(int x, int y) {
        if (inArea(x, y) && !isMine(x, y)) {
            open[x][y] = true;
            if (numbers[x][y] > 0) {
                return;
            } else {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1 ; j++) {
                        if (inArea(i, j) && !open[i][j] && !mines[i][j]){
                            open(i, j);
                        }
                    }
                }
            }
        }
    }

    public boolean isWin(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (flags[i][j] != mines[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
