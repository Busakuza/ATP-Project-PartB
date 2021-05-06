package Server;

import algorithms.mazeGenerators.IMazeGenerator;

import java.io.*;
import java.util.Properties;

public class Configurations {
    private static Configurations instance = new Configurations();
    public static Configurations getInstance(){ return instance;}


    public int getNumOfThreads(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(prop.getProperty("threadPoolSize"));
    }
    public String getMazeGenerator(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("mazeGeneratingAlgorithm");
    }
    public String getMazeSolveAlgorithm(){
        Properties prop = new Properties();
        try {
            prop.load(Configurations.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("mazeSearchingAlgorithm");
    }
    //set the properties in the configuration file
    public void setNumOfThreads(String numOfThreads){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            prop.setProperty("threadPoolSize",numOfThreads);
//            prop.setProperty("mazeGeneratingAlgorithm",mazeGen);
//            prop.setProperty("mazeSearchingAlgorithm",mazeSolve);
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }
    public void setMazeSearchingAlgorithm(String mazeSolve){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            prop.setProperty("mazeSearchingAlgorithm",mazeSolve);
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }
    public void setMazeGenAlgorithm(String mazeGen){
        try(InputStream input = new FileInputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"))
        {
            Properties prop = new Properties();
            prop.setProperty("mazeGeneratingAlgorithm",mazeGen);
            prop.store(new FileOutputStream("C://Users//Kuza//Desktop//SISE//ATP-Project-PartB//resources//config.properties"),null);
        }

        catch (IOException e){e.printStackTrace();}
    }

}
