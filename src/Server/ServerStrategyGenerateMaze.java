package Server;

import IO.MyCompressorOutputStream;
import IO.SimpleCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.HashMap;
/**
 * Strategy Design Pattern implementation.
 * Gets from a client the size of the requested maze, the server generates the maze via 1 of the
 * maze Generating algorithms compresses the maze and sends it back to the client.
 * */
public class ServerStrategyGenerateMaze implements IServerStrategy{
    private HashMap<String, IMazeGenerator> mazeGens;
    public ServerStrategyGenerateMaze(){
        super();
        mazeGens = new HashMap<>();
        mazeGens.put("EmptyMazeGenerator", new EmptyMazeGenerator());
        mazeGens.put("SimpleMazeGenerator", new SimpleMazeGenerator());
        mazeGens.put("MyMazeGenerator",  new MyMazeGenerator());
    }
    @Override
    public void applyStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            String tmpFile= "tmp.tmp";
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            OutputStream toBeCompressedMaze = new MyCompressorOutputStream(new FileOutputStream(tmpFile));
            InputStream in = new FileInputStream(tmpFile);

            int[] dim = (int[])fromClient.readObject();
             IMazeGenerator mazeGen = mazeGens.get(Configurations.getInstance().getMazeGenerator());
             Maze maze = mazeGen.generate(dim[0],dim[1]);
             byte[] compressedMaze = new byte[maze.toByteArray().length];
             // I have byteArray of the maze, and now we want to send the compressed version to the client
             toBeCompressedMaze.write(maze.toByteArray());//write it to the tmpFile
             toBeCompressedMaze.flush();
             in.read(compressedMaze);//read the compressed maze from the tmpFile
             toClient.writeObject(compressedMaze);// send to client.
             toClient.flush();
             // closing the streams...
             in.close();
             fromClient.close();
             toBeCompressedMaze.close();
             toClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
