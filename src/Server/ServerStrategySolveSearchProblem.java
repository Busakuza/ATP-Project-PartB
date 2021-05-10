package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.HashMap;
/**
 * Strategy Design Pattern implements IServerStrategy,
 * applies the strategy of solving a maze problem given from a client, saving it's solution to a file in order to retrieve it
 * if the same maze problem is given and sending the solution to the client.
 * Each maze has a different hashCode which is generated from the maze.toByteArray() method.
 * */
public class ServerStrategySolveSearchProblem implements IServerStrategy {
    private HashMap<String, ISearchingAlgorithm> mazeSolver;

    public ServerStrategySolveSearchProblem() {
        this.mazeSolver = new HashMap<>();
        mazeSolver.put("BreadthFirstSearch", new BreadthFirstSearch());
        mazeSolver.put("BestFirstSearch", new BestFirstSearch());
        mazeSolver.put("DepthFirstSearch", new DepthFirstSearch());
    }

    @Override
    /**
     * Applies the strategy for solving a Maze problem, we get the solver algorithm from a configuration file.
     * @param inFromClient - The InputStream from the client, the stream from which we recieve the maze.
     * @param outToClient - The OutputStream to the client, the stream we send the solution for the maze to.
     * */
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ISearchingAlgorithm solver = this.mazeSolver.get(Configurations.getInstance().getMazeSolveAlgorithm());

            File tempDirectoryPath = new File(System.getProperty("java.io.tmpdir"));//Tmp Directory
            File[] solutionFiles = getSolutionFiles(tempDirectoryPath); //all Files with .solution
            Maze maze = (Maze) fromClient.readObject();//get the maze from the client
            //creating the name of the file
            String mazeID = tempDirectoryPath.toString() + "/" + maze.hashCode()+solver.getName()+ ".solution";
            //get the relevant solution file
            File mazeSolutionFile = getFile(solutionFiles,solver ,maze);
            //get the Solution to the maze problem
            Solution solution = getMazeSolution(mazeSolutionFile, mazeID, solver, maze);
            toClient.writeObject(solution);// write to the client
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method returns an array of files containing a solution to a maze problem
     * @param tempDirectoryPath - the path of the Temp directory in the local PC.
     * @return - An array of solution files to different maze problems.
     */
    private File[] getSolutionFiles(File tempDirectoryPath) {
        File[] solutionFiles = tempDirectoryPath.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".solution");
            }
        });
        return solutionFiles;
    }

    /**
     * The method returns a file from a list of mazeFiles matches the maze's HashCode
     * @param maze - The current Maze
     * @param solutionFiles - All the solutions to different maze problems. an Array of File objects.
     */
    private File getFile(File[] solutionFiles, ISearchingAlgorithm solver,Maze maze) {
        File mazeSolutionFile = null;
        for (File f : solutionFiles) {// should be only 1 file in the list of matching files
            if (f.getName().equals(maze.hashCode()+solver.getName()+ ".solution")) {
                mazeSolutionFile = f;
                break;
            }
        }
        return mazeSolutionFile;
    }
    /**
     * The method returns a solution to a maze problem.
     * if the mazeSolutionFile is null, it means there's a new problem to solve. create a solution file, solve the problem
     * and write the solution to the file.
     * else read the solution from the mazeSolutionFile and return it
     * @param mazeSolutionFile - the file containing the solution to the maze problem
     * @param mazeID - A string representation of the full path to a solution file of the current maze.
     * @param solver - A Searching algorithm implemented by BFS,DFS,BestFirstSearch
     * @param maze - The current maze we want to find it's solution
     * @return - Solution to the maze problem.
     * */
    private Solution getMazeSolution(File mazeSolutionFile, String mazeID, ISearchingAlgorithm solver, Maze maze) {
        Solution solution = null;
        try {
            if (mazeSolutionFile == null) {
                mazeSolutionFile = new File(mazeID);//create a new file in path
                ObjectOutputStream decoratorMazeSolFile =
                        new ObjectOutputStream(new FileOutputStream(mazeSolutionFile.getPath()));//decorator for the solution

                solution = solver.solve(new SearchableMaze(maze));// solve the maze

                decoratorMazeSolFile.writeObject(solution);// write the solution to the file
            }
            else {
                ObjectInputStream readSol = new ObjectInputStream(new FileInputStream(mazeSolutionFile.getPath()));
                solution = (Solution) readSol.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return solution;
    }
}
