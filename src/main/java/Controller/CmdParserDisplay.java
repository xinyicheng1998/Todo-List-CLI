package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * CmdParserAdd represents a class that checks the part of "display to do" of a command line.
 */
public class CmdParserDisplay implements ICmdParser<DisplayTodo>{
  private static final String DISPLAY = "--display";
  private static final String SHOW_INCOMPLETE = "--show-incomplete";
  private static final String SHOW_CATEGORY = "--show-category";
  private static final String SORT_DATE = "--sort-by-date";
  private static final String SORT_PRIORITY = "--sort-by-priority";

  /**
   * Constructs a CmdParserDisplay.
   */
  public CmdParserDisplay() {
  }

  /**
   * Checks whether the given command line contains "display to do" command
   * and whether it is valid if it contains
   * @param args - the input from the user.
   * @return - object DisplayTodo, contains information of this "display to do" command
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  public DisplayTodo parse(String[] args) throws InvalidArgumentsException {
    List<String> commandList = Arrays.asList(args);
    Boolean isDisplay = Boolean.FALSE;
    Boolean isShowIncomplete = Boolean.FALSE;
    String category = null;
    Boolean isSortDate = Boolean.FALSE;
    Boolean isSortPriority = Boolean.FALSE;

    if (commandList.contains(DISPLAY)) {
      isDisplay = Boolean.TRUE;
      if (commandList.contains(SORT_DATE)){
        isSortDate = Boolean.TRUE;
      }
      if (commandList.contains(SORT_PRIORITY)){
        isSortPriority = Boolean.TRUE;
      }
      //it can only contain one sort type
      if (isSortDate && isSortPriority) {
        throw new InvalidArgumentsException("two sort types at the same time is invalid");
      }
      if (commandList.contains(SHOW_INCOMPLETE)){
        isShowIncomplete = Boolean.TRUE;
      }
      category = this.isShowCategory(commandList);

      DisplayTodo newDisplay = new DisplayTodo(isDisplay, isShowIncomplete, category, isSortDate, isSortPriority);
      return newDisplay;
    } else {
      return null;
    }
  }

  /**
   * Helper method checks whether the command line contains a valid category.
   * @param command - the command line from user, as List<String>
   * @return - category of display to do, as String
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private String isShowCategory(List<String> command) throws InvalidArgumentsException {
    if (command.contains(SHOW_CATEGORY)){
      int index = command.indexOf(SHOW_CATEGORY);
      if ((index + 1) <= command.size() - 1 ){
        return command.get(index + 1);
      } else {
          throw new InvalidArgumentsException("category of display command is at the end, no string follows");
      }
    }
    return null;
  }

  /**
   * Get the string of the object
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "CmdParserDisplay{}";
  }
}
