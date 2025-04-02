package assignment_02;

import java.util.Random;

public class GameLogic {
    private static char[][] grid = new char[3][4];
    private int row;
    private int col;
    private boolean maxFirst;
    private boolean gameEnded;
    private static int[] score = new int[4];

    public GameLogic(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                grid[i][j] = ' ';
            }
        }

        Random rand = new Random(System.currentTimeMillis());

        int rand_int1 = rand.nextInt(13);
        int xrow = rand_int1 % 3;
        int xcol = rand_int1 / 4;
        int rand_int2 = rand.nextInt(13);
        int orow = rand_int2 % 3;
        int ocol = rand_int2 / 4;
        while(orow == xrow && ocol == xcol || orow == xrow + 1 && ocol == xcol || orow == xrow - 1 && ocol == xcol || orow == xrow && ocol == xcol + 1 ||
                orow == xrow && ocol == xcol - 1 || orow == xrow + 1 && ocol == xcol + 1 || orow == xrow - 1 && ocol == xcol + 1 ||
                orow == xrow - 1 && ocol == xcol - 1 || orow == xrow + 1 && ocol == xcol - 1){
            rand_int2 = rand.nextInt(13);
            orow = rand_int2 % 3;
            ocol = rand_int2 / 4;
        }

        this.grid[rand_int1 % 3][rand_int1 / 4] = 'X';
        this.grid[rand_int2 % 3][rand_int2 / 4] = 'O';

        int rand_int3 = rand.nextInt(1001);
        if(rand_int3 > 500){
            maxFirst = true;
        }
        else{
            maxFirst = false;
        }

        gameEnded = false;

        
    }

    public char[][] getGrid(){
        return grid;
    }

    public boolean isMaxFirst(){
        return maxFirst;
    }

    public boolean isGameEnded(){
        if(this.isGridFull()){
            gameEnded = true;
        }

        return gameEnded;
    }

    public boolean coordsValid(int row, int col){
        if(this.grid[row][col] == ' '){
            return true;
        }

        return false;
    }

    public void play(int row, int col){
        this.grid[row][col] = 'O';
    }

    public boolean isGridFull(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public void printGrid(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                System.out.print("| " + grid[i][j] + " |");
            }
            System.out.println("\n--------------------");
        }
    }

    public int evaluate(char[][] grid){
        String win = "";

        /*
         * Check horizontally
         */
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j] == ' '){
                    win = "";
                    continue;
                }
                win += grid[i][j];
                if(win.contains("XOX")){
                    return 2;
                }
                if(win.contains("OXO")){
                    return -2;
                }
            }
            win = "";
        }
        win = "";

        /*
         * Check vertically
         */
        for(int j = 0; j < 4; j++){
            for(int i = 0; i < 3; i++){
                if(grid[i][j] == ' '){
                    win = "";
                    continue;
                }
                win += grid[i][j];
                if(win.contains("XOX")){
                    return 2;
                }
                if(win.contains("OXO")){
                    return -2;
                }
            }
            win = "";
        }
        win = "";

        /*
         * Check diagonally (\)
         */
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                if(grid[j][j+i] == ' '){
                    win = "";
                    continue;
                }
                win += grid[j][j+i];
                if(win.contains("XOX")){
                    return 2;
                }
                if(win.contains("OXO")){
                    return -2;
                }
            }
            win = "";
        }
        win = "";


        /*
         * Check diagonally (/)
         */
        for(int i = 0; i < 2; i++){
            for(int j = 2; j >= 0; j--){
                if(grid[j][2-j+i] == ' '){
                    win = "";
                    continue;
                }
                win += grid[j][2-j+i];
                if(win.contains("XOX")){
                    return 2;
                }
                if(win.contains("OXO")){
                    return -2;
                }
            }
            win = "";
        }
        win = "";

        if(this.isGridFull()){
            return 0;
        }

        return -1;
    }
    
    public int[] minimax(char[][] grid, boolean isMax, int steps){
        score[0] = evaluate(grid);
        score[1] = steps;
        
        if(score[0] != -1){

            return score;
        }
        
        if(isMax){
            score[0] = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'X';
                        int[] temp = minimax(grid, !isMax, steps++);
                        score[0] = Math.max(score[0],temp[0]);
                        grid[i][j] = ' ';
                    }
                }
            }
            
            return score;
        }
        else{
            score[0] = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'O';
                        int[] temp = minimax(grid, !isMax, steps++);
                        score[0] = Math.min(score[0], temp[0]);
                        grid[i][j] = ' ';
                    }
                }
            }
        
            return score;
        }
    }

    public void findBestMove(){
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        int bestSteps = Integer.MAX_VALUE;
        int[] bestMove = new int[2];

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j] == ' '){
                    grid[i][j] = 'X';
                    bestMove = minimax(grid, false, 1);
                    grid[i][j] = ' ';
                    
                    if((bestMove[0] >= bestValue && bestMove[1] <= bestSteps) || (bestMove[0] > bestValue)){
                        bestValue = bestMove[0];
                        bestSteps = bestMove[1];
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        System.out.println("Best move: (" + bestRow + ", " + bestCol);
        grid[bestRow][bestCol] = 'X';
    }
}
