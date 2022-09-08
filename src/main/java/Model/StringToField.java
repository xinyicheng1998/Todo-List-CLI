package Model;

import Model.Todo.Builder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
/**
 * FieldToString is a utility class used to transfer a HashMap of String and String to a Todo
 * object.
 */
public class StringToField {

  /** The null value will be "?" in csv file */
  private static final String NULL_VALUE = "?";
  /** The default format of LocalDate object */
  private static final String DEFAULT_LOCAL_DATE_FORMAT = "yyyy-MM-dd";

  /**
   * Constructor, creating a new StringToField object, to use methods in this class.
   */
  private StringToField() {
  }

  /**
   * Transfer a HashMap of String and String to a Todo object
   * @param item - the given HashMap of String and String
   * @return a Todo object with the given information
   * @throws IllegalArgumentException when the given string cannot be converted
   * @throws InvalidTodoException when the Todo object cannot be initialized correctly
   */
  public static Todo HashmapToTodo(HashMap<String, String> item)
      throws IllegalArgumentException, InvalidTodoException {
    Integer id = StringToInteger(item.get("id"));
    String text = StringToString(item.get("text"));
    Boolean completed = StringToBoolean(item.get("completed"));
    LocalDate due = StringToLocalDate(item.get("due"));
    Integer priority = StringToInteger(item.get("priority"));
    String category = StringToString(item.get("category"));
    return new Builder(text).addID(id)
        .addCompleted(completed)
        .addDue(due)
        .addPriority(priority)
        .addCategory(category)
        .build();
  }

  /**
   * Helper function to transfer String to Integer
   * @param s - the given String to be converted
   * @return the Integer which the given String is converted to
   * @throws NumberFormatException when the Integer is not in the correct format
   */
  // TA says that the id and priority in csv file will not be null
  private static Integer StringToInteger(String s) throws NumberFormatException{
    try {
      return Integer.parseInt(s);
    }
    catch (NumberFormatException ne){
      throw ne;
    }
  }

  /**
   * Helper function to transfer String to String. The "?" in csv file will be converted to null.
   * @param s - the given String to be converted
   * @return the String content which the given String is converted to
   */
  private static String StringToString(String s){
    if (s.equals(NULL_VALUE)) return null;
    else return s;
  }

  /**
   * Helper function to transfer String to Boolean. The "?" in csv file will be converted to null.
   * @param s - the given String to be converted
   * @return null if the string is "?", false and true if the string can be converted to Boolean,
   * and throws exception otherwise
   * @throws IllegalArgumentException when the given string cannot be converted to Boolean
   */
  private static Boolean StringToBoolean(String s) throws IllegalArgumentException{
    if (s.equals(NULL_VALUE)) return null;
    if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
      return Boolean.valueOf(s);
    }
    else {
      throw new IllegalArgumentException("The given string cannot be converted to Boolean.");
    }
  }

  /**
   * Helper function to transfer String to LocalDate. The "?" in csv file will be converted to null.
   * @param s - the given String to be converted
   * @return null if the string is "?", the corresponding LocalDate object if the string can be
   * converted to LocalDate, and throws exception otherwise
   * @throws DateTimeParseException when the given string cannot be converted to LocalDate.
   */
  private static LocalDate StringToLocalDate(String s) throws DateTimeParseException{
    if (s.equals(NULL_VALUE)) return null;
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_FORMAT);
      return LocalDate.parse(s, formatter);
    }catch (DateTimeParseException dtpe){
      throw dtpe;
    }
  }

}
