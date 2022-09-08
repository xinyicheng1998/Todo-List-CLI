package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CmdParserComplete represents a class that checks the part of "complete to do" of a command line.
 */
public class CmdParserComplete implements ICmdParser<ArrayList<Integer>>{
  private static final String COMPLETE = "--complete-todo";

  /**
   * Constructs a CmdParserAdd.
   */
  public CmdParserComplete() {
  }

  /**
   *  Checks whether the given command line contains "complete to do" command
   * @param args - the input from the user.
   * @return - the id of the complete to do, as ArrayList
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  public ArrayList<Integer> parse(String[] args) throws InvalidArgumentsException {
    List<String> commandList = Arrays.asList(args);
    ArrayList<Integer> completeList = new ArrayList<>();
    if (commandList.contains(COMPLETE)){
      for (String command: commandList) {
        if (command.equals(COMPLETE)) {
          int index = commandList.indexOf(command);
          if ((index + 1) <= commandList.size() - 1) {
            int id = Integer.parseInt(commandList.get(index + 1));
            completeList.add(id);
          } else {
            throw new InvalidArgumentsException("the command of complete is at the end, no valid id follows");
          }
        }
      }
      return completeList;
    } else {
      return null;
    }
  }

  /**
   * Get the string of the object
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "CmdParserComplete{}";
  }
}
