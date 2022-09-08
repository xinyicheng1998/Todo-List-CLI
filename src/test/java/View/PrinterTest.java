package View;

import static org.junit.jupiter.api.Assertions.*;

import Model.InvalidTodoException;
import Model.Todo;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrinterTest {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  ArrayList<Todo> todos;
  Todo sampleTodo;

  @BeforeEach
  void setUp() throws InvalidTodoException {
    System.setOut(new PrintStream(outputStreamCaptor));
    sampleTodo = new Todo.Builder("sample").addCompleted(false).addID(1).build();
    todos = new ArrayList<>();
    todos.add(sampleTodo);
  }

  @Test
  void printList() {
    String expected = "Model.Todo{id=1, text='sample', completed=false, due=null, priority=3, category='null'}";
    Printer.printList(todos);
    assertEquals(expected, outputStreamCaptor.toString()
        .trim());
  }
}