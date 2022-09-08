package View;

import Model.Todo;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class for filtering by category
 */
public class CriteriaCategory implements ICriteria<Todo> {

  private String category;

  /**
   * Constructor for CriteriaCategory
   *
   * @param category - String, category to be filtered
   */
  public CriteriaCategory(String category) {
    this.category = category;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<Todo> meetCriteria(ArrayList<Todo> todoList) {
    todoList = (ArrayList<Todo>) todoList.stream()
        .filter(todo -> todo.getCategory() != null && todo.getCategory().equals(this.category))
        .collect(Collectors.toList());
    return todoList;
  }
}
