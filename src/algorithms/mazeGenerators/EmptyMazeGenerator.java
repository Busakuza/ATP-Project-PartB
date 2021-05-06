package algorithms.mazeGenerators;
/**
 * class EmptyMazeGenerator: is extends the abstract class AMazeGenerator and create a maze of zero's
 * this class implements the function generate.
 * */
public class EmptyMazeGenerator extends AMazeGenerator{

    /**
     * function generate: get two inputs: row, col and return a maze with size row*col of zero's
     * @param row ,Integer ,number of rows
     * @param col ,Integer , number of columns
     * @return Maze of Zero's.
     * */
    @Override
    public Maze generate(int row, int col) {
        Maze maze=new Maze(row,col);
//        for (int i=0; i<row; i++)
//        {
//            for (int j=0; j<col; j++)
//            {
//                maze.SetPosition(i,j,0);
//
//            }
//        }
        maze.setStartPosition(0,0);
        maze.setGoalPosition(row-1,col-1);
        return maze;
    }


}
