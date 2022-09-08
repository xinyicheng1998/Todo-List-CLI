package View;

import Model.Todo;
import java.util.ArrayList;

/**
 * Utility Class for sorting todo list
 */
public class Sorter {

  /**
   * Constructor for sorter
   */
  private Sorter() {
  }

  /**
   * Sort the Todos by date (ascending)
   *
   * @param todolist - ArrayList<Todo>, list of todo items
   */
  public static void sortByDate(ArrayList<Todo> todolist) {
    DateComparator dateComparator = new DateComparator();
    todolist.sort(dateComparator);
  }

  /**
   * Sort the Todos by priority (ascending)
   *
   * @param todolist - ArrayList<Todo>, list of todo items
   */
  public static void sortByPriority(ArrayList<Todo> todolist) {
    PriorityComparator priorityComparator = new PriorityComparator();
    todolist.sort(priorityComparator);
  }
}
