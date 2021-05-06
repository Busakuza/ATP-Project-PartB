package algorithms.search;

import algorithms.maze3D.IMazeGenerator3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestFirstSearchTest {
    private BestFirstSearch bfs = new BestFirstSearch();
    private ISearchable maze;
    private IMazeGenerator[] mazeGen = {new EmptyMazeGenerator(), new SimpleMazeGenerator(), new MyMazeGenerator()};
    private IMazeGenerator3D maze3DGen = new MyMaze3DGenerator();

    @Test
    void getName() throws Exception{
        assertEquals("BestFirstSearch",bfs.getName());
    }

    @Test
    void EmptyMazeSolutionTest() throws Exception{
     maze = new SearchableMaze(mazeGen[0].generate(1000,1000));
     long before = System.currentTimeMillis();
     bfs.solve(maze);
     long after = System.currentTimeMillis();
     assertTrue(60000 >= (after-before));
    }
    @Test
    void SimpleMazeSolutionTest() throws Exception
    {
        maze = new SearchableMaze(mazeGen[1].generate(1000,1000));
        long before = System.currentTimeMillis();
        bfs.solve(maze);
        long after = System.currentTimeMillis();
        assertTrue(60000 >= (after-before));
    }
    @Test
    void MyMazeSolutionTest() throws Exception
    {
        maze = new SearchableMaze(mazeGen[2].generate(1000,1000));
        long before = System.currentTimeMillis();
        bfs.solve(maze);
        long after = System.currentTimeMillis();
        assertTrue(60000 >= (after-before));
    }
    @Test
    void MyMaze3DSolutionTest() throws Exception{
        maze = new SearchableMaze3D(maze3DGen.generate(100,100,100));
        long before = System.currentTimeMillis();
        bfs.solve(maze);
        long after = System.currentTimeMillis();
        assertTrue(60000 >= (after-before));
    }
    @Test
    void wrongInputTest() throws Exception{
        assertTrue(bfs.solve(null) == null);
    }
}