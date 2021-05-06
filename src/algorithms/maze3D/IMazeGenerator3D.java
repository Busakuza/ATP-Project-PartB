package algorithms.maze3D;
/** Interface of the 3D maze generator, which every 3D maze generator must implement
 * */
public interface IMazeGenerator3D {
    /** generates a 3D maze and returns a Maze3D object.
     * the method is given 3 integer arguments of depth, row and column*/
    Maze3D generate(int depth, int row, int column);
    /** measures the time in ms to generate a 3D Maze.
     * is given 3 integer arguments of depth, row and column*/
    long measureAlgorithmTimeMillis(int depth, int row, int column);

}
