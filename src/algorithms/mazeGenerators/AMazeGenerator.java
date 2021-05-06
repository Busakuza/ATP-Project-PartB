package algorithms.mazeGenerators;

/**
* class AMazeGenerator: is abstract class that implements the functions of the header IMazeGenerator.
* This class implements the function measureAlgorithmTimeMillis for all the heirs.
* */
public abstract class AMazeGenerator implements IMazeGenerator{

/**
* function measureAlgorithmTimeMillis: get two inputs: row, col and return the length time
* that take to function generate to create a Maze with size row*col.
* @param row ,Integer ,number of rows
* @param col ,Integer , number of columns
* @return the length of time of generate function to run, type long.
* */
    @Override
    public long measureAlgorithmTimeMillis(int row, int col) {
        long time_before;
        long time_after;
        long generate_time;
        time_before=System.currentTimeMillis();
        generate(row, col);
        time_after=System.currentTimeMillis();
        generate_time=time_after-time_before;
        return generate_time;

    }

}
