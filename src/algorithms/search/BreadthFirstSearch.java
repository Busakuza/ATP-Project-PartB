package algorithms.search;

import java.util.ArrayList;
/** Implementation of Breadth First Search algorithm to find a solution for the maze problem*/
public class BreadthFirstSearch extends ASearchingAlgorithm{
    /** constructor
     * calls the father class constructor using super*/
    public BreadthFirstSearch() {
        super();
    }
    /** returns a Solution Object which is the solution to the maze using the Breadth First Search algorithm
     * we add the start state to the openList and until we reach the goal for every successor to the current state we check
     * if the state wasn't visited before via the closeList if not we mark it as visited and add it to the list
     * we continue iuntil we reach the goal
     * @start - the start state of the searchable problem
     * @goal - goal state we want to reach in the searchable problem.
     * @return - Solution to the searchable problem*/
    public Solution solve(ISearchable problem){
        AState start = problem.getStartState(); // startPosition
        AState goal = problem.getGoalState();//GoalPosition
        openList.add(start);
        AState current = popOpenList();
        while(!current.equals(goal))
        {
            ArrayList<AState> successors = problem.getAllSuccessors(current);
            for (AState route:successors) {
                if (route.equals(goal))
                {
                    return new Solution(solutionPath(start,route));
                }
                if(!closeList.containsKey(route)){
                    closeList.put(route,current);
                    openList.add(route);
                }
            }
            current = popOpenList();
        }
        return new Solution(solutionPath(start,goal));
    }
    /**returns the name of the searching algorithm*/
    @Override
    public String getName() {
        return "BreadthFirstSearch";
    }
}
