package algorithms.search;

import java.util.ArrayList;
/** Solution Class - represents a valid path from the start position
 * in the maze towards the goal
 * @solutionPath - an arrayList containts AState object which represent the path towards the goal in the maze
 * */
public class Solution {
    private ArrayList<AState> solutionPath;
/**
 * constructor
 * @solutionPath - ArrayList of AState */
    public Solution(ArrayList<AState> solutionPath) {
        this.solutionPath = solutionPath;
    }
    /** copy constructor
     * */
    public Solution(Solution otherSolution){
        this.solutionPath = otherSolution.solutionPath;
    }
    /** getter - returns the solutionPath field
     * */
    public ArrayList<AState> getSolutionPath(){return this.solutionPath;}
}
