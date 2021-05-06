package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;
/**searchable Maze3D implements ISearchable.
 * @maze3D - a Maze3D object*/
public class SearchableMaze3D implements ISearchable {
    private Maze3D maze3D;
    /**constructor
     * given a maze Maze3D object and initializes it's field*/
    public SearchableMaze3D(Maze3D maze){
        this.maze3D = maze;
    }
    /**return the startState which is a Maze3DState*/
    @Override
    public AState getStartState() {
        return new Maze3DState(this.maze3D.getStartPosition());
    }
    /**returns the goal state which is a Maze3DState.*/
    @Override
    public AState getGoalState() {
        return new Maze3DState(this.maze3D.getGoalPosition());
    }
    /**return a List of all the valid successors to the s AState.
     * first we get the possibleNeighbours of s by calling the method getNeighbourCells of the maze
     * for each neighbour we check if it's value is 0 which represents a path. if it is then we insert a new MazeState
     * to the list which is a neighbor/successor to s.
     * @s - AState the cell we want to find it's successors.
     * @currentCell - MazeState3D casting of s
     * @currentPosition - the state of the currentCell
     * @p_Successors - ArrayList Position3D of valid neighbours to currentPosition.
     * @successors - an ArrayList of AStates that contains MazeState3D neighbours to the current cell.
     * @return - return the successors*/
    @Override
    public ArrayList<AState> getAllSuccessors(AState s) {
        Maze3DState currentCell = (Maze3DState) s;
        Position3D currentPosition = currentCell.getState();
        ArrayList<Position3D> p_Successors = maze3D.getNeighbourCells(currentPosition);
        ArrayList<AState> successors = new ArrayList<AState>();
        for (Position3D successor:p_Successors) {
            if(maze3D.getMap()[successor.getDepthIndex()][successor.getRowIndex()][successor.getColumnIndex()]== 0)
                successors.add(new Maze3DState(successor,currentCell, currentCell.getCost()+10));

        }
        return successors;
    }
}
