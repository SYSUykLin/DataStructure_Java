package ApplicationOfAlgorithm.Maze_Solver;

import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AlgorithmVisualizer {
    private int DELAY = 5;
    private MazeData data;
    private AlgorithmFrame frame;
    private static int blockSick = 5;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public AlgorithmVisualizer(String fileName) {
        data = new MazeData(fileName);
        int sceneHeight = data.N() * blockSick;
        int sceneWidth = data.M() * blockSick;
        EventQueue.invokeLater(() -> {
            frame = new AlgorithmFrame(fileName, sceneWidth, sceneHeight);
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
        setData(-1, -1, false);
//        if (!go(data.getEntanceX(), data.getEntanceY())) {
//            System.out.println("Maze has no solution!");
//        }
        if (!go_iteration()) {
            System.out.println("Maze has no solution!");
        }
//        if (!go_level()) {
//            System.out.println("Maze has no solution!");
//        }
        setData(-1, -1, false);
    }

    //Level
    private boolean go_level() {
        Queue<Position> queue = new LinkedList<>();
        Position position = new Position(data.getEntanceX(), data.getEntanceY());
        queue.add(position);
        data.visited[position.getX()][position.getY()] = true;
        while (!queue.isEmpty()) {
            Position position1 = queue.poll();
            setData(position1.getX(), position1.getY(), true);
            for (int i = 0; i < 4; i++) {
                int newX = position1.getX() + direction[i][0];
                int newY = position1.getY() + direction[i][1];

                if (newX == data.getExitX() && newY == data.getExitY()) {
                    findPath(position1);
                    setData(newX, newY, true);
                    return true;
                }
                Position newPosition = new Position(newX, newY, position1);


                if (data.inArea(newPosition.getX(), newPosition.getY()) &&
                        data.getMaze(newPosition.getX(), newPosition.getY()) == MazeData.ROAD
                        && !data.visited[newPosition.getX()][newPosition.getY()]) {
                    queue.add(newPosition);
                    data.visited[newPosition.getX()][newPosition.getY()] = true;
                }
            }
        }
        return false;
    }

    //iteration
    private boolean go_iteration() {
        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(data.getEntanceX(), data.getEntanceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;
        while (!stack.isEmpty()) {
            Position position = stack.pop();
            setData(position.getX(), position.getY(), true);
            for (int i = 0; i < 4; i++) {
                int newX = position.getX() + direction[i][0];
                int newY = position.getY() + direction[i][1];

                if (newX == data.getExitX() && newY == data.getExitY()) {
                    findPath(position);
                    setData(newX, newY, true);
                    return true;
                }

                Position newPosition = new Position(newX, newY, position);
                if (data.inArea(newPosition.getX(), newPosition.getY()) &&
                        data.getMaze(newPosition.getX(), newPosition.getY()) == MazeData.ROAD
                        && !data.visited[newPosition.getX()][newPosition.getY()]) {
                    stack.push(newPosition);
                    data.visited[newPosition.getX()][newPosition.getY()] = true;
                }
            }
        }
        return false;
    }

    //recusion
    private boolean go(int x, int y) {
        if (!data.inArea(x, y)) {
            throw new IllegalArgumentException("Paramenter is illgel!");
        }
        data.visited[x][y] = true;
        setData(x, y, true);
        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        } else {
            for (int i = 0; i < 4; i++) {
                int nexX = x + direction[i][0];
                int nexY = y + direction[i][1];
                if (data.inArea(nexX, nexY) &&
                        data.getMaze(nexX, nexY) == MazeData.ROAD &&
                        !data.visited[nexX][nexY]) {
                    if (go(nexX, nexY)) {
                        return true;
                    }
                }

            }
            setData(x, y, false);
            return false;
        }
    }

    private void findPath(Position position) {
        data.paths.add(new Position(data.getExitX(), data.getExitY()));
        while (position != null) {
            data.paths.add(position);
            position = position.pre;
        }
    }

    private void setData(int x, int y, boolean isPath) {
        if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
        frame.render(data);
        AlgorithmHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter {
    }

    private class AlgoMouseListener extends MouseAdapter {
    }
}
