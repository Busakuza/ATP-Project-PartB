package test;
import algorithms.mazeGenerators.*;
public class RunMazeGenerator {
    public static void main(String[] args) {
//        testMazeGenerator(new EmptyMazeGenerator());
        testMazeGenerator(new SimpleMazeGenerator());
//        testMazeGenerator(new MyMazeGenerator());
    }

    private static void testMazeGenerator(IMazeGenerator mazeGenerator) {
        // prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(100/*rows*/,100/*columns*/)));

        // generate another maze
        Maze maze = mazeGenerator.generate(1000/*rows*/, 1000/*columns*/);

        Maze maze2 = new Maze(maze.toByteArray());
        maze.print();
        Position startPosition = maze.getStartPosition();

        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"

        //prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
        System.out.println("second maze: \n");
        // prints the maze
        maze2.print();

        // get the maze entrance
        Position maze2startPosition = maze2.getStartPosition();

        // print the start position
        System.out.println(String.format("Start Position: %s", maze2startPosition)); // format "{row,column}"

        //prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze2.getGoalPosition()));
    }
}