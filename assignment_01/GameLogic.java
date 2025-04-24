package assignment_01;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    public ArrayList<Maze> getRobotMoveList(Maze maze){
        ArrayList<Maze> robotMoveList = new ArrayList<>();
        int[] robotPosition = maze.getRobotPosition();
        int N = maze.getN();
        char robot = maze.getRobot();

        int x = robotPosition[0];
        int y = robotPosition[1];
        int[][] moves = {
            {x-1, y}, {x+1, y}, {x, y-1}, {x, y+1}, // Up, Down, Left, Right
            {x-1, y-1}, {x-1, y+1}, {x+1, y-1}, {x+1, y+1} // Diagonal moves
        };

        for (int[] move : moves) {
            int newX = move[0];
            int newY = move[1];
            if (newX >= 0 && newX < N && newY >= 0 && newY < N) {
                if (maze.getMaze()[newX][newY] == ' ') {
                    int[] _robotPosition = {newX, newY};
                    Maze newMaze = new Maze(maze.getMaze(), _robotPosition, 1);
                    // for (int i = 0; i < N; i++) {
                    //     System.arraycopy(maze.getMaze()[i], 0, newMaze.getMaze()[i], 0, N);
                    // }
                    newMaze.getMaze()[newX][newY] = robot;
                    robotMoveList.add(newMaze);
                }
            }
        }

        // Special case for bottom right corner to top left corner
        if (robotPosition[0] == N-1 && robotPosition[1] == N-1) {
            int[] _robotPosition = {0, 0};
            Maze newMaze = new Maze(maze.getMaze(), _robotPosition, 2);
            // for (int i = 0; i < N; i++) {
            //     System.arraycopy(maze.getMaze()[i], 0, newMaze.getMaze()[i], 0, N);
            // }
            newMaze.getMaze()[0][0] = robot;
            robotMoveList.add(newMaze);
        }

        // Special case for top left corner to bottom right corner
        if (robotPosition[0] == 0 && robotPosition[1] == 0) {
            int[] _robotPosition = {N-1, N-1};
            Maze newMaze = new Maze(maze.getMaze(), _robotPosition, 2);
            // for (int i = 0; i < N; i++) {
            //     System.arraycopy(maze.getMaze()[i], 0, newMaze.getMaze()[i], 0, N);
            // }
            newMaze.getMaze()[N-1][N-1] = robot;
            robotMoveList.add(newMaze);
        }

        return robotMoveList;
    }
}
