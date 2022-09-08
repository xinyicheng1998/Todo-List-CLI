package View;

import static org.junit.jupiter.api.Assertions.*;

import Controller.DisplayTodo;
import Model.InvalidTodoException;
import Model.Todo;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DisplayerTest {

  DisplayTodo testDisplay;
  ArrayList<Todo> todos;
  Todo sampleTodo1;
  Todo sampleTodo2;
  Todo sampleTodo3;
  Todo sampleTodo4;

  @BeforeEach
  void setUp() throws InvalidTodoException {
    testDisplay = new DisplayTodo(Boolean.TRUE, Boolean.TRUE, null, Boolean.FALSE, Boolean.TRUE);
    sampleTodo1 = new Todo.Builder("sample1").addID(1).addDue(LocalDate.now()).addPriority(3)
        .build();
    sampleTodo2 = new Todo.Builder("sample2").addID(2).addDue(LocalDate.of(2020, 1, 1))
        .addPriority(2)
        .build();
    sampleTodo3 = new Todo.Builder("sample3").addID(3).build();
    sampleTodo4 = new Todo.Builder("sample3").addID(3).build();
    todos = new ArrayList<>();
    todos.add(sampleTodo1);
    todos.add(sampleTodo2);
    todos.add(sampleTodo3);
    todos.add(sampleTodo4);
  }

  @Test
  void run() {
    Displayer.run(testDisplay, todos);
  }
}