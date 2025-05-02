package assignment_01;

import java.util.*;

public class mazeRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the maze size (N): ");
        int N = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter the probability of a cell being empty (p): ");
        double p = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        Maze mazeObj = new Maze(N, p);
        
        Engine engine = new Engine(mazeObj);

        mazeObj.printMaze();
        
        System.out.println("Running UCS search...");
        engine.searchUCS();

        System.out.println("Running A* search...");
        engine.searchAStar();

        scanner.close();
    }
}
