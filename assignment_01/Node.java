package assignment_01;

import java.util.ArrayList;

public class Node {
    private Node parent;
    private ArrayList<Maze> children;
    private double heuristicValue;
    private double cost;

    public Node(ArrayList<Maze> state){
        this.parent = null;
        this.children = new ArrayList<>();
        this.heuristicValue = 0.0;
        this.cost = 0.0;

        for (Maze maze : state) {
            this.children.add(maze);
        }
    }

    public Node(ArrayList<Maze> state, Node parent){
        this.parent = parent;
        this.children = new ArrayList<>();
        this.heuristicValue = 0.0;
        this.cost = parent.getCost();

        for (Maze maze : state) {
            this.children.add(maze);
        }
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(double heuristicValue) {
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

    
}
