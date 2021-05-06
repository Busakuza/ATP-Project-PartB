package algorithms.maze3D;

//import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
/** class Maze3D represents the 3D maze
 * @maze- 3D array of int
 * @startPosition - the start position of the 3D maze, a Position3D object
 * @goalPosition - the goal Position of the 3D maze, a Position3D object*/
public class Maze3D{
    private int[][][] maze;
    private Position3D startPosition;
    private Position3D goalPosition;
//------------------------------------------Maze3D Constructors---------------------------------------------------------
    /** constructors
     * first constructor:
     * is given a depth, row and column and initializes the maze. and puts the startPostion and goalPosition to null.
     * second constructor:
     * is given the start Position3D, goal Position3D and map a 3D int array, and initializes the fields accordingly.
     * */
    public Maze3D(int depth, int row, int col) {
        this.maze = new int[depth][row][col];
        startPosition = null;
        goalPosition = null;
    }
    public Maze3D(Position3D start, Position3D goal, int[][][] map){
        this.maze = map;
        this.startPosition = start;
        this.goalPosition = goal;
    }
//------------------------------------------Maze3D Constructors---------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------
//--------------------------------------- getters & setters for the private fields of Maze3D----------------------------
   /**getters -
    * first: returns the maze, the 3D int array
    * second: returns the startPosition of the maze, Position3D
    * third: returns the goalPosition of the maze, Position3D*/
    public int[][][] getMap() {
        return maze;
    }
    public Position3D getStartPosition() {
        return startPosition;
    }
    public Position3D getGoalPosition() {
        return goalPosition;
    }
    /** set the maze field, given a map of the maze a 3D int array*/
    public void setMaze(int[][][] maze) {
        this.maze = maze;
    }
    /** sets the start position, is given the depth row and column and sets the startPosition*/
    public void setStartPosition(int depth, int row, int col){
        this.startPosition = new Position3D(depth,row,col);
    }
    /**sets the start position, is given a start position and sets the field accordingly*/
    public void setStartPosition(Position3D start){this.startPosition = start;}
    /** sets the goal position is given the depth row and column and sets the field accordingly*/
    public void setGoalPosition(int depth, int row, int col) {
        this.goalPosition = new Position3D(depth,row,col);
    }
    /** sets the goal position is given the goal Position3D and sets the field accordingly*/
    public void setGoalPosition(Position3D goal){this.goalPosition = goal;}
    /** the function is given the depth, row, and value. sets the value of the cell int the maze*/
    public void setCellValue(int depth, int row, int col, int value){this.maze[depth][row][col] = value;}
//------------------------------------------- getters & setters for the private fields of Maze3D------------------------
    /** the function prints the maze, replaces the start position with "S" and the goal position with "E"
     * goes over the entire maze and prints it.
     * @path - StringBuilder, represents each 2D maze separately. every time the depth increases by 1 the path resets.
     * @seperator - a string of "-" to seperate between each 2D maze.
     * @depth - the depth of the maze
     * @row - the row of the maze in each depth.
     * @col - the column of the maze in each depth.*/
    public void print() {
        StringBuilder path = new StringBuilder(" ");
        String seperator = "";
        System.out.println("{");
        for (int depth = 0; depth < this.maze.length; depth++)
        {
            for (int row = 0; row < this.maze[0].length; row++)
            {
                for (int col = 0; col < this.maze[0][0].length; col++)
                {
                    if (this.getStartPosition().getRowIndex() == row && this.getStartPosition().getColumnIndex() == col && this.getStartPosition().getDepthIndex() == depth)
                        path.append("S ");
                    else if (this.getGoalPosition().getRowIndex() == row && this.getGoalPosition().getColumnIndex() == col && this.getGoalPosition().getDepthIndex() == depth)
                        path.append("E ");
                    else
                        path.append(this.get_Position_value(depth, row, col)).append(" ");
                }
                System.out.println("{" + path + "}");
                seperator = "{"+path+"}";
                path = new StringBuilder(" ");
            }
            if(depth<this.maze.length-1)
            {
                for(int i=0; i<seperator.length();i++)
                    System.out.print("-");
                System.out.println("");
            }
            seperator = "";
        }
        System.out.println("}");
    }
    /** returns the value of the current cell in the maze by the depth, row and column.*/
    private int get_Position_value(int depth, int row, int col) {
        return this.maze[depth][row][col];
    }
    /** The function is given a Position3D position which represents a certain cell in the maze,
     *  depth, row, column of a different ,would be neighbour, cell of the maze and checks if it's
     *  a valid neighbour(up,down,left,right,in,out) if not we return false
     *@position - Position3D, the position of a certain cel in the maze we want to find it's neighbours.
     * @depth - the depth of the maze.
     * @row - the row of the maze for each depth.
     * @col - the column of the maze for each depth.
     * @p_depth - the depth of the current position
     * @p_row - the row of the current position
     * @p_column - the column of the current position*/
    public boolean checkValidNeighbour(Position3D position, int depth, int row, int col) {
        int p_depth = position.getDepthIndex();
        int p_row = position.getRowIndex();
        int p_column = position.getColumnIndex();
        if((p_depth == 0 && depth == -1 ) || (p_depth == maze.length-1 && depth==maze.length) ||
                (p_row == 0 && row == -1 ) || (p_row == maze[0].length-1 && row==maze[0].length) ||
                (p_column == 0 && col == -1 ) || (p_column == maze[0][0].length-1 && col==maze[0][0].length))
            return false;
        else if (Math.abs(p_depth-depth+p_row-row+p_column-col)==1)
            return true;
        return false;
    }
    /**return an ArrayList of the neighbours of a given position.
     * we have an array of 6 Position3D object which represent an upper, down,left,right,in.out position from the current position.
     * for each position we sum it's depth, row and column with the depth, row, column of the current position respectively and
     * we check if it'd a valid neighbour via the "checkValidNeighbour" method from above.
     * @current - the current Position3D we want to find it's neighbours.
     * @neighbours - a Position3D arraylist of the neighbours of the current position.
     * @c_depth - the depth of the current position.
     * @c_row - the row of the current position corresponds to it's depth.
     * @c_column - the column of the current position corresponds to it's depth.
     * @positions - a Position3D array of 6 possible positions we can find to the current cell by adding their respective fields
     * to the current position and the creating a new Position with the sum of the respective fields.
     * @neighbourCell - the new Position3D a valid neighbour to the current position with dept, row, and column fields are the sum
     * of the depth, row column of the current position and a p_neighbour position from the Position3D array.
     * @return - return the neighbours of the current position.
     * */
    public ArrayList<Position3D> getNeighbourCells(Position3D current){
        ArrayList<Position3D> neighbours = new ArrayList<Position3D>();
        int c_depth = current.getDepthIndex(), c_row = current.getRowIndex(), c_column = current.getColumnIndex();
        Position3D[] positions = {
                new Position3D(0,1,0),//   down
                new Position3D(0,0,-1),// left
                new Position3D(0,0,1),//   right
                new Position3D(1,0,0),//    in
                new Position3D(0,-1,0),//  up
                new Position3D(-1,0,0),//  out
        };
        for (Position3D p_Neighbour: positions)
        {
            Position3D neighbourCell = new Position3D(
                    c_depth+p_Neighbour.getDepthIndex()
                    ,c_row+p_Neighbour.getRowIndex(),
                    c_column+p_Neighbour.getColumnIndex());
            if(checkValidNeighbour(current, neighbourCell.getDepthIndex(),neighbourCell.getRowIndex(),neighbourCell.getColumnIndex()))
                    neighbours.add(neighbourCell);

        }
        return neighbours;
    }
}
