package algorithms.maze3D;

import algorithms.search.AState;

import java.util.Objects;
/**Maze3DState extend the AState class. same as the MazeState class from the 2D Searchable maze.
 * @state - Position3D state. */
public class Maze3DState extends AState {
    private Position3D state;
    /** constructors"
     * first constructor:
     * is given a cost, and calls the father AState using super, and null's the state
     * second constructor:
     * given depth, row, column. calls the father's constructor and giving it a default cost of 10.
     * initializes the state with the depth, row and column.
     * third constructor:
     * given depth, row, column, papa, cost. call the super's constructor giving it the cost and the papa AState.
     * initialized the state with the depth, row and column.
     * fourth constructor:
     * given a position a Position3D, call the super constructor with a default cost =10 and initializes the state with the
     * position.
     * fifth constructor:
     * given a position3D position, papa AState and int cost. calls the super constructor giving it cost and papa.
     * inputs the state field with the position.*/
    public Maze3DState(int cost) {
        super(cost);
        state=null;
    }
    public Maze3DState(int depth, int row, int column){
        super(10);
        this.state = new Position3D(depth,row,column);
    }
    public Maze3DState(int depth, int row, int column, AState papa, int cost){
        super(cost,papa);
        this.state = new Position3D(depth,row,column);
    }
    public Maze3DState(Position3D position){
        super(10);
        this.state = position;
    }
    public Maze3DState(Position3D position, AState papa, int cost){
        super(cost,papa);
        this.state = position;
    }
    /**getter
     * returns the state a Position3D*/
    public Position3D getState(){return state;}
    @Override
    public String toString() {
        return this.state.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze3DState maze3dstate = (Maze3DState) o;
        return this.state.equals(maze3dstate.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }
}
