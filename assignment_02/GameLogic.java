package assignment_02;

import java.util.Random;

public class GameLogic {
    private char[][] grid;
    private int row;
    private int col;
    private boolean maxFirst;
    private boolean gameEnded;

    public GameLogic(){
        grid = new char[3][4];
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
                if(win.equals("XOX")){
                    return 2;
                }
                if(win.equals("OXO")){
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
                if(win.equals("XOX")){
                    return 2;
                }
                if(win.equals("OXO")){
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
                if(win.equals("XOX")){
                    return 2;
                }
                if(win.equals("OXO")){
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
                if(win.equals("XOX")){
                    return 2;
                }
                if(win.equals("OXO")){
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
    
    public int minimax(char[][] grid, boolean isMax){
        int score = evaluate(grid);

        if(score == 2){
            return score;
        }
        
        if(isMax){
            int bestScore = Integer.MIN_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'X';
                        bestScore = Math.max(bestScore, minimax(grid, !isMax));
                        grid[i][j] = ' ';
                    }
                }
            }
            
            return bestScore;
        }
        else{
            int bestScore = Integer.MAX_VALUE;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 4; j++){
                    if(grid[i][j] == ' '){
                        grid[i][j] = 'O';
                        bestScore = Math.min(bestScore, minimax(grid, !isMax));
                        grid[i][j] = ' ';
                    }
                }
            }
        
            return bestScore;
        }
    }

    public void findBestMove(){
        int bestValue = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 4; j++){
                if(grid[i][j] == ' '){
                    grid[i][j] = 'X';
                    int moveValue = minimax(grid, false);
                    grid[i][j] = ' ';
                    
                    if(moveValue > bestValue){
                        bestValue = moveValue;
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
