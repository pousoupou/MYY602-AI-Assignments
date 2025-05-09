/*
 * Papaspyros Stylianos 5162
 * Bouzas Ioannis 5025
 */

import java.util.*;

public class Engine {
    private PriorityQueue<Node> frontier;
    private ArrayList<Node> explored;

    private Node startNode;
    private Node currentNode;

    private int expantionCounter;

    public Engine(Maze maze){
        this.frontier = null;
        this.explored = null;

        this.startNode = new Node(maze);
        this.currentNode = null;
    }

    public void searchUCS(){
        expantionCounter = 0;
        this.frontier = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.getCost(), n2.getCost());
            }
        });
        this.explored = new ArrayList<>();
        
        this.frontier.add(this.startNode);
        while(!this.frontier.isEmpty()){
            this.currentNode = this.frontier.poll();
            if(this.currentNode.getState().isGoal()){
                System.out.println("Goal found!");
                this.printInfo();
                
                return;
            }
            
            this.explored.add(this.currentNode);

            ArrayList<Node> children = this.currentNode.expandNode();
            expantionCounter++;
            for(Node child : children){
                boolean inExplored = false;

                for(Node exploredNode : this.explored){
                    if(exploredNode.equals(child)){
                        inExplored = true;
                
                        break;
                    }
                }

                if(!inExplored){
                    // Check if state is in frontier
                    boolean inFrontier = false;
                    Node nodeToRemove = null;

                    for(Node frontierNode : this.frontier){
                        if(frontierNode.equals(child)){
                            inFrontier = true;
                            // If we found a better path to this state
                
                            if(child.getCost() < frontierNode.getCost()){
                                nodeToRemove = frontierNode;
                            }
                
                            break;
                        }
                    }

                    if(nodeToRemove != null){
                        // Better path found, update frontier
                        this.frontier.remove(nodeToRemove);
                        this.frontier.add(child);
                    }
                    else if(!inFrontier){
                        // New state, add to frontier
                        this.frontier.add(child);
                    }
                }
            }
        }
       
        System.out.println("No solution found.");
    }

    public void searchAStar(){
        expantionCounter = 0;
        this.frontier = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.getE(), n2.getE());
            }
        });
        this.explored = new ArrayList<>();

        this.frontier.add(this.startNode);
        while(!this.frontier.isEmpty()){
            this.currentNode = this.frontier.poll();
            if(this.currentNode.getState().isGoal()){
                System.out.println("Goal found!");
                this.printInfo();
                
                return;
            }
            
            this.explored.add(this.currentNode);

            ArrayList<Node> children = this.currentNode.expandNode();
            expantionCounter++;
            for(Node child : children){
                boolean inExplored = false;

                child.heuristic();

                for(Node exploredNode : this.explored){
                    if(exploredNode.equals(child)){
                        inExplored = true;
                
                        break;
                    }
                }

                if(!inExplored){
                    // Check if state is in frontier
                    boolean inFrontier = false;
                    Node nodeToRemove = null;

                    for(Node frontierNode : this.frontier){
                        if(frontierNode.equals(child)){
                            inFrontier = true;
                            // If we found a better path to this state
                            if(child.getE() < frontierNode.getE()){
                                nodeToRemove = frontierNode;
                            }
                
                            break;
                        }
                    }

                    if(nodeToRemove != null){
                        // Better path found, update frontier
                        this.frontier.remove(nodeToRemove);
                        this.frontier.add(child);
                    }
                    else if(!inFrontier){
                        // New state, add to frontier
                        this.frontier.add(child);
                    }
                }
            }
        }
       
        System.out.println("No solution found.");
    }

    public ArrayList<Node> getPath(){
        ArrayList<Node> path = new ArrayList<>();
        Node current = this.currentNode;
        while(current != null){
            path.add(current);
            current = current.getParent();
        }
        
        Collections.reverse(path);

        return path;
    }

    public void printPath(){
        ArrayList<Node> path = getPath();
        System.out.println("Path:");
        for(Node node : path){
            System.out.println(node.getState().getRobotPosition()[0] + ", " + node.getState().getRobotPosition()[1]);
            // node.getState().printMaze();
        }
    }

    public void printInfo(){
        System.out.println("Cost: " + this.currentNode.getCost());
        System.out.println("Expanded nodes: " + expantionCounter);
        this.printPath();
        System.out.println();
    }
}
