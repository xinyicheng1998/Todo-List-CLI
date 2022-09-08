import Controller.InvalidArgumentsException;
import Controller.Processor;
import Model.InvalidTodoException;
import java.io.IOException;

public class Main {

  // --csv-file todos.csv --add-todo --todo-text newtodotest --complete-todo 1 --display --show-incomplete --sort-by-date
  // --csv-file todos.csv --add-todo --todo-text testtwofilter --complete-todo 1 --display --show-incomplete --show-category home --sort-by-priority
  // --csv-file todos.csv --add-todo --todo-text testduedate --due 2022-04-20 --priority 1 --complete-todo 1 --display --show-incomplete --sort-by-date
  // --csv-file todos.csv --add-todo --todo-text testcategory --category haha --complete-todo 1 --display --show-incomplete --sort-by-date
  // --csv-file todos.csv --add-todo --todo-text completetodo --complete-todo 1 --complete-todo 2 --complete-todo 3 --display
  public static void main(String[] args)
      throws IOException, InvalidArgumentsException, InvalidTodoException {
    Processor.run(args);
  }
}
