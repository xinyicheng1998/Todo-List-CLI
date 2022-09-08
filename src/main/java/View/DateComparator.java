package View;

import Model.Todo;
import java.util.Comparator;

/**
 * Comparator for date comparison
 */
public class DateComparator implements Comparator<Todo> {

  /**
   * {@inheritDoc}
   */
  @Override
  public int compare(Todo o1, Todo o2) {
    if (o1.getDue() == null && o2.getDue() == null) {
      return 0;
    }
    if (o1.getDue() == null) {
      return 1;
    }
    if (o2.getDue() == null) {
      return -1;
    }
    return o1.getDue().compareTo(o2.getDue());
  }
}
