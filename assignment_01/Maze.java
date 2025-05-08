import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Maze {
    private int N;          // Size of the maze (N x N)
    private double p;       // Probability of a cell being a Wall
    private char[][] maze;
    private char robot = 'R';
    private int[] robotPosition = new int[2];
    private static int[] goalPosition = new int[2];
    private boolean teleported = false;

    public Maze(int N, double p) {
        if(p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be between 0 and 1");
        }
        this.p = p;
        this.N = N;
        this.maze = new char[N][N];
        this.initMaze();
    }

    public Maze(char[][] maze, int[] robotPosition, boolean teleported) {
        this.N = maze.length;
        this.maze = new char[N][N];
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[i].length; j++){
                this.maze[i][j] = maze[i][j];
            }
        }
        
        for(int i = 0; i < robotPosition.length; i++){
            this.robotPosition[i] = robotPosition[i];
        }

        this.teleported = teleported;
    }

    private void initMaze(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(i == 0 && j == 0 || i == N-1 && j == N-1) {
                    maze[i][j] = ' ';
                    continue;
                }
                if(Math.random() < p){
                    maze[i][j] = 'X';
                } else {
                    maze[i][j] = ' ';
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the start position (x y): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        this.setStart(startX, startY);
        robotPosition[0] = startX;
        robotPosition[1] = startY;
        maze[startX][startY] = robot;

        System.out.print("Enter the goal position (x y): ");
        int goalX = scanner.nextInt();
        int goalY = scanner.nextInt();
        this.setGoal(goalX, goalY);
        // scanner.close();
    }

    public void printMaze(){
        System.out.println();
        for(int i = 0; i < N - 1; i++){
            System.out.print("+---");
        }
        System.out.print("+---+\n");

        for(int i = 0; i < N - 1; i++){
            for (int j = 0; j < N - 1; j++) {
                System.out.print("| " + maze[i][j] + " ");
            }
            System.out.print("| " + maze[i][N-1] + " |\n");

            for(int j = 0; j < N - 1; j++) {
                System.out.print("+---");
            }
            System.out.print("+---+\n");
        }

        for(int i = 0; i < N; i++){
            System.out.print("| " + maze[N-1][i] + " ");
        }
        System.out.print("|\n");

        for(int i = 0; i < N - 1; i++){
            System.out.print("+---");
        }
        System.out.print("+---+\n");
    }

    public void setStart(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N){
            throw new IllegalArgumentException("Start position out of bounds");
        }
        maze[x][y] = 'S';
    }

    public void setGoal(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N){
            throw new IllegalArgumentException("Goal position out of bounds");
        }
        maze[x][y] = 'G';
        goalPosition[0] = x;
        goalPosition[1] = y;
    }

    public int[] getGoalPosition() {
        return goalPosition;
    }

    public ArrayList<Maze> getPossibleMoves() {
        ArrayList<Maze> moves = new ArrayList<>();
        int x = this.robotPosition[0];
        int y = this.robotPosition[1];

        int[][] directions = {
            {x-1, y}, {x+1, y}, {x, y-1}, {x, y+1}, // Up, Down, Left, Right
            {x-1, y-1}, {x-1, y+1}, {x+1, y-1}, {x+1, y+1} // Diagonal
        };

        for(int[] direction : directions){
            int newX = direction[0];
            int newY = direction[1];

            if(newX >= 0 && newX < N && newY >= 0 && newY < N){
                if (this.maze[newX][newY] == ' ' || this.maze[newX][newY] == 'G') {
                    Maze newMaze = new Maze(this.maze, new int[]{newX, newY}, false);
                    newMaze.getMaze()[newX][newY] = robot;
                    newMaze.getMaze()[x][y] = ' ';
                    moves.add(newMaze);
                    continue;
                }
            }

            // Special cases for teleportation
            if(x == N-1 && newX == N-1 && newY == N){
                if(this.maze[0][0] == ' ' || this.maze[0][0] == 'G'){
                    Maze newMaze = new Maze(this.maze, new int[]{0, 0}, true);
                    newMaze.getMaze()[0][0] = robot;
                    newMaze.getMaze()[x][y] = ' ';
                    moves.add(newMaze);
                    continue;
                }
            }
            if(x == 0 && newX == 0 && newY == -1){
                if(this.maze[N-1][N-1] == ' ' || this.maze[N-1][N-1] == 'G'){
                    Maze newMaze = new Maze(this.maze, new int[]{N-1, N-1}, true);
                    newMaze.getMaze()[N-1][N-1] = robot;
                    newMaze.getMaze()[x][y] = ' ';
                    moves.add(newMaze);
                    continue;
                }
            }
        }

        return moves;
    }

    public boolean isTeleported() {
        return teleported;
    }

    public int[] getRobotPosition() {
        return this.robotPosition;
    }
    
    public char[][] getMaze() {
        return this.maze;
    }

    public int getN() {
        return N;
    }

    public char getRobot() {
        return robot;
    }

    public boolean isGoal() {
        return this.robotPosition[0] == goalPosition[0] && this.robotPosition[1] == goalPosition[1];
    }
}