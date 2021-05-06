package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;
/** Implementation of Best First Search, a variant of Breadth First Search which uses a PriorityQueue.
 * @OpenQueue - a PriorityQueue of the AState object of the problem.*/
public class BestFirstSearch extends ASearchingAlgorithm{
    private PriorityQueue<AState> openQueue;
    /** constructor
     * calls the father's constructor using super, initializes the OpenQueue by giving it the AstateComparator
     * which compares between AStates using their cost.*/
    public BestFirstSearch() {
        super();
        this.openQueue = new PriorityQueue<AState>(new AStateComparator());
    }
    /** returns the name of the Searching Algorithm*/
    @Override
    public String getName() {
        return "BestFirstSearch";
    }
    /** returns the head of the OpenQueue which is the AState with the lowest cost due to the comparator
     * we gave to it's constructor.*/
    @Override
    protected AState popOpenList() {
        super.popOpenList();
        return openQueue.poll();
    }
    /**same implementation of the Breadth First Search but instead of using a LinkedList, we use a Priority Queue
     * since the implementation wan't to find the path with the lowes cost towards the solution.
     * @start - start AState of the problem
     * @goal - goal AState of the problem
     * @current - the current AState which has the lowes cost from the OpenQueue.
     * @successors - valid neighbours to the current AState.
     * @return - return a Solution To the searchale problem.*/
    @Override
    public Solution solve(ISearchable domain) {
        if (domain == null)
            return null;
        AState start = domain.getStartState();
        AState goal = domain.getGoalState();
        if (start == null || goal == null)
            return null;
        closeList.put(start, start.getCameFrom());
        openQueue.add(start);
        while (!openQueue.isEmpty())
        {
            AState current = popOpenList();
            if (current.equals(goal))
                break;
            ArrayList<AState> successors = domain.getAllSuccessors(current);
            for (AState neighbour : successors) {
                if (goal.equals(neighbour))
                    return new Solution(solutionPath(start,neighbour));
                if (!closeList.containsKey(neighbour) && !openQueue.contains(neighbour)){
                    closeList.put(neighbour,neighbour.getCameFrom());
                    openQueue.add(neighbour);
                }
            }
        }
        return new Solution(solutionPath(start,goal));
    }

}
