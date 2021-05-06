package algorithms.search;


import java.util.*;

/**abstract class ASearching Algorithm , has several function which are common between several of the
 * searching algorithms, each searching algorithm will extend the ASearchingAlgorithm
 * @openList - a LinkedList of AState object, each algorithm will use it differently i.e. as a stack or a queue.
 * @closeLost - HashMap object which represents the AStates that had been visited.
 * @visitedNodes - int, the total number of cells the searching algorithm visited in order to find the goal of the problem*/
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{
    protected LinkedList<AState> openList;
    protected HashMap<AState,AState> closeList;
    private int visitedNodes;
    /** default constructor*/
    public ASearchingAlgorithm(){
        openList = new LinkedList<AState>();
        closeList = new HashMap<AState,AState>();
        visitedNodes=0;
    }
    /** returns and removes the head of the openList
     * increases the visitedNodes by 1.*/
    protected AState popOpenList(){
        visitedNodes++;
        return openList.poll();
    }
    /** returns an ArrayList of AState object in order from the start state to the goal.
     * we add each element from last to start, but we add to the start of the list.
     * while the goal doesn't equal to the start, replace the goal with it's father and continue the loop.
     * @start - the start AState of the problem
     * @goal - the goal AState of the problem*/
    protected ArrayList<AState> solutionPath(AState start, AState goal){
        ArrayList<AState> solutionPath = new ArrayList<AState>();
        solutionPath.add(0,goal);
        if (goal.getCameFrom()==null)
            goal = closeList.get(goal);
        while(!start.equals(goal))
        {
            if(!solutionPath.contains(goal))
                solutionPath.add(0,goal);
            goal = goal.getCameFrom();
        }
        solutionPath.add(0,start);
        return solutionPath;
    }
    public Solution solve(ISearchable problem){return null;}

    /** returns the number of visited nodes/states in the searching algorithm*/
    @Override
    public int getNumberOfNodesEvaluated() {
        return visitedNodes;
    }

    public abstract String getName();
}
