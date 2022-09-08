package Model;

import FileIO.FileState;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for CRUD operation on todo list
 */
public class CRUD {

  /**
   * Constructor for CRUD class
   */
  private CRUD() {
  }

  /**
   * add new todo and update csv file
   *
   * @param todolist - ArrayList<Todo>, list of todo items
   * @param newItem  - Todo, new todo item to be added
   * @param filePath - String, csv file path
   * @throws IOException - throws when the i/o stream meets problem
   */
  public static void addNewRow(ArrayList<Todo> todolist, Todo newItem, String filePath)
      throws IOException {
    newItem.setId(todolist.size() + 1);
    todolist.add(newItem);
    saveToFile(todolist, filePath);
  }

  /**
   * set the completed status of an existing Todo to true and update csv file
   *
   * @param todolist - ArrayList<Todo>, list of todo items
   * @param ids      - ArrayList<Integer>, list of ids that has completed
   * @param filePath - String, csv file path
   * @throws IOException - throws when the i/o stream meets problem
   */
  public static void completeTodo(ArrayList<Todo> todolist, ArrayList<Integer> ids, String filePath)
      throws IOException {
    for (Todo todo : todolist) {
      if (ids.contains(todo.getId())) {
        todo.setCompleted();
      }
    }
    saveToFile(todolist, filePath);
  }

  /**
   * @param todolist - ArrayList<Todo>, list of todo items
   * @param filePath - String, csv file path
   * @throws IOException - throws when the i/o stream meets problem
   */
  public static void saveToFile(ArrayList<Todo> todolist, String filePath) throws IOException {
    FileState fs = FileState.getInstance();
    fs.setCsvInfo(todolist);
    fs.write(filePath);
  }

}
