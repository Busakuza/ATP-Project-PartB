package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.Objects;
/** class MazeState extends AState - represents the state of each cell in the maze
 * @state - Position, the position of the cell in the maze.*/
public class MazeState extends AState{
    private Position state;
    /**constructor
     * first constructor:
     * @position - the position of a cell in the maze
     * @cost - the cost of arrival to the cell
     * second constructor:
     * @row - int, represents the row of the 2D maze
     * @col - int, represents the column of the 2D maze
     * @cost - the cost of arrival to the cell
     * third constructor:
     * the same arguments as the second constructor with an addition:
     * @papa - the father state from which we arrived to the cell.
     * */
    public MazeState(Position position, int cost) {
        super(cost);
        this.state = position;
    }

    public MazeState(int row, int col, int cost) {
        super(cost);
        this.state = new Position(row,col);
    }
    public MazeState(int row, int col, int cost, AState papa) {
        super(cost,papa);
        this.state = new Position(row,col);
    }
    /**getter
     * returns the state of the cell.
     * */
    public Position getState() {
        return state;
    }

    @Override
    public String toString() {
        return this.state.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MazeState mazestate = (MazeState) o;
        return this.state.equals(mazestate.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}

