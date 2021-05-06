package algorithms.maze3D;

import java.util.Objects;
/**Position3D class represents the position of a cell in the maze
 * @depth - the depth of the cell in the maze
 * @row - the row of the cell in it's depth.
 * @column - the column of the cell in it's depth.*/
public class Position3D {
    private int depth;
    private int row;
    private  int column;
    /** constructor
     * given depth row and column and initializes the respective fields.*/
    public Position3D(int depth, int row, int col) {
        this.depth=depth;
        this.row=row;
        this.column=col;
    }
    /**gets the row field*/
    public int getRowIndex() {
        return row;
    }
    /**sets the row field*/
    public void setRowIndex(int p3_r) {
        this.row = p3_r;
    }
    /**gets the column field*/
    public int getColumnIndex() {
        return column;
    }
    /**sets the column field*/
    public void setColumnIndex(int p3_c) {
        this.column = p3_c;
    }
    /** returns the depth field*/
    public int getDepthIndex() {
        return depth;
    }
    /** sets the depth field*/
    public void setDepthIndex(int p3_d) {
        this.depth = p3_d;
    }
    /** the string representation of Position3D*/
    @Override
    public String toString() {
        return "{" +depth +
                "," + row +
                "," + column +
                '}';
    }
    /**equals function of Position 3D*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position3D that = (Position3D) o;
        return row == that.row && column == that.column && depth == that.depth;
    }
    /**hashcode of Position3D*/
    @Override
    public int hashCode() {
        return Objects.hash(row, column, depth);
    }
}
