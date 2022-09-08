package View;

import Model.Todo;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class for filtering by completion status
 */
public class CriteriaCompletion implements ICriteria<Todo> {

  private boolean completed;

  /**
   * Constructor for CriteriaCompletion
   *
   * @param completed - boolean, completion status to be filtered
   */
  public CriteriaCompletion(boolean completed) {
    this.completed = completed;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<Todo> meetCriteria(ArrayList<Todo> todoList) {
    todoList = (ArrayList<Todo>) todoList.stream()
        .filter(todo -> todo.getCompleted() == this.completed).collect(
            Collectors.toList());
    return todoList;
  }
}
