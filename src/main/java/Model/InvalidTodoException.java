package Model;

/**
 * The exception caused by the failure during initializing an Todo object.
 */
public class InvalidTodoException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.  The cause is the failure during
   * initializing an Todo object.
   * @param message the detail message. The detail message is saved for later retrieval by the {@link
   *                #getMessage()} method.
   */
  public InvalidTodoException(String message) {
    super(message);
  }
}
