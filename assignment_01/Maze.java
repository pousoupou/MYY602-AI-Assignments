package assignment_01;

import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private int N;          // Size of the maze (N x N)
    private double p;       // Probability of a cell being empty
    private char[][] maze;
    private char robot = 'R';
    private int[] robotPosition = new int[2];
    private int cost;

    public Maze(int N, double p) {
        if(p < 0 || p > 1) {
            throw new IllegalArgumentException("Probability p must be between 0 and 1");
        }
        this.p = p;
        this.N = N;
        this.maze = new char[N][N];
        this.initMaze();
    }

    public Maze(char[][] maze, int[] robotPosition, int cost){
        this.N = maze.length;
        this.maze = maze;
        this.robotPosition = robotPosition;
        this.cost = cost;
    }

    private void initMaze(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if(i == 0 && j == 0 || i == N-1 && j == N-1) {
                    maze[i][j] = ' ';
                    continue;
                }
                if(Math.random() < (1 - p)){
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
        scanner.close();
    }

    public void printMaze() {
        System.out.println();
        for(int i = 0; i < N - 1; i++) {
            System.out.print("+---");
        }
        System.out.print("+---+\n");

        for (int i = 0; i < N - 1; i++) {
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

        for(int i = 0; i < N - 1; i++) {
            System.out.print("+---");
        }
        System.out.print("+---+\n");
    }

    public void setStart(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            throw new IllegalArgumentException("Start position out of bounds");
        }
        maze[x][y] = 'S';
    }

    public void setGoal(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N) {
            throw new IllegalArgumentException("Goal position out of bounds");
        }
        maze[x][y] = 'G';
    }

    public void moveRobot(int newX, int newY) {
        if (newX < 0 || newX >= N || newY < 0 || newY >= N) {
            throw new IllegalArgumentException("Move out of bounds");
        }
        if (maze[newX][newY] == 'X') {
            throw new IllegalArgumentException("Move blocked by wall");
        }
        maze[robotPosition[0]][robotPosition[1]] = ' ';
        robotPosition[0] = newX;
        robotPosition[1] = newY;
        maze[newX][newY] = robot;
    }

    public int[] getRobotPosition() {
        return robotPosition;
    }
    
    public char[][] getMaze() {
        return maze;
    }

    public int getN() {
        return N;
    }

    public char getRobot() {
        return robot;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the maze (N): ");
        int N = scanner.nextInt();
        System.out.print("Enter the probability of a cell being empty (p): ");
        double p = scanner.nextDouble();
        Maze maze = new Maze(N, p);
        maze.printMaze();
    }
}