package algorithms.search;
/** abstract class AState - represents the picture of our "world" or the problem.
 * in the maze problem AState will represent the cell of the maze, the start state, goal state.
 * @cost - an integer which represents the cost of arrival to a certain state
 * @camefrom - AState which represents the "father" state from which we got to the current state.
 * */
public abstract class AState {

  private int cost;
  private AState cameFrom;

  /** constructor
   * @cost - the cost of arrival to the state*/
  public AState(int cost) {
    this.cost = cost;
    this.cameFrom = null;
    }
  /** constructor
   * @cost -  int, the cost of arrival to the state
   * @papa- AState, the father state from which we arrived to the current state
   * */
  public AState(int cost, AState papa) {
    this.cost = cost;
    this.cameFrom = papa;
  }
  /**getters
   * getCost - int, returns the cost of the current state
   * getCameFrom - AState, returns the father state
   * */
  public int getCost(){return this.cost;}
  public AState getCameFrom(){return this.cameFrom;}
  /**setters
   * setCost - sets the cost of the current state
   * @cost - int, the cost of arrival to the current state
   * setCameFrom - sets the father state of the current state
   * @camefrom - AState, the "father" from which we arrived to the current state.
   * */
  public void setCost(int cost) {this.cost = cost;}
  public void setCameFrom(AState cameFrom) {this.cameFrom = cameFrom; }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
