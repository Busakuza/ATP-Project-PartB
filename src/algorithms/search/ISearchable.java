package algorithms.search;

import java.util.ArrayList;
/** an Interface which every " searchable problem" must implement
 * getStartState - returns the start state of the problem
 * getGoalState - returns the goal state of the problem
 * getAllSuccessors - returns an ArrayList of all possible successors to a certain state
 * */
public interface ISearchable {
    public AState getStartState();
    public AState getGoalState();
    public ArrayList<AState> getAllSuccessors(AState s);
}
