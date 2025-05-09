/*
 * Papaspyros Stylianos 5162
 * Bouzas Ioannis 5025
 */

import java.util.Random;

public class GameLogic {
    private static char[][] grid = new char[3][4];
    private int row;
    private int col;
    private boolean gameEnded;

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

        grid[rand_int1 % 3][rand_int1 / 4] = 'X';
        grid[rand_int2 % 3][rand_int2 / 4] = 'O';

        gameEnded = false;
    }

    public char[][] getGrid(){
        return grid;
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
    
    public int minimax(char[][] grid, boolean isMax, int depth){
        int score = evaluate(grid);

        if(score != -1){
            if(score == 2){
                return score - depth;
            }
            else if(score == -2){
                return score + depth;
            }
            else{
                return score;
            }
        }
        
        if(isMax){
            int best = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'X';
                        best = Math.max(best, minimax(grid, !isMax, depth+1));
                        grid[i][j] = ' ';
                    }
                }
            }
            
            return best;
        }
        else{
            int best = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'O';
                        best = Math.min(best, minimax(grid, !isMax, depth+1));
                        grid[i][j] = ' ';
                    }
                }
            }
        
            return best;
        }
    }

    public void findBestMove(){
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        int bestSteps = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j] == ' '){
                    grid[i][j] = 'X';
                    int moveValue = minimax(grid, false, 0);
                    grid[i][j] = ' ';

                    if(moveValue > bestValue){
                        bestValue = moveValue;
                        bestSteps = moveValue;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        System.out.println("Best move: (" + bestRow + ", " + bestCol + ")");
        grid[bestRow][bestCol] = 'X';
    }
}
