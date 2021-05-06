
package algorithms.mazeGenerators;
import java.util.Random;
/**
 * class SimpleMazeGenerator: is extends the abstract class AMazeGenerator and create a simple randomized maze
 * this maze contains the integers: 1 or 0 (1=wall , 0=path).
 * */
public class SimpleMazeGenerator extends AMazeGenerator{

    /**
     * function generate: get two inputs: row, col and return a maze with size row*col
     * @param row ,Integer ,number of rows
     * @param col ,Integer , number of columns
     * @return Maze.
     * */
    @Override
    public Maze generate(int row, int col) {
        Random rand = new Random();
        Maze maze=new Maze( row, col);
        maze.setStartPosition(((int)Math.random() * row), (int) (Math.random() * col));
        if (row==1 && col==1)
            maze.setGoalPosition(maze.getStartPosition().getRowIndex(),maze.getGoalPosition().getColumnIndex());
        else {
            maze.setGoalPosition(((int)Math.random() * row), (int) (Math.random() * col));
            while (maze.getStartPosition().Compare(maze.getGoalPosition()))
            {
                maze.setGoalPosition(((int)Math.random() * row), (int) (Math.random() * col));
            }
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    int rand_int1 = rand.nextInt(5);
                    if (rand_int1 % 5 != 0)
                        maze.SetPosition(i, j, 0);
                    else
                        maze.SetPosition(i, j, 1);
                }
            }
        }
        return maze;
    }
}
