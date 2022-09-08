package Controller;

import Model.InvalidTodoException;
import Model.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CmdParserAdd represents a class that checks the part of "add to do" of a command line.
 */
public class CmdParserAdd implements ICmdParser<Todo> {

  private static final String ADD = "--add-todo";
  private static final String TEXT = "--todo-text";
  private static final String COMPLETED = "--completed";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";

  /**
   * Constructs a CmdParserAdd.
   */
  public CmdParserAdd() {
  }

  /**
   * Checks whether the given command line contains "add to do" command and whether it is valid if
   * it contains
   *
   * @param args - the input from the user.
   * @return - object AddTodo, contains information of this "add to do" command
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  public Todo parse(String[] args) throws InvalidArgumentsException, InvalidTodoException {
    List<String> commandList = Arrays.asList(args);
    String description = null;
    Boolean isCompleted = null;
    LocalDate dueDate = null;
    Integer priority = null;
    String category = null;

    //it may contain at most one "add to do" command
    if (commandList.contains(ADD)) {
      if (Collections.frequency(commandList, ADD) == 1) {
        //if it contains one "add to do" command
        //it must contain "add-text" command
        if (commandList.contains(TEXT)) {
          //get description of the text if it is valid
          description = this.getDescription(commandList);
          if (commandList.contains(COMPLETED)) {
            isCompleted = Boolean.TRUE;
          }
          dueDate = this.getDue(commandList);
          priority = this.getPriority(commandList);
          category = this.getCategory(commandList);
        } else {
          throw new InvalidArgumentsException("A description of the new todo is required");
        }
      } else {
        throw new InvalidArgumentsException("Only one Add todo is accepted in one command");
      }
      Todo newTodo = new Todo.Builder(description)
          .addCompleted(isCompleted)
          .addDue(dueDate)
          .addPriority(priority)
          .addCategory(category)
          .build();
      return newTodo;
    } else {
      return null;
    }
  }

  /**
   * Helper method checks whether the command line contains a valid description.
   *
   * @param command - the command line from user, as List<String>
   * @return - description of the text, as String
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private String getDescription(List<String> command) throws InvalidArgumentsException {
    int index = command.indexOf(TEXT);
    if ((index + 1) > command.size() - 1) {
      throw new InvalidArgumentsException(
          "todo-text command is at the end, no description follows");
    }
    return command.get(index + 1);
  }

  /**
   * Helper method checks whether the command line contains a valid due.
   *
   * @param command - the command line from user, as List<String>
   * @return - due date of add to do, as String
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private LocalDate getDue(List<String> command) throws InvalidArgumentsException {
    if (command.contains(DUE)) {
      int index = command.indexOf(DUE);
      if ((index + 1) > command.size() - 1) {
        throw new InvalidArgumentsException("due of add command is at the end, no date follows");
      }
      return this.convertDate(command.get(index + 1));
    } else {
      return null;
    }
  }

  /**
   * Checks whether is a valid date and convert to LocalDate. the valid format is: yyyy-mm-dd
   *
   * @param date - the given end date, as String.
   * @return - end date, as LocalDate
   */
  private LocalDate convertDate(String date) throws InvalidArgumentsException {
    LocalDate dueDate = null;
    try {
      dueDate = LocalDate.parse(date);
    } catch (DateTimeParseException e) {
      throw new InvalidArgumentsException(
          "given due date is invalid, format yyyy-mm-dd is expected");
    }
    return dueDate;
  }

  /**
   * Helper method checks whether the command line contains a valid priority.
   *
   * @param command - the command line from user, as List<String>
   * @return - priority of add to do, as Integer
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private Integer getPriority(List<String> command) throws InvalidArgumentsException {
    if (command.contains(PRIORITY)) {
      int index = command.indexOf(PRIORITY);
      if ((index + 1) > command.size() - 1) {
        throw new InvalidArgumentsException(
            "priority of add command is at the end, no number follows");
      }
      try {
        return Integer.parseInt(command.get(index + 1));
      } catch (NumberFormatException nfe) {
        throw new InvalidArgumentsException("An integer is required after priority command");
      }
    } else {
      return null;
    }
  }

  /**
   * Helper method checks whether the command line contains a valid category.
   *
   * @param command - the command line from user, as List<String>
   * @return - category of add to do, as String
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private String getCategory(List<String> command) throws InvalidArgumentsException {
    if (command.contains(CATEGORY)) {
      int index = command.indexOf(CATEGORY);
      if ((index + 1) > command.size() - 1) {
        throw new InvalidArgumentsException(
            "category of add command is at the end, no string follows");
      }
      return command.get(index + 1);
    } else {
      return null;
    }
  }

  /**
   * Get the string of the object
   *
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "CmdParserAdd{}";
  }
}
