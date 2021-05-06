package algorithms.maze3D;
/** abstract class which implements IMazeGenerator3D.
 * implements the measureAlgorithmTimeMillis and each class that will extends it will implement the generate method.*/
public abstract class AMaze3DGenerator implements IMazeGenerator3D {
    /** returns the time in ms for the generation of a 3D maze.
     * @depth - the depth of the maze, int.
     * @row - the row of the maze in each depth, int.
     * @column - the column of the maze in each depth, int.
     * @time_before - The time before calling the generate method, long.
     * @time_after  - The time after the calling the generate method.
     * @return - returns the differens between the time_after to the time_before to find the time it took to generate
     * the 3D maze.*/
    @Override
    public long measureAlgorithmTimeMillis(int depth, int row, int col) {
        long time_before;
        long time_after;
        time_before = System.currentTimeMillis();
        generate(depth, row, col);
        time_after = System.currentTimeMillis();
        return time_after - time_before;
    }
}
