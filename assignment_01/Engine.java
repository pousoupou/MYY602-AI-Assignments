package assignment_01;

import java.util.*;

public class Engine {
    private PriorityQueue<Node> frontier;
    private ArrayList<Node> explored;

    private Node startNode;
    private Node currentNode;

    public Engine(Maze maze){
        this.frontier = null;
        this.explored = new ArrayList<>();

        this.startNode = new Node(maze);
        this.currentNode = null;
    }

    public void searchUCS(){
        this.frontier = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.getCost(), n2.getCost());
            }
        });

        this.frontier.add(this.startNode);
        while(!this.frontier.isEmpty()){
            this.currentNode = this.frontier.poll();
            if(this.currentNode.getState().isGoal()){
                System.out.println("Goal found!");
                this.printPath();

                return;
            }

            ArrayList<Node> children = this.currentNode.expandNode();
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

            this.explored.add(this.currentNode);
        }
       
        System.out.println("No solution found.");
    }

    public void searchAStar(){
        this.frontier = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                return Integer.compare(n1.getE(), n2.getE());
            }
        });

        this.startNode.heuristic();
        this.frontier.add(this.startNode);
        while(!this.frontier.isEmpty()){
            this.currentNode = frontier.poll();
            if(this.currentNode.getState().isGoal()){
                System.out.println("Goal found!");
                this.printPath();
            
                return;
            }
            
            ArrayList<Node> children = this.currentNode.expandNode();
            for(Node child : children){
                boolean inExplored = false;
                
                for(Node exploredNode : this.explored){
                    if(exploredNode.equals(child)){
                        inExplored = true;
                        break;
                    }
                }
                
                if(!inExplored){
                    // Calculate heuristic for new node
                    child.heuristic();

                    boolean inFrontier = false;
                    Node nodeToRemove = null;
                    
                    for(Node frontierNode : this.frontier){
                        if(frontierNode.equals(child)){
                            inFrontier = true;
                            // Compare total estimated cost (f = g + h)
                            if(child.getE() < frontierNode.getE()){
                                nodeToRemove = frontierNode;
                            }
                            break;
                        }
                    }
                    
                    if(nodeToRemove != null){
                        this.frontier.remove(nodeToRemove);
                        this.frontier.add(child);
                    }
                    else if(!inFrontier){
                        this.frontier.add(child);
                    }
                }
            }
            
            this.explored.add(this.currentNode);
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
}
