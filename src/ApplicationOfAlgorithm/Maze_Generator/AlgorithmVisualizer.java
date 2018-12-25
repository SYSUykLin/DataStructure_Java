package ApplicationOfAlgorithm.Maze_Generator;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;

public class AlgorithmVisualizer {
    public int DELAY = 10;
    private MazeData data;
    private AlgorithmFrame frame;
    public static int blockSize = 3;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public static final String fileDie = "src/ApplicationOfAlgorithm/Maze_Solver/MazeFile/";

    public AlgorithmVisualizer(int N, int M) {
        data = new MazeData(N, M);
        int sceneWidth = data.getM() * blockSize;
        int sceneHeight = data.getN() * blockSize;
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame("Maze_Generator", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });

    }

    public AlgorithmVisualizer(String title, int sceneWidth, int sceneHeight) {

        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(title, sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run() {
        setData(-1, -1);
        //go(data.getEntranceX(), data.getEntranceY() + 1);
        //go_iterator();
        go_level();
        setData(-1, -1);
        ApplicationOfAlgorithm.Maze_Solver.AlgorithmVisualizer algorithmVisualizer1 = new ApplicationOfAlgorithm.Maze_Solver.AlgorithmVisualizer("src/ApplicationOfAlgorithm/Maze_Solver/MazeFile/maze_201_201.txt");
    }

    private void go_level() {
        RandomQueue<Position> stack = new RandomQueue<>();
        Position firstPosition = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.add(firstPosition);
        data.openMinst(firstPosition.getX(), firstPosition.getY());
        data.visited[firstPosition.getX()][firstPosition.getY()] = true;
        while (!stack.isEmpty()) {
            Position position = stack.remove();
            for (int i = 0; i < 4; i++) {
                int newX = position.getX() + direction[i][0] * 2;
                int newY = position.getY() + direction[i][1] * 2;
                if (data.inArea(newX, newY) &&
                        !data.visited[newX][newY]) {
                    data.openMinst(newX, newY);
                    setData(position.getX() + direction[i][0], position.getY() + direction[i][1]);
                    stack.add(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                }
            }
        }
        packageMaze("maze_" + data.getN() + '_' + data.getM() + ".txt");

    }

    private void go_iterator() {
        Stack<Position> stack = new Stack<>();
        Position firstPosition = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        stack.push(firstPosition);
        data.visited[firstPosition.getX()][firstPosition.getY()] = true;
        while (!stack.isEmpty()) {
            Position position = stack.pop();
            for (int i = 0; i < 4; i++) {
                int newX = position.getX() + direction[i][0] * 2;
                int newY = position.getY() + direction[i][1] * 2;
                if (data.inArea(newX, newY) &&
                        !data.visited[newX][newY]) {
                    setData(position.getX() + direction[i][0], position.getY() + direction[i][1]);
                    stack.push(new Position(newX, newY));
                    data.visited[newX][newY] = true;
                }
            }
        }
    }

    //recursion
    private void go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("x or y is illegal!");
        }
        data.visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0] * 2;
            int newY = y + direction[i][1] * 2;
            if (data.inArea(newX, newY) &&
                    !data.visited[newX][newY]) {
                data.openMinst(newX, newY);
                setData(x + direction[i][0], y + direction[i][1]);
                go(newX, newY);
            }
        }
    }

    public void setData(int x, int y) {
        if (data.inArea(x, y)) {
            data.maze[x][y] = MazeData.ROAD;
        }

        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    public void packageMaze(String fileName) {
        File file = new File(fileDie + '/' + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("fail to create!");
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileWriter.write(String.valueOf(data.getN()));
            fileWriter.write(' ');
            fileWriter.write(String.valueOf(data.getM()));
            fileWriter.write('\n');
            for (int i = 0; i < data.getN(); i++) {
                for (int j = 0; j < data.getM(); j++) {
                    if (data.maze[i][j] == MazeData.WALL) {
                        fileWriter.write('#');
                    } else {
                        fileWriter.write(' ');
                    }
                }
                fileWriter.write('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
