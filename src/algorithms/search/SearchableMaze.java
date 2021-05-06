package algorithms.search;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
//
import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze myMaze;

    public SearchableMaze(Maze myMaze) {
        this.myMaze = myMaze;
    }

    /**getters
     * getStartState - returns the start state of the searchable problem
     * @positon - the start position of the 2D maze
     * @start - new MazeState object which represents the start state
     * getGoalState - returns the goal state of the searchable problem
     * @position- the goal position of the 2D maze
     * @goal - MazeState object whicg rpresents the goal state*/
    @Override
    public AState getStartState() {
        Position position = myMaze.getStartPosition();
        MazeState start = new MazeState(position, 0);
        return start;

    }
    @Override
    public AState getGoalState() {
        Position position = this.myMaze.getGoalPosition();
        MazeState goal = new MazeState(position, 0);
        return goal;
    }

    /** outOfBounds - is given integers row and col as arguments which represent indexes
     * in the 2D maze and checks whether the indexes are out of bound.
     * another check if the value of the cell is "1" which represents a wall.
     * returns true if the one of the above conditions are met, or false if not.
     * @row - represents the index of the row of the 2D maze
     * @col - represents the index of the column of the 2D maze*/
    public boolean outOfBounds(int row, int col) {
        if (row >= this.myMaze.getMaze().length || row < 0 || col < 0 || col >= this.myMaze.getMaze()[0].length)
            return true;
        else if(myMaze.getMaze()[row][col]==1)
            return true;
        return false;
    }
    @Override
/** getAllSuccessors - returns an ArrayList of "valid" successors to a given Astate.
 * the "valid" successors must meet the conditions of the outOfBound method above
 * we create an array of 8 possible MazeState which represent cells horizontal,vertical and diagonal from the current
 * state. each time we sum the indexes of the current state with one of the 8 possible neighbours and check if they're
 * not out of bound. if not we add a new MazeState which represents the correct neighbouring cell to the current cell.
 * @s - the current AState of the maze
 * @successors - an ArrayList of valid successors to the current AState.
 * @mazeState - casting to s to be a MazeState
 * @position - the state of the mazeState
 * @row - the row index of position
 * @col - the column index of the position
 * @possibleNeighborCells - an Array of MazeState which contain 8 possible neighbouring cells. which we add their indexes
 * to the current state to represent correctly the neighbour cell.*/
    public ArrayList<AState> getAllSuccessors(AState s) {
        ArrayList<AState> successors = new ArrayList<AState>();
        MazeState mazestate = (MazeState) s;
        Position position = mazestate.getState();
        int row = position.getRowIndex();
        int col = position.getColumnIndex();
        MazeState[] possibleNeighbourCells = {
                new MazeState(-1,0,10),//up
                new MazeState(1,0,10),//down
                new MazeState(0,-1,10),//left
                new MazeState(0,1,10),//right
                new MazeState(-1,-1,15),//DiagonalUpLeft
                new MazeState(-1,1,15),//diagonalUpRight
                new MazeState(1,-1,15),//diagonalDownLeft
                new MazeState(1,1,15)//DiagonalDownRight

        };
        for (MazeState neighbour: possibleNeighbourCells) {
            if(!outOfBounds(row+neighbour.getState().getRowIndex(),col+neighbour.getState().getColumnIndex()))
                successors.add(new MazeState(row+neighbour.getState().getRowIndex(),
                        col+neighbour.getState().getColumnIndex(),
                        neighbour.getCost() + mazestate.getCost(),mazestate));
        }

        return successors;
    }
}
