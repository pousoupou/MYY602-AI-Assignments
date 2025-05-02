import java.util.ArrayList;

public class Node {
    private Node parent;
    private Maze state;
    private int heuristicValue;
    private int cost;

    public Node(Maze state){
        this.parent = null;
        this.state = state;
        this.heuristicValue = 0;
        this.cost = 0;
    }

    public Node(Maze state, Node parent){
        this.parent = parent;
        this.state = state;
        this.heuristicValue = 0;
        this.cost = parent.getCost();
    }

    public Maze getState(){
        return this.state;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristicValue() {
        return this.heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    public int getE(){
        return this.heuristicValue + this.cost;
    }

    public Node getParent() {
        return this.parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> expandNode(){
        ArrayList<Node> result = new ArrayList<>();
        ArrayList<Maze> moves = new ArrayList<>();
        Node childNode;

        moves = this.state.getPossibleMoves();
        for(Maze move : moves){
            childNode = new Node(move, this);
            
            if(move.isTeleported()){
                childNode.setCost(this.getCost() + 2);
            }
            else{
                childNode.setCost(this.getCost() + 1);
            }

            result.add(childNode);
        }

        return result;
    }

    public int heuristic(){
        int[] robotPosition = this.state.getRobotPosition();
        int[] goalPosition = this.state.getGoalPosition();
        int N = this.state.getMaze().length;

        // Robot to Goal distance
        int xStdDistance = Math.abs(robotPosition[0] - goalPosition[0]);
        int yStdDistance = Math.abs(robotPosition[1] - goalPosition[1]);
        int stdDist = Math.max(xStdDistance, yStdDistance); // Chebyshev distance

        // Robot to Goal through Top-Left Teleportation distance
        int LeftTeleportDist = Math.max(robotPosition[0], robotPosition[1]);
        LeftTeleportDist += 2;
        LeftTeleportDist += Math.max(Math.abs(goalPosition[0] - (N-1)), Math.abs(goalPosition[1] - (N-1)));

        // Robot to Goal through Bottom-Right Teleportation distance
        int RightTeleportDist = Math.max(Math.abs(robotPosition[0] - (N-1)), Math.abs(robotPosition[1] - (N-1)));
        RightTeleportDist += 2;
        RightTeleportDist += Math.max(goalPosition[0], goalPosition[1]);

        this.heuristicValue = Math.min(stdDist, Math.min(LeftTeleportDist, RightTeleportDist));

        return heuristicValue;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        
        Node otherNode = (Node) obj;
        int[] thisPos = this.state.getRobotPosition();
        int[] otherPos = otherNode.state.getRobotPosition();
        
        return thisPos[0] == otherPos[0] && thisPos[1] == otherPos[1] && this.state.isTeleported() == otherNode.state.isTeleported();
    }

    @Override
    public int hashCode(){
        int[] pos = this.state.getRobotPosition();
        return 31 * (31 * pos[0] + pos[1]) + (state.isTeleported() ? 1 : 0);
    }
}
