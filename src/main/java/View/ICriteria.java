package View;

import Model.Todo;
import java.util.ArrayList;

/**
 * Interface for Filtering Criteria
 */
public interface ICriteria<T> {

  /**
   * Filter items that meets the criteria
   *
   * @param items - ArrayList<T>, list of objects
   * @return - filtered list of objects
   */
  public ArrayList<T> meetCriteria(ArrayList<T> items);
}
