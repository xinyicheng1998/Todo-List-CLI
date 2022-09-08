package Controller;

import FileIO.FileState;
import Model.CRUD;
import Model.InvalidTodoException;
import Model.Todo;
import View.Displayer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for processing argument and executing instructions
 */
public class Processor {

  protected static final String CSV = "csv";
  protected static final String ADD = "add todo";
  protected static final String COMPLETE = "complete todo";
  protected static final String DISPLAY = "display todo";

  /**
   * process argument and execute instructions
   *
   * @param args - String[], arguments
   * @throws InvalidArgumentsException - throws when meets invalid argument
   * @throws IOException               - throws when the i/o stream meets problem
   * @throws InvalidTodoException      - throws when todo requirement not meet
   */
  public static void run(String[] args)
      throws InvalidArgumentsException, IOException, InvalidTodoException {
    CommandLineParser clp = new CommandLineParser();
    HashMap<String, Object> commands = clp.parse(args);
    FileState fs = FileState.getInstance();
    String filepath = (String) commands.get(CSV);
    fs.read(filepath);
    ArrayList<Todo> todolist = fs.getCsvInfo();
    Todo newtodo = (Todo) commands.get(ADD);
    CRUD.addNewRow(todolist, newtodo, filepath);
    ArrayList<Integer> ids = (ArrayList<Integer>) commands.get(COMPLETE);
    CRUD.completeTodo(todolist, ids, filepath);
    DisplayTodo displayTodo = (DisplayTodo) commands.get(DISPLAY);
    Displayer.run(displayTodo, todolist);
  }

}
