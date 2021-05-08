package algorithms.mazeGenerators;
/**The class Maze responsible for creating two-dimensional maze.
 */
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;

public class Maze implements Serializable {
    /**number of columns in maze */
    private int col;

    /**number of rows in maze */
    private int row;
    /** The start position in maze */
    private Position StartPosition;
    /** The goal position in maze */
    private Position GoalPosition;
    /**A two-dimensional array that represents the mazes*/
    private final int [][] maze;
    /**constructor of Class Maze:  create a new two-dimensional maze with size: (col*row)
     //* @param row - number of rows in maze
     //* @param col - number of columns in maze
     */
    public Maze(byte[] data) {
        row = 0;
        col = 0;
        // init the row & col
        for (int i = 0; i < 8; i++) {
            row += data[i];
            col += data[i + 8];
        }
        maze = new int[row][col];
        int i = 16;
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                maze[r][c] = data[i++];
            }
        }
        int sumColStart = 0, sumRowStart = 0;
        for (int sr = 0; sr < 8; sr++) {
            sumRowStart += (int) data[i];
            sumColStart += (int) data[i + 8];
            i++;
            ;
        }
        i += 8;
        StartPosition = new Position(sumRowStart, sumColStart);
        int sumRowGoal = 0, sumColGoal = 0;
        for (int gr = 0; gr < 8; gr++) {
            sumRowGoal += (int) data[i];
            sumColGoal += (int) data[i + 8];
            i++;
        }
        GoalPosition = new Position(sumRowGoal, sumColGoal);
    }
    public Maze(int row, int col) {
        this.col = col;
        this.row = row;
        this.StartPosition = null;
        this.GoalPosition = null;
        this.maze = new int[row][col];
    }
    /**
     *function getStartPosition: no inputs
     *@return Position, the Start position of maze
     */
    public Position getStartPosition() {
        return StartPosition;
    }
    /**
     *function getGoalPosition: no inputs
     *@return Position, the goal position of maze
     */
    public Position getGoalPosition() {
        return GoalPosition;
    }
    /**
     *function getMaze: no inputs
     *@return two-dimensional array
     */
    public int[][] getMaze() {
        return maze;
    }
    /**
     *function getCol: no inputs
     *@return integer, number of column in maze
     */
    public int getCol() {
        return col;
    }
    /**
     *function getRow: no inputs
     *@return integer, number of rows in maze
     */
    public int getRow() {
        return row;
    }
    /**function setStartPosition: get values for start position
     *and update the StartPosition of maze accordingly.
     * @param p_row ,Integer ,number of row from maze
     * @param p_col ,Integer , number of column from maze
     */
    public void setStartPosition(int p_row, int p_col) {
        StartPosition = new Position(p_row,p_col);
    }
    /**function setGoalPosition: get values for goal position
     *and update the GoalPosition of maze accordingly.
     * @param p_row ,Integer ,number of row from maze
     * @param p_col ,Integer , number of column from maze
     */
    public void setGoalPosition(int p_row, int p_col) { GoalPosition = new Position(p_row, p_col);
    }
    /**function get_Position_value: gets two inputs: number of row and number of column from maze
     *and return the value from maze in cell [row][col] .
     * @param row ,Integer ,number of row from maze
     * @param col ,Integer , number of column from maze
     * @return Cell [row][col], Integer (0 or 1)
     */
    public int get_Position_value(int row, int col)
    {
        return this.maze[row][col];

    }
    /**function setCol: update  column of maze accordingly.
     * @param col ,Integer , number of column from maze
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**function setRow: update  row of maze accordingly.
     * @param row ,Integer , number of column from maze
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**function print: print the maze to screen.
     * no inputs
     */
    public void print(){
        StringBuilder path = new StringBuilder(" ");
//        path.append("{");
        for (int row=0; row<this.maze.length; row++)
        {
            for(int col=0; col<this.maze[0].length ;col++)
            {
                if (this.getStartPosition().getRowIndex()==row && this.getStartPosition().getColumnIndex()==col)
                {
                    path.append("S ");
                }
                else if(this.getGoalPosition().getRowIndex()==row &&this.getGoalPosition().getColumnIndex()==col)
                {
                    path.append("E ");
                }
                else
                    path.append( this.get_Position_value(row, col)).append(" ");
            }
            System.out.println("{" + path + "}");
            path = new StringBuilder(" ");
        }
        path.append("{");
    }

    /**function SetPosition:update the value from maze in cell [row][col] to be value.
     * @param row ,Integer ,number of row from maze
     * @param col ,Integer , number of column from maze
     * @param value ,Integer
     */
    public void SetPosition(int row, int col, int value)
    {
        this.maze[row][col]=value;
    }
    /**function cell_exist:return true if cell [row][col] exist in maze else false.
     * @param row ,Integer ,number of row from maze
     * @param col ,Integer , number of column from maze
     * @param maze ,two-dimensional array of integer.
     * @return true or false accordingly
     */
    public boolean cell_exist(int row, int col, int[][]maze)
    {
        if (row<0 || row>maze.length-1)
            return false;
        return col >= 0 && col <= maze[0].length - 1;
    }

    /**function sum_neighbors:return the number of cells located next to cell[row][col] in maze.
     * @param row ,Integer ,number of row from maze
     * @param col ,Integer , number of column from maze
     * @param maze ,two-dimensional array of integer.
     * @return integer, number of neighbors of cell[row][col]
     */
    public int sum_neighbors(int row, int col, int[][]maze)
    {
        int sum_neighbors=4;
        if (row==0 || row==maze.length-1)
            sum_neighbors--;
        if (col==0 || col==maze[0].length-1)
            sum_neighbors--;
        return sum_neighbors;
    }
    /**function sum_neighbors:return true if most of the neighbors surrounding the cell
     *contain wall(the integer 1) else return false.
     * @param row ,Integer ,number of row from maze
     * @param col ,Integer , number of column from maze
     * @param maze ,two-dimensional array of integer.
     * @return true or false accordingly.
     */
    public boolean must_neighbors_is_walls(int row, int col, int[][]maze)
    {
        int sum_neighbors=sum_neighbors(row, col, maze);
        int sum_wall_neighbors=0;
        int slant_count=0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (cell_exist(row + x, col + y, maze)) {
                    if (x == 0 && y == 0)
                        continue;
                    else if (x != 0 && y != 0 && maze[row + x][col + y] == 1)
                        slant_count++;
                    if (cell_exist(row + x, col + y, maze) && get_Position_value(row + x, col + y) == 1)
                        sum_wall_neighbors++;
                }
            }
        }
        if (slant_count>=2)
            return true;
        else if (sum_neighbors == 2)
        {
            if (sum_wall_neighbors>=1)
                return true;
        }
        else if  (sum_neighbors == 3)
        {
            if (sum_wall_neighbors>=2)
                return true;
        }
        else if  (sum_neighbors == 4)
        {
            if (sum_wall_neighbors>=3)
                return true;
        }
        return false;
    }
    /** remember to check for mikre kaze & error handling
     * the byte array contains:
     * 0:7 row
     * 8:15 col
     * 16:maze2DSize+16 maze info
     * start_row
     * start_col
     * goal_row
     * goal_col
     * */

    public byte[] toByteArray(){
        byte[] mazeByteArray = new byte[16+row*col+32];
        int c_row = row, c_col = col,start_row = StartPosition.getRowIndex(),
                start_col = StartPosition.getColumnIndex(),goal_row = GoalPosition.getRowIndex(), goal_col = GoalPosition.getColumnIndex(),i=0;
        while(c_row>127){
            mazeByteArray[i++] =127;
            c_row-=127;
        }
        mazeByteArray[i] = (byte)c_row;
        i=8;// starting the column
        while(c_col>127){
            mazeByteArray[i++] = 127;
            c_col-=127;
        }
        mazeByteArray[i] = (byte)c_col;
        i = 16;// maze info
        for(int x = 0; x< row; x++){
            for(int y =0 ; y < col; y++){
                mazeByteArray[i++] = (byte)maze[x][y];
            }
        }
        // starting position
        //i=8+8+row*col;
        while(start_row > 127){
            mazeByteArray[i++] = (byte) 127;
            start_row-=(byte)127;
        }
        mazeByteArray[i] = (byte)start_row;
        i = 8+8+ row*col+8 ;
        while(start_col > 127){
            mazeByteArray[i++] =(byte) 127;
            start_col-=(byte)127;
        }
        mazeByteArray[i] = (byte)start_col;
        // goal position
        i = 8+8+ row*col+8+8;
        while(goal_row > 127){
            mazeByteArray[i++] = 127;
            goal_row-=127;
        }
        mazeByteArray[i] = (byte)goal_row;
        i = 8+8+ row*col+8+8+8;
        while(goal_col > 127){
            mazeByteArray[i++] = 127;
            goal_col-=127;
        }
        mazeByteArray[i] = (byte) goal_col;
        return mazeByteArray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maze maze1 = (Maze) o;
        return Arrays.equals(maze, maze1.maze);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(toByteArray());
    }



}