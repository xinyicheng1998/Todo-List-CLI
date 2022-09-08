package Controller;

import Model.InvalidTodoException;

/**
 * Required operations to check command line.
 */
public interface ICmdParser<T> {

  /**
   * Gets the path of the specified part of the command line if it is valid.
   * @param args - the input from the user.
   * @return - the specified part of the command line, as generics.
   * @throws InvalidArgumentsException - catch invalid arguments.
   */
  T parse(String[] args) throws InvalidArgumentsException, InvalidTodoException;

}
