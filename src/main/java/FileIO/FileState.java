package FileIO;

import Model.FieldToString;
import Model.InvalidTodoException;
import Model.StringToField;
import Model.Todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * FileState is a class that uses to read and write file, implementing interface IReader with
 * ArrayList<Todo> as type parameter, and interface IWriter with ArrayList<Todo> as type parameter.
 * Singleton pattern is applied.
 */
public class FileState implements IReader, IWriter {

  /** The data structure to store the information of csv file */
  private ArrayList<Todo> csvInfo;
  /** The data structure to store the header line of csv file */
  private ArrayList<String> header;
  /** The singleton pattern is applied */
  private static final FileState INSTANCE = new FileState(); // Instance will be created on demand

  /** The regular expression to split the string in reading file */
  private static final String DELIMITER = "\"*,*\"";
  /** The String to concat the string in writing file */
  private static final String DELIMITER_PLAIN = "\",\"";
  /** The double quote symbol to concat the string in writing file */
  private static final String DOUBLE_QUOTE = "\"";

  /**
   * Constructor, creating a new FileState object
   */
  private FileState() { // Private prevents instantiation outside class
    this.csvInfo = null;
  }

  /**
   * The application of singleton pattern
   * @return the instance of FileState
   */
  // global access to instance
  public static FileState getInstance() {
    return INSTANCE;
  }

  /**
   * Get the csvInfo of the given file
   * @return csv information stored in the ArrayList of Todo
   */
  public ArrayList<Todo> getCsvInfo() {
    return csvInfo;
  }

  /**
   * Set the csvInfo to the given ArrayList of Todo
   * @param csvInfo the given information, encoded as ArrayList of Todo
   */
  public void setCsvInfo(ArrayList<Todo> csvInfo) {
    this.csvInfo = csvInfo;
  }

  /**
   * Read the information in the given filepath, and store it in the private field csvInfo
   * @param filepath - the path of the csv file, encoded as String
   * @throws IOException when the i/o stream is interrupted
   */
  @Override
  public void read(String filepath) throws IOException {
    this.csvInfo = this.readFile(filepath);
  }

  /**
   * Write the information stored in the private field csvInfo to the given filepath
   * @param filepath - the path of the csv file, encoded as String
   * @throws IOException when the i/o stream is interrupted
   */
  @Override
  public void write(String filepath) throws IOException {
    this.writeFile(filepath, this.csvInfo);
  }

  /**
   * Helper method to clear one line information
   * @param oneObject - the given information in one line of one object, encoded as String
   * @return the split information, encoded as an arraylist of strings
   */
  private ArrayList<String> clearOneLine(String oneObject) {
    String[] list = oneObject.split(DELIMITER); // split one string to string array
    ArrayList<String> info = new ArrayList<>(list.length);
    Collections.addAll(info, list); // transfer string array to string arraylist
    info.remove(0);
    return info;
  }

  /**
   * Helper method:
   * Read the information in the given file, and return the structure where the information stored
   * @param filepath - the file to be read, encoded as String
   * @return the structure in which the information stored
   * @throws FileNotFoundException - throws when the file cannot be found
   * @throws IOException           - throws when the i/o stream meets problem
   */
  private ArrayList<Todo> readFile(String filepath) throws IOException {
    ArrayList<Todo> fileInfo = new ArrayList<>();
    BufferedReader inputFile = null;
    try {
      inputFile = new BufferedReader(new FileReader(filepath));
      String firstLine = inputFile.readLine();
      if (firstLine == null) {
        return null;
      }

      this.header = this.clearOneLine(firstLine);
      String line;
      while ((line = inputFile.readLine()) != null) {
        if (line.length() == 0) {
          continue;
        }
        ArrayList<String> oneLineInfo = this.clearOneLine(line);// information of given csv's object
        HashMap<String, String> obj = new HashMap<>();
        for (int i = 0; i < header.size(); i++) {
          obj.put(header.get(i), oneLineInfo.get(i));
        }
        Todo newtodo = StringToField.HashmapToTodo(obj);
        fileInfo.add(newtodo);
      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
      throw ioe;
    } catch (InvalidTodoException e) {
      e.printStackTrace();
    } finally {
      if (inputFile != null) {
        try {
          inputFile.close();
        } catch (IOException e) {
          System.out.println("Failed to close input stream");
          e.printStackTrace();
          throw e;
        }
      }
    }
    return fileInfo;
  }

  /**
   * Helper method:
   * Write the information to the given file path
   * @param filepath - where the information should be stored, encoded as String
   * @param records - the data structure where the information stored, encoded as ArrayList of Todo
   * @throws IOException when the i/o stream is interrupted
   */
  private void writeFile(String filepath, ArrayList<Todo> records) throws IOException {
    BufferedWriter outputFile = null;
    String content = DOUBLE_QUOTE;
    try {
      outputFile = new BufferedWriter(new FileWriter(filepath));
      content += String.join(DELIMITER_PLAIN, this.header);
      content += DOUBLE_QUOTE;
      content += System.lineSeparator();
      for (int i = 0; i < records.size(); i++) {
        Todo record = records.get(i);
        String processed = FieldToString.TodoToString(record);
        content += processed.concat(System.lineSeparator());
      }
      content = content.substring(0, content.length() - 1);
      outputFile.write(content);
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
      ioe.printStackTrace();
      throw ioe;
    } finally {
      try {
        outputFile.close();
      } catch (IOException e) {
        System.out.println("errString");
        e.printStackTrace();
      }
    }
  }

}
