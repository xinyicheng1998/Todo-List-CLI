package Controller;

import Model.InvalidTodoException;
import Model.Todo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * CommandLineParser represents a class that validates a command line and gets information from it.
 */
public class CommandLineParser implements ICmdParser<HashMap<String, Object>> {

  protected static final String CSV = "csv";
  protected static final String ADD = "add todo";
  protected static final String COMPLETE = "complete todo";
  protected static final String DISPLAY = "display todo";

  /**
   * Constructs a CommandLineParser.
   */
  public CommandLineParser() {
  }

  /**
   * Gets information from command line if it is valid.
   *
   * @param args - the input from the user.
   * @return - a Hashmap represents the path of the csv file, the information of "add to do" if it
   * contains, the information of "complete to do" if it contains, the information of "display to
   * do" if it contains,
   * @throws InvalidArgumentsException - catch invalid arguments.
   */
  public HashMap<String, Object> parse(String[] args)
      throws InvalidArgumentsException, InvalidTodoException {
    CmdParserCSV parserCSV = new CmdParserCSV();
    CmdParserAdd parserAdd = new CmdParserAdd();
    CmdParserComplete parserComplete = new CmdParserComplete();
    CmdParserDisplay parserDisplay = new CmdParserDisplay();

    HashMap<String, Object> result = new HashMap<String, Object>();
    String csvPath = parserCSV.parse(args);
    Todo addTodo = parserAdd.parse(args);
    ArrayList<Integer> completeTodo = parserComplete.parse(args);
    DisplayTodo displayTodo = parserDisplay.parse(args);

    // must contain valid csv command
    result.put(CSV, csvPath);
    // must contain at least one valid type of command
    if (addTodo != null || completeTodo != null || displayTodo != null) {
      //compare length of valid commands to the input command
      //if they are not equal, unexpected commands entered
      if (this.getLength(args, csvPath, addTodo, completeTodo, displayTodo) == args.length) {
        result.put(ADD, addTodo);
        result.put(COMPLETE, completeTodo);
        result.put(DISPLAY, displayTodo);
        return result;
      } else {
        throw new InvalidArgumentsException("unexpected commands entered");
      }
    } else {
      throw new InvalidArgumentsException("no command of the three functionalities entered");
    }
  }


  /**
   * Helper method gets the length of all valid commands.
   *
   * @param path         - the csv path getting from the command, as String.
   * @param addTodo      - the Todo object getting from the command, as Todo.
   * @param completeTodo - the list of id to be completed getting from the command, as Arraylist.
   * @param displayTodo  - the DisplayTodo object getting from the command, as DisplayTodo.
   * @return
   */
  private Integer getLength(String[] args, String path, Todo addTodo,
      ArrayList<Integer> completeTodo, DisplayTodo displayTodo) {
    Integer length = 0;
    Integer CSV_LENGTH = 2;
    if (path != null) {
      length += CSV_LENGTH;
      length += this.getAddCommandLength(args, addTodo)
          + this.getCompleteCommandLength(completeTodo)
          + this.getDisplayCommandLength(displayTodo);
    }
    return length;
  }

  /**
   * Helper method checks the length of valid commands of adding todo.
   *
   * @param addTodo - the Todo object getting from the command, as Todo.
   * @return -the length of valid commands of adding todo.
   */
  private Integer getAddCommandLength(String[] args, Todo addTodo) {
    Integer length = 0;
    Integer BASE_LEN = 3;
    Integer COMPLETE_LEN = 1;
    Integer DUE_LEN = 2;
    Integer PRIORITY_LEN = 2;
    Integer CATEGORY_LEN = 2;
    String PRIORITY = "--priority";
    if (addTodo != null) {
      length += BASE_LEN;
      if (addTodo.getCompleted() == Boolean.TRUE) {
        length += COMPLETE_LEN;
      }
      if (addTodo.getDue() != null) {
        length += DUE_LEN;
      }
      List<String> commandList = Arrays.asList(args);
      if (commandList.contains(PRIORITY)) {
        length += PRIORITY_LEN;
      }
      if (addTodo.getCategory() != null) {
        length += CATEGORY_LEN;
      }
    }
    return length;
  }

  /**
   * Helper method checks the length of valid commands of adding todo.
   *
   * @param completeTodo - the list of id to be completed getting from the command, as Arraylist.
   * @return -the length of valid commands of completing todo.
   */
  private Integer getCompleteCommandLength(ArrayList<Integer> completeTodo) {
    Integer length = 0;
    Integer COMPLETE_LEN = 2;
    if (completeTodo != null) {
      length += COMPLETE_LEN * (completeTodo.size());
    }
    return length;
  }

  /**
   * Helper method checks the length of valid commands of displaying todo.
   *
   * @param displayTodo - the DisplayTodo object getting from the command, as DisplayTodo.
   * @return -the length of valid commands of displaying todo.
   */
  private Integer getDisplayCommandLength(DisplayTodo displayTodo) {
    Integer length = 0;
    Integer DISPLAY_LEN = 1;
    Integer SHOW_INCOMPLETE_LEN = 1;
    Integer SHOW_CATE_LEN = 2;
    Integer SORT_LEN = 1;

    if (displayTodo != null) {
      length += DISPLAY_LEN;
      if (displayTodo.getShowIncomplete() == Boolean.TRUE) {
        length += SHOW_INCOMPLETE_LEN;
      }
      if (displayTodo.getShowCategory() != null) {
        length += SHOW_CATE_LEN;
      }
      if (displayTodo.getSortByDate() == Boolean.TRUE) {
        length += SORT_LEN;
      }
      if (displayTodo.getSortByPriority() == Boolean.TRUE) {
        length += SORT_LEN;
      }
    }
    return length;
  }

  /**
   * Get the string of the object
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "CommandLineParser{}";
  }
}
