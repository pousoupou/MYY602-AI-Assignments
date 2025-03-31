package assignment_02;

import java.util.Arrays;

public class GameLogic {
    private char[][] grid;
    private int row;
    private int col;

    public GameLogic(){
        grid = new char[4][3];
        Arrays.fill(grid, ' ');
    }

    public char[][] getGrid(){
        return grid;
    }

    public int evaluate(char[][] grid){
        String win = "";

        /**
         * check right
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col + i > 3 || grid[this.col + i][this.row] == ' '){
                break;
            }
            win += grid[this.col + i][this.row];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check left
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col - i < 0 || grid[this.col - i][this.row] == ' '){
                break;
            }
            win += grid[this.col - i][this.row];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check down
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.row + i > 2 || grid[this.col][this.row + i] == ' '){
                break;
            }
            win += grid[this.col][this.row + i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check up
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.row - i < 0 || grid[this.col][this.row - i] == ' '){
                break;
            }
            win += grid[this.col][this.row - i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check right and up
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col + i > 3 || this.row - i < 0 || grid[this.col + i][this.row - i] == ' '){
                break;
            }
            win += grid[this.col + i][this.row - i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check right and down
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col + i > 3 || this.row + i > 2 || grid[this.col + i][this.row + i] == ' '){
                break;
            }
            win += grid[this.col + i][this.row + i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check left and up
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col - i < 0 || this.row - i < 0 || grid[this.col - i][this.row - i] == ' '){
                break;
            }
            win += grid[this.col - i][this.row - i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        /**
         * check left and down
         */
        win += grid[this.col][this.row];
        for(int i = 1; i < 3; i++){
            if(this.col - i < 0 || this.row + i > 2 || grid[this.col - i][this.row + i] == ' '){
                break;
            }
            win += grid[this.col - i][this.row + i];
        }
        if(win.equals("XOX")){
            return 2;
        }
        if(win.equals("OXO")){
            return -2;
        }

        return 0;
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

    public int minimax(char[][] grid, int depth, boolean isMax){
        int score = evaluate(grid); 
    }
}
