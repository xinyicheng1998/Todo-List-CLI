package View;

import Controller.DisplayTodo;
import Model.Todo;
import java.util.ArrayList;

/**
 * Utility Class for display todo list
 */
public class Displayer {

  /**
   * Constructor for displayer
   */
  private Displayer() {
  }

  /**
   * Run according to the DisplayTodo instruction
   *
   * @param displayTodo - DisplayTodo, instructions for how to display todo list
   * @param todolist    - ArrayList<Todo>, list of todo items
   */
  public static void run(DisplayTodo displayTodo, ArrayList<Todo> todolist) {
    if (!displayTodo.getDisplay()) {
      return;
    }
    if (displayTodo.getShowIncomplete()) {
      CriteriaCompletion incompleteFilter = new CriteriaCompletion(Boolean.FALSE);
      todolist = incompleteFilter.meetCriteria(todolist);
    }
    if (displayTodo.getShowCategory() != null) {
      CriteriaCategory categoryFilter = new CriteriaCategory(displayTodo.getShowCategory());
      todolist = categoryFilter.meetCriteria(todolist);
    }
    if (displayTodo.getSortByDate()) {
      Sorter.sortByDate(todolist);
      Printer.printList(todolist);
    } else if (displayTodo.getSortByPriority()) {
      Sorter.sortByPriority(todolist);
      Printer.printList(todolist);
    }
  }
}
