package algorithms.search;

import java.util.Comparator;

public class AStateComparator implements Comparator<AState> {
    /** a Comparator between 2 AStates based on their cost.
     * used for the priorityQueue in BestFirstSearch
     * */
    @Override
    public int compare(AState state1, AState state2) {
        return Integer.compare(state1.getCost(),state2.getCost());
    }
}
