package Model;

import static org.junit.jupiter.api.Assertions.*;

import FileIO.FileState;
import View.CriteriaCompletion;
import View.Printer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CRUDTest {

  ArrayList<Todo> todos;
  Todo sampleTodo;
  String filepath = "test.csv";
  FileState fs;

  @BeforeEach
  void setUp() throws InvalidTodoException, IOException {
    fs = FileState.getInstance();
    fs.read(filepath);
    sampleTodo = new Todo.Builder("sample").addCompleted(false).addID(1).build();
    todos = new ArrayList<>();
    todos.add(sampleTodo);
  }

  @Test
  void addNewRow() throws IOException {
    CRUD.addNewRow(todos, sampleTodo, filepath);
    assertEquals(2, todos.size());
  }

  @Test
  void completeTodo() throws IOException {
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ids.add(1);
    CRUD.completeTodo(todos, ids, filepath);
    CriteriaCompletion incomplete = new CriteriaCompletion(false);
    assertEquals(0, incomplete.meetCriteria(todos).size());
  }
}