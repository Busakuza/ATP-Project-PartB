package algorithms.maze3D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
/** 3D Maze generator. using a variation of prim and iterative DFS maze generation.*/
public class MyMaze3DGenerator extends AMaze3DGenerator{
    /** we create a new maze3D a Maze3D object and initialize the visited boolean 3D array.
     * first we start with a maze full of walls which are represented by "1". we pick a start position and mark as visited
     * and set the value of the maze via the start position indexes as 0, creating a path in other words.
     * we push the start position to the stack.
     * while the stack isn't empty we pop the head of the stack and if the current position has at least 1 neighbour that
     * hasn't been visited yet, we add the current position to the stack check the number of walls surronding the current cell
     * randomly pick 1 neighbour from the unvisited neighbours and create a path.
     * we continue this process until the stack is empty.
     * pick "randomly" a goal position from a list of possibleGoal, set the value in the maze to 0 (using the indexes of
     * the goal position) and return the maze.
     * @depth - the depth of the maze
     * @row - the row of the maze in each depth
     * @column - the column of the maze in each depth.
     * @maze3D - Maze3D object, our 3D maze
     * @randomCellIndex = Random, using it to randomly pick a neighbour from the neighbour list.
     * @visited - a 3D boolean array corresponds with the maze3D size
     * @wallList - a Stack data structure, holds the unvisited positions in the maze
     * @possibleGoalCells - a List of possible goals we pick randomly at the end of the maze generation.
     * @neighbours - a list of unvisited neighbours to the currentCell
     * @start - the start Position3D of the maze
     * @currentCell - the current position we work with, the head of the stack.
     * @return - maze3D object after finishing creating the maze.*/
    @Override
    public Maze3D generate(int depth, int row, int column) {
        Maze3D maze3D = new Maze3D(depth,row,column);
        Random randomCellIndex = new Random();
        // **************************************************
        // Initializing the visited boolean array and the wallList
        //****************************************************
        boolean[][][] visited = new boolean[depth][row][column];
        Stack<Position3D> wallsList = new Stack<Position3D>();
        // we will pick a random goal from the list, the
        // list will contain only cells which have "0" and were visited.
        ArrayList<Position3D> possibleGoalCells = new ArrayList<Position3D>();
        ArrayList<Position3D> neighbours = new ArrayList<Position3D>();
        //We pick a starter position
        Position3D start = new Position3D(0,0,column/2);
        maze3D.setStartPosition(start);
        // we start with a grid full of "1".
        makeGridOfWalls(maze3D);
        maze3D.setCellValue(start.getDepthIndex(), start.getRowIndex(), start.getColumnIndex(), 0);
        // mark first cell, the start cell, as visited.
        visited[start.getDepthIndex()][start.getRowIndex()][start.getColumnIndex()] = true;
        wallsList.push(start);
        // add the neighbours of the cell to the wallsList
        while(!wallsList.isEmpty())
        {
            // get a random cell from the wallsList
            Position3D currentCell = wallsList.pop();
//            visited[currentCell.getDepthIndex()][currentCell.getRowIndex()][currentCell.getColumnIndex()]=true;
            addCellNeighbours(neighbours,currentCell,maze3D,visited);
            //neighbour.size is bigger then 0 if we have any unvisited cells.
            if(neighbours.size()>=1)
            {
                if(numOfWalls(neighbours,maze3D)>=4)
                {
                    wallsList.push(currentCell);
                    Position3D neighbourCell = neighbours.get(randomCellIndex.nextInt(neighbours.size()));
                    maze3D.setCellValue(neighbourCell.getDepthIndex(), neighbourCell.getRowIndex(), neighbourCell.getColumnIndex(), 0);
                    visited[neighbourCell.getDepthIndex()][neighbourCell.getRowIndex()][neighbourCell.getColumnIndex()]=true;
                    wallsList.push(neighbourCell);
                    possibleGoalCells.add(neighbourCell);
                }

            }
            neighbours.clear();
        }
        if(depth >= 50)
            maze3D.setGoalPosition(possibleGoalCells.get((possibleGoalCells.size()/1000)));
        else
            maze3D.setGoalPosition(possibleGoalCells.get((possibleGoalCells.size()/2)));
        return maze3D;
    }
    /** The function goes iterates over the maze, and inputs "1" in every cell.
     *  @maze - Maze3D object. the 3D maze
     *  @m_depth - the depth of the maze.
     *  @m_row - the row of the maze in each depth.
     *  @m_col - the column of the maze in each depth.*/
    private void makeGridOfWalls(Maze3D maze)
    {

        for (int m_depth =0; m_depth<maze.getMap().length;m_depth++)
        {
            for(int m_row = 0;m_row < maze.getMap()[0].length; m_row++)
            {
                for (int m_col = 0; m_col<maze.getMap()[0][0].length;m_col++){
                    maze.setCellValue(m_depth, m_row, m_col,1);
                }
            }
        }
    }
    /** The function gets as an argument the neighbours list of a certain cell
     * in the maze, and counts how many "walls" are in the neighbours list.
     * the walls are represented by the "1" value
     * @neighbours - a list of position3D which are neighbours to a certain cell.
     * @maze - Maze3D object, the 3D maze
     * @wallNumber - the number of walls, default =1.
     * @returns - the number of walls surrounding the cell.*/
    private int numOfWalls(ArrayList<Position3D> neighbours, Maze3D maze){
        int wallNumber = 1;
        for (Position3D cell: neighbours) {
            if (maze.getMap()[cell.getDepthIndex()][cell.getRowIndex()][cell.getColumnIndex()] == 1)
                wallNumber++;
        }
        return wallNumber;
    }
    /** the function adds neighbours to the list neighbours only if they haven't benn visited yet.
     * @neighbours - an empty list which will contain unvisited neighbours to a certain cell in the maze.
     * @position - the position of the current cell.
     * @maze - a 3D maze.
     * @visited - 3D array of boolean values. the same size as the 3D maze.
     * @potentialNeighbours - an ArrayList of neighbours we get to the current position. which we will check their
     * depth row and column indexes in the visited array to see if it's true or false.
     * @returns- the unvisited neighbours to position.*/
    private void addCellNeighbours(ArrayList<Position3D> neighbours, Position3D position, Maze3D maze,boolean[][][]visited) {
        //the function adds neighbours to the list only if they haven't been visited yet
            ArrayList<Position3D> potentialNeighbours = maze.getNeighbourCells(position);
        for (Position3D neighbour: potentialNeighbours) {
            if(visited[neighbour.getDepthIndex()][neighbour.getRowIndex()][neighbour.getColumnIndex()]==false)
                neighbours.add(neighbour);
        }

    }
}
