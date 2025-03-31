package assignment_02;

import java.util.Scanner;

public class xoxo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameLogic gl = new GameLogic();

        int row = -1;
        int col = -1;

        while(!gl.isGameEnded()){
            gl.printGrid();
            
            System.out.println("Choose your cell (row -> enter -> column -> enter):\n");
            row = sc.nextInt();
            col = sc.nextInt();
            
            while(!gl.coordsValid(row, col)){
                System.out.println("Choose an empty cell!\n");
                gl.printGrid();
                System.out.println("Choose your cell (row -> enter -> column -> enter):\n");
                row = sc.nextInt();
                col = sc.nextInt();
            }
            
            gl.play(row, col);

            if(gl.evaluate(gl.getGrid()) == -2){
                System.out.println("User Wins!");
                gl.printGrid();
                return;
            }
            else if(gl.evaluate(gl.getGrid()) == 2){
                System.out.println("Computer Wins!");
                gl.printGrid();
                return;
            }
            else if(gl.evaluate(gl.getGrid()) == 0){
                System.out.println("Draw!");
                gl.printGrid();
                return;
            }
            
            gl.printGrid();
            
            gl.findBestMove();
            
            if(gl.evaluate(gl.getGrid()) == -2){
                System.out.println("User Wins!");
                gl.printGrid();
                return;
            }
            else if(gl.evaluate(gl.getGrid()) == 2){
                System.out.println("Computer Wins!");
                gl.printGrid();
                return;
            }
            else if(gl.evaluate(gl.getGrid()) == 0){
                System.out.println("Draw!");
                gl.printGrid();
                return;
            }
        }

        gl.printGrid();
    }
}
