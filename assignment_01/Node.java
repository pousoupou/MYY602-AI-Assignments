package assignment_01;

import java.util.ArrayList;

public class Node {
    private Node parent;
    private ArrayList<Maze> mazes;
    private int heuristicValue;
    private int cost;

    public Node(ArrayList<Maze> state){
        this.parent = null;
        this.mazes = new ArrayList<>();
        this.heuristicValue = 0;
        this.cost = 0;

        for (Maze maze : state) {
            this.mazes.add(maze);
        }
    }

    public Node(ArrayList<Maze> state, Node parent){
        this.parent = parent;
        this.mazes = new ArrayList<>();
        this.heuristicValue = 0;
        this.cost = parent.getCost();

        for (Maze maze : state) {
            this.mazes.add(maze);
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public double getE(){
        return heuristicValue + cost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Maze> getMazes() {
        return mazes;
    }

    public ArrayList<Node> expandNode(){
        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Maze> moves = new ArrayList<>();
        Node childNode;

        for(Maze maze : mazes){
            moves = maze.getRobotMoveList(maze);
            for(Maze move : moves){
                childNode = new Node(moves, this);
                
                if(move.isTeleported()){
                    childNode.setCost(2);
                }
                else{
                    childNode.setCost(1);
                }

                result.add(childNode);
            }
        }
    }

    public int heuristic(Maze maze){
        int[] robotPosition = maze.getRobotPosition();
        int[] goalPosition = maze.getGoalPosition();

        int xDistance = Math.abs(robotPosition[0] - goalPosition[0]);
        int yDistance = Math.abs(robotPosition[1] - goalPosition[1]);
        int h = xDistance + yDistance;
        
        this.setHeuristicValue(h);

        return h;
    }
}
