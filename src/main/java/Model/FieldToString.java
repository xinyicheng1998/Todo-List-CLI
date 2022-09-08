package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * FieldToString is a utility class that could be used to transfer a Todo object to a String for
 * a csv file.
 */
public class FieldToString {

  /** The null value will be "?" in csv file */
  private static final String NULL_VALUE = "?";
  /** The default format of LocalDate object */
  private static final String DEFAULT_LOCAL_DATE_FORMAT = "yyyy-MM-dd";
  /** The regular expression to split the string */
  private static final String DELIMITER = "\",\"";
  /** The head or tail of the String */
  private static final String HEAD_AND_TAIL = "\"";

  /**
   * Constructor, creating a new FieldToString object, to use methods in this class.
   */
  private FieldToString() {
  }

  /**
   * Transfer a Todo object to a String for a csv file. The data will be organized in the order of
   * "id","text","completed","due","priority","category".
   * @param todo - the given Todo object to be transferred
   * @return the String for a csv file
   */
  public static String TodoToString(Todo todo){
    String msg = String.join(DELIMITER, IntegerToString(todo.getId()), TextToString(todo.getText()),
        CompletedToString(todo.getCompleted()), DueToString(todo.getDue()),
        IntegerToString(todo.getPriority()), CategoryToString(todo.getCategory()));
    return HEAD_AND_TAIL.concat(msg).concat(HEAD_AND_TAIL);
  }

  /**
   * Helper function to transfer Integer id to String and Integer priority to String.
   * The id cannot be null since it will be generated in CRUD.addNewRow()
   * Priority cannot be null since in Todo.isValidPriority(), if the given priority is null,
   * the priority of this object will be set to default priority.
   * @param integer - the given id, encoded as Integer
   * @return the String of numbers
   */
  private static String IntegerToString(Integer integer){
    return integer.toString();
  }

  /**
   * Helper function to transfer String text to String.
   * Text is the required field and impossible to be null.
   * Although the text does not need transition,  for possible future revision of text, we still
   * use method here.
   * @param text - the given text, encoded as String
   * @return the content of text, encoded as String
   */
  private static String TextToString(String text){
    return text;
  }

  /**
   * Helper function to transfer Boolean completed to String. The completed will not be null since
   * in Todo.addCompleted(), if the given completed is null, the completed of the object will be
   * false.
   * @param completed - the given completed, encoded as Boolean
   * @return the String of completed
   */
  private static String CompletedToString(Boolean completed){
    return completed.toString();
  }

  /**
   * Helper function to transfer LocalDate due to String
   * @param due - the given due, encoded as LocalDate
   * @return the String of due in given format
   */
  private static String DueToString(LocalDate due){
    if (due == null) return NULL_VALUE;
    else return due.format(DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_FORMAT));
  }

  /**
   * Helper function to transfer String category to String.
   * @param category - the given category, encoded as String
   * @return "?" if the category is null, and otherwise the category
   */
  private static String CategoryToString(String category){
    if (category == null) return NULL_VALUE;
    else return category;
  }

}
