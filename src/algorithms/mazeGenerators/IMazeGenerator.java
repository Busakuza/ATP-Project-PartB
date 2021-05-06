package algorithms.mazeGenerators;

/**
* the interface of mazeGenerators, has two function: generate and measureAlgorithmTimeMillis.
*/
public interface IMazeGenerator {
    Maze generate (int row, int col);
    long measureAlgorithmTimeMillis (int row, int col);
}
