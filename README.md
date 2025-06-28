# MYY602 - Artificial Intelligence Assignments

This repository contains the assignments for the course MYY602: Artificial Intelligence.

**Authors:**
*   Papaspyros Stylianos (5162)
*   Bouzas Ioannis (5025)

---

## Assignment 1: Maze Solving with UCS and A*

This project implements and compares the Uniform Cost Search (UCS) and A* search algorithms to find the optimal path for a robot within a dynamically generated maze.

### Features

*   **Dynamic Maze Generation**: Creates an N x N grid where each cell has a probability `p` of being an impassable wall.
*   **Robot Navigation**: The robot can move in eight directions (horizontally, vertically, and diagonally).
*   **Search Algorithms**:
    *   **Uniform Cost Search (UCS)**: Finds the path with the lowest cumulative cost.
    *   **A\* Search**: Uses a heuristic (e.g., Manhattan or Euclidean distance) to efficiently find the shortest path.
*   **User-Defined Points**: The user specifies the start and goal coordinates for the robot at runtime.

### How to Run

1.  Navigate to the assignment directory:
    ```bash
    cd assignment_01
    ```
2.  Compile the Java source files:
    ```bash
    javac *.java
    ```
3.  Run the main program:
    ```bash
    java MazeRunner
    ```
4.  Follow the on-screen prompts to configure and run the simulation.

---

## Assignment 2: Tic-Tac-Toe Variant with Minimax AI

This project is a variant of Tic-Tac-Toe where a human player competes against a CPU that uses the Minimax algorithm to play optimally. The winning condition is forming the sequence "XOX" or "OXO".

### Features

*   **Game Logic**: A Tic-Tac-Toe game on a 3x3 board.
*   **Unique Winning Condition**: A player wins by creating the sequence "XOX" or "OXO" in any horizontal, vertical, or diagonal line.
*   **AI Opponent**: The CPU player uses the **Minimax algorithm** to analyze the game tree and select the move that maximizes its chances of winning.
*   **Interactive Gameplay**: Allows a human to play against the intelligent CPU agent.

### How to Run

1.  Navigate to the assignment directory:
    ```bash
    cd assignment_02 
    ```
2.  Compile the Java source files:
    ```bash
    javac *.java
    ```
3.  Run the main game class:
    ```bash
    java xoxo
    ```