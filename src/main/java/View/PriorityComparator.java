package View;

import Model.Todo;
import java.util.Comparator;

/**
 * Comparator class for priority comparison
 */
public class PriorityComparator implements Comparator<Todo> {

  /**
   * {@inheritDoc}
   */
  @Override
  public int compare(Todo o1, Todo o2) {
    return o1.getPriority().compareTo(o2.getPriority());
  }
}
