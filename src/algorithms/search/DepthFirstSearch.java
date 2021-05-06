package algorithms.search;

import java.util.ArrayList;

/** Implementation of the Depth First Search Algorithm to a Searchable Problem*/
public class DepthFirstSearch extends ASearchingAlgorithm{
    /** constructor
     * calls the father class' constructor using super*/
    public DepthFirstSearch() { super();}
    /**returns the name of the Searching algorithm*/
    @Override
    public String getName() {
        return "DepthFirstSearch";
    }


    /** returns a Solution to the searchable problem using Depth First Search.
     * openList is used as a stack since we use the iterative implementation of DFS.
     * we add the start state to the "stack", the head of the openList, mark the node as visited
     * while the stack isn't empty, for each successor of the current state if it's not visited
     * mark as visited and add to the head of the list. continue until the stack is empty or we reach the goal state.
     * @start - start state of the searchable problem
     * @goal - goal state of the searchable problem
     * @current - the current state/node from the "stack"
     * @successors - valid AState object which are neighbours to the current state
     * @return - Solution to the searchable problem.
     * */
    @Override
    public Solution solve(ISearchable problem) {
        AState start = problem.getStartState();
        AState goal = problem.getGoalState();
        openList.add(0,start);
        closeList.put(start,start.getCameFrom());//visited
        while(!openList.isEmpty())
        {
            AState current = popOpenList();
            ArrayList<AState> successors = problem.getAllSuccessors(current);
            for (AState neighbour : successors)
            {
                if(!closeList.containsKey(neighbour))
                {
                    closeList.put(neighbour,neighbour.getCameFrom());
                    openList.add(0,neighbour);
                }
                if (neighbour.equals(goal))
                    return new Solution(solutionPath(start,neighbour));
            }
        }
        return new Solution(solutionPath(start,goal));
    }
}



