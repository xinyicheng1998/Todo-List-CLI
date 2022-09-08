package Controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CmdParserCSV represents a class that checks the part of CSV file of a command line.
 */
public class CmdParserCSV implements  ICmdParser<String>{
  protected static final String CSV = "--csv-file";

  /**
   * Constructs a CmdParserCSV.
   */
  public CmdParserCSV() {
  }

  /**
   * Checks whether the part of csv file of the command line is valid and gets the path.
   * @param args - the input from the user.
   * @return - the path of csv file if it is valid, as String.
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  public String parse(String[] args) throws InvalidArgumentsException{
    List<String> commandList = Arrays.asList(args);
    String path = null;
    if (commandList.contains(CSV)) {
      if (Collections.frequency(commandList, CSV) == 1) {
        int index = commandList.indexOf(CSV);
        // valid path of the CSV file must follow the command
        if ((index + 1) > commandList.size() - 1) {
          throw new InvalidArgumentsException("csv command is an the end, no path follows");
        }
        // checks whether the path refers to a valid file.
        if (this.isValidCSV(commandList.get(index + 1))) {
          path = commandList.get(index + 1);
        }
      } else {
        throw new InvalidArgumentsException("Repeated CSV command entered.");
      }
    } else {
      throw new InvalidArgumentsException("CSV command is required");
    }
    return path;
  }

  /**
   * Helper method to check whether is a valid file path.
   * @param path - a csv path provided, as String
   * @return - true if it is valid, false otherwise
   * @throws InvalidArgumentsException - catches invalid arguments.
   */
  private boolean isValidCSV(String path) throws InvalidArgumentsException {
    String suffix = ".csv";
    File file = new File(path);
    if (file.isFile() && path.endsWith(suffix)) {
        return Boolean.TRUE;
    } else {
      throw new InvalidArgumentsException("Invalid path entered: " + path);
    }
  }

  /**
   * Get the string of the object
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "CmdParserCSV{}";
  }
}
