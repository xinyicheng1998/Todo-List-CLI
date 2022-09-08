package View;

import Model.Todo;
import java.util.ArrayList;

/**
 * Utility Class for printing todo list
 */
public class Printer {

  /**
   * Constructor for printer
   */
  private Printer() {
  }

  /**
   * print todolist out
   *
   * @param todolist - ArrayList<Todo>, list of todo items
   */
  public static void printList(ArrayList<Todo> todolist) {
    for (Todo item : todolist) {
      System.out.println(item);
    }
  }
}
