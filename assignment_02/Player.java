package assignment_02;

public class Player {
    private char player;
    private boolean isMax;

    public Player(char player){
        this.player = player;
        this.isMax = false;        

        if(player == 'X'){
            this.isMax = true;
        }
    }

    public char getPlayer(){
        return player;
    }

    public boolean isMax(){
        return this.isMax;
    }
}
