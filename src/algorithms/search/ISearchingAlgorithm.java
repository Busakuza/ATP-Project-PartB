package algorithms.search;
/** an interface which every searching algorithm must implement.
 * solve - returns a Solution to the searchable problem.
 * getNumberOfNodesEvaluated - returns the number of visited nodes/states when searching for the goal.
 * getName - returns the name of the Searching Algorithm.
 * */
public interface ISearchingAlgorithm {
    Solution solve(ISearchable domain);
    int getNumberOfNodesEvaluated();
    String getName();
}
