package assignment_02;

import java.util.Arrays;

public class Grid {
    private char grid[][];

    public Grid(){
        grid = new char[4][3];
        Arrays.fill(grid, ' ');
    }

    public char[][] getGrid(){
        return grid;
    }

    public void place(char c, int row, int col){
        grid[row][col] = c;
    }

    public boolean isWin(){
        String win = "";

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == ' '){
                    continue;
                }

                win += grid[i][j];

                if()
            }
        }
    }

    public boolean isFull(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                if(grid[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
