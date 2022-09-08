package Controller;

/**
 * InvalidArgumentsException is an exception, extending Exception
 */
public class InvalidArgumentsException extends Exception {

  /**
   * Constructor for InvalidArgumentsException
   * @param message - message String, message in the exception
   */
  public InvalidArgumentsException(String message) {
    super(message);
  }
}
