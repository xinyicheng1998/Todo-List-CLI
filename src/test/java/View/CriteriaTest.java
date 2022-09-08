package View;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Model.InvalidTodoException;
import Model.Todo;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;


class CriteriaTest {

  ArrayList<Todo> todos;
  Todo sampleTodo1;
  Todo sampleTodo2;
  Todo sampleTodo3;

  @BeforeEach
  void setUp() throws InvalidTodoException {
    sampleTodo1 = new Todo.Builder("sample1").addCompleted(false).addID(1).build();
    sampleTodo2 = new Todo.Builder("sample2").addCompleted(false).addID(1).addCategory("home")
        .build();
    sampleTodo3 = new Todo.Builder("sample3").addCompleted(true).addID(3).build();
    todos = new ArrayList<>();
    todos.add(sampleTodo1);
    todos.add(sampleTodo2);
    todos.add(sampleTodo3);
  }

  @Test
  void CriteriaCategory() {
    CriteriaCategory category = new CriteriaCategory("home");
    assertEquals(1, category.meetCriteria(todos).size());
  }

  @Test
  void CriteriaCompletion() {
    CriteriaCompletion incompletion = new CriteriaCompletion(false);
    assertEquals(2, incompletion.meetCriteria(todos).size());
  }
}