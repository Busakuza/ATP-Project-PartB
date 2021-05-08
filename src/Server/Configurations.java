package Server;


import algorithms.search.BestFirstSearch;

import java.io.*;
import java.util.Properties;
/**
 * Singleton Design pattern.
 * using the config.properties in the resources folder has getters and setters for the information in the config file.
 * */
public class Configurations {
    private static Configurations instance = new Configurations();
    public static Configurations getInstance(){ return instance;}

    /**
     * getter for the number of threads the thread pool will use.
     * */
    public int getNumOfThreads(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }
    /**
     * getter for a String representation of a maze generation algorithm in config.properties.
     * */
    public String getMazeGenerator(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("mazeGeneratingAlgorithm");
    }
    /**
     * getter for a String representation of a maze solving algorithm in config.properties.
     * */
    public String getMazeSolveAlgorithm(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("mazeSearchingAlgorithm");
    }
    /**
     * setter for the number of threads to be used in the thread pool.
     * Sets the value in config.properties.
     * */
    public void setNumOfThreads(String numOfThreads){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            prop.setProperty("threadPoolSize",numOfThreads);
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }
    /**
     * setter the maze searching algorithm.
     * Sets the value in config.properties.
     * */
    public void setMazeSearchingAlgorithm(String mazeSolve){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            if(mazeSolve.equals("BestFirstSearch") || mazeSolve.equals("DepthFirstSearch") || mazeSolve.equals("BreadthFirstSearch"))
                prop.setProperty("mazeSearchingAlgorithm",mazeSolve);
            else
                prop.setProperty("mazeSearchingAlgorithm", "BestFirstSearch");
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }
    /**
     * setter the maze generator algorithm.
     * Sets the value in config.properties.
     * */
    public void setMazeGenAlgorithm(String mazeGen){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            if(mazeGen.equals("EmptyMazeGenerator") || mazeGen.equals("SimpleMazeGenerator") ||
                    mazeGen.equals("MyMazeGenerator"))
                prop.setProperty("mazeGeneratingAlgorithm",mazeGen);
            else
                prop.setProperty("mazeSearchingAlgorithm", "MyMazeGenerator");
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }

}
