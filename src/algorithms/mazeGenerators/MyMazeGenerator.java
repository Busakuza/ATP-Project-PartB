package algorithms.mazeGenerators;
import java.util.ArrayList;
/**
 * class MyMazeGenerator: is extends the abstract class AMazeGenerator and create a randomized solvable maze
 * according to Randomized Prim's algorithm, this maze contains the integers: 1 or 0 (1=wall , 0=path).
 * */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * function generate: get two inputs: row, col and return a random maze with size row*col
     * @param row ,Integer ,number of rows
     * @param col ,Integer , number of columns
     * @return Maze.
     * */
    public Maze generate(int row, int col) {
        Maze maze = new Maze(row, col);
        // iterate through direct neighbors of node
        ArrayList<Position> frontier = new ArrayList<>();
        boolean[][] visited_points = new boolean[row][col];
        // build maze and initialize with only walls
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                maze.SetPosition(x, y, 1);
                visited_points[x][y] = false;
            }
        }
        // select random point and open as start node
        Position p = new Position((int) 0, (int) col/2);
        int p_row = p.getRowIndex();
        int p_col = p.getColumnIndex();
        maze.setStartPosition(p_row, p_col);
        maze.SetPosition(p_row, p_col, 0);
        frontier.add(new Position(p_row, p_col));
        while (!frontier.isEmpty()) {
            // pick current node at random
            Position current = frontier.remove((int) (Math.random() * frontier.size()));
            int cur_row = current.getRowIndex();
            int cur_col = current.getColumnIndex();
            visited_points[cur_row][cur_col] = true;
            if (maze.must_neighbors_is_walls(cur_row, cur_col, maze.getMaze()))
                maze.SetPosition(cur_row, cur_col, 0);
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 || x != 0 && y != 0)
                        continue;
                    if (maze.cell_exist(cur_row + x, cur_col + y, maze.getMaze()) && !visited_points[cur_row + x][cur_col + y]) {
                        frontier.add(new Position(cur_row + x, cur_col + y));
                    }
                }
            }
            // if algorithm has resolved, mark end node
            if (frontier.isEmpty()) {
                maze.SetPosition(cur_row, cur_col, 0);
                maze.setGoalPosition(cur_row, cur_col);
            }
        }
        return maze;
    }
}

