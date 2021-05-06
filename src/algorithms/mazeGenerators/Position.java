package algorithms.mazeGenerators;

import java.util.Objects;

/**
 * The class Position responsible for creating Position,
 * the position represents a cell in maze ,it has a p_row (number of the row) and p_col (number of the column).
 * */
public class Position {
    //the row of position
    private int p_row;
    //the column of position
    private int p_col;

    /**constructor of Class Position: create a new Position.
     * @param row - the number of row of position
     * @param col - the number of column of position
     * @return Position
     */
    public Position(int row, int col) {
        this.p_row = row;
        this.p_col = col;
    }

    /**copy constructor of Class Position.
     * @param other, another Position
     * @return Position
     */
    public Position (Position other){
        this.p_row = other.p_row;
        this.p_col = other.p_col;
    }

    /**function toString: no inputs, return a string of the values: p_row,p_col of Position.
    *@return String
    * */
    @Override
    public String toString() {
        return "{" +p_row + "," + p_col + "}";
    }

    /**
     *function getRowIndex: no inputs
     *@return p_row, integer, the row index of position.
     */
    public int getRowIndex() {
        return p_row;
    }

    /**
     *function getColumnIndex: no inputs
     *@return p_col, integer, the column index of position.
     */
    public int getColumnIndex() {
        return p_col;
    }

    /**function Compare: get other position and return true if the current position and other position is equal
     *else return false .
     * @param other, another Position
     * @return true or false accordingly.
     */
    public boolean Compare(Position other)
    {
        if (this.getRowIndex()==other.getRowIndex()  && this.getColumnIndex()==other.getColumnIndex() )
            return true;
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return p_row == position.p_row && p_col == position.p_col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p_row, p_col);
    }
}

