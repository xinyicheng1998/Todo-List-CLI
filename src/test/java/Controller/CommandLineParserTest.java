package Controller;

import static org.junit.jupiter.api.Assertions.*;

import Model.InvalidTodoException;
import Model.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineParserTest {
  CommandLineParser testCMD;
  DisplayTodo testDisplay;

  @BeforeEach
  void setUp() {
    testCMD = new CommandLineParser();
    testDisplay = new DisplayTodo(Boolean.TRUE, Boolean.TRUE, null, Boolean.FALSE, Boolean.TRUE);
  }

  @Test
    //a valid command line
  void testCMDValid1() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();

    HashMap<String, Object> expectedResult = new HashMap<String, Object>();
    LocalDate testDate = LocalDate.parse("2021-12-20");
    Todo testTodo = new Todo.Builder( "success").addCompleted(Boolean.TRUE).addDue(testDate).addPriority(1).addCategory("school").build();
    ArrayList<Integer> completeList = new ArrayList<Integer>();
    completeList.add(3);
    expectedResult.put("csv", "todos.csv");
    expectedResult.put("add todo", testTodo);
    expectedResult.put("complete todo", completeList);
    expectedResult.put("display todo", testDisplay);

    assertEquals(expectedResult, test.parse(args));
  }

  @Test
    //a valid command line
  void testCMDValid2() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed"};
    CommandLineParser test = new CommandLineParser();

    HashMap<String, Object> expectedResult = new HashMap<String, Object>();
    LocalDate testDate = LocalDate.parse("2021-12-20");
    Todo testTodo = new Todo.Builder( "success").addCompleted(Boolean.TRUE).addDue(testDate).addPriority(1).addCategory("school").build();
    ArrayList<Integer> completeList = new ArrayList<Integer>();
    completeList.add(3);
    expectedResult.put("csv", "todos.csv");
    expectedResult.put("add todo", testTodo);
    expectedResult.put("complete todo", completeList);
    expectedResult.put("display todo", null);

    assertEquals(expectedResult, test.parse(args));
  }

  // invalid, no csv command
  @Test
  void testCMDInvalid1() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed", "--display", "--show-incomplete", "--show-category", "school", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "CSV command is required");
    }
  }

  // invalid, duplicate csv command
  @Test
  void testCMDInvalid2() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "--csv-file", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "Repeated CSV command entered.");
    }
  }

  // invalid, no csv file follows csv command
  @Test
  void testCMDInvalid3() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed", "--display", "--show-incomplete", "--sort-by-priority", "--csv-file"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "csv command is an the end, no path follows");
    }
  }

  // invalid, given csv file is invalid
  @Test
  void testCMDInvalid4() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "green", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--complete-todo", "3", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "Invalid path entered: green");
    }
  }

  // invalid, no valid id follows complete command
  @Test
  void testCMDInvalid5() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority", "--complete-todo"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "the command of complete is at the end, no valid id follows");
    }
  }

  // invalid, multiple add command, only one is allowed
  @Test
  void testCMDInvalid6() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--add-todo", "--todo-text", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "Only one Add todo is accepted in one command");
    }
  }

  // invalid, no description follow add command
  @Test
  void testCMDInvalid7() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "success", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "A description of the new todo is required");
    }
  }


  // invalid, due of add command at the end, no date follows
  @Test
  void testCMDInvalid8() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority", "--due"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "due of add command is at the end, no date follows");
    }
  }

  // invalid, priority of add command at the end, no date follows
  @Test
  void testCMDInvalid9() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success","--due", "2021-12-20", "--category", "school", "--completed", "--priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "priority of add command is at the end, no number follows");
    }
  }

  // invalid, category of add command at the end, no date follows
  @Test
  void testCMDInvalid10() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success","--due", "2021-12-20",  "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority", "--category"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "category of add command is at the end, no string follows");
    }
  }

  // invalid, given due date of add command is not in valid format
  @Test
  void testCMDInvalid11() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--due", "2021", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "given due date is invalid, format yyyy-mm-dd is expected");
    }
  }

  // invalid, two types of sort for display are given
  @Test
  void testCMDInvalid12() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--display", "--sort-by-priority", "--sort-by-date"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "two sort types at the same time is invalid");
    }
  }

  // invalid, category of display command is at the end, no specific category follows
  @Test
  void testCMDInvalid13() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv","--display", "--show-category"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "category of display command is at the end, no string follows");
    }
  }

  // invalid, command of to do text is at the end, no description follows
  @Test
  void testCMDInvalid14() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--due", "2021-12-20", "--category", "school", "--priority", "1", "--completed", "--display", "--show-incomplete", "--sort-by-priority", "--todo-text"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "todo-text command is at the end, no description follows");
    }
  }

  // invalid, no valid command given, only contains csv command
  @Test
  void testCMDInvalid15() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "no command of the three functionalities entered");
    }
  }

  // invalid, no valid command given, only contains csv command
  @Test
  void testCMDInvalid16() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--display", "--show-category", "school", "--sort-by-date", "--delete"};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "unexpected commands entered");
    }
  }

  // invalid, no integer follows priority of add command
  @Test
  void testCMDInvalid17() throws InvalidArgumentsException, InvalidTodoException {
    String[] args = new String[]{"--csv-file", "todos.csv", "--add-todo", "--todo-text", "success", "--priority", null};
    CommandLineParser test = new CommandLineParser();
    try {
      HashMap<String, Object> expectedResult = test.parse(args);

    } catch (InvalidArgumentsException e) {
      assertEquals(e.getMessage(), "An integer is required after priority command");
    }
  }

  @Test
  void testToString_CommandLineParser(){
    String expectedString = "CommandLineParser{}";
    assertEquals(expectedString, testCMD.toString());
  }

  @Test
  void testToString_CmdParserCSV(){
    CmdParserCSV testCSV = new CmdParserCSV();
    String expectedString = "CmdParserCSV{}";
    assertEquals(expectedString, testCSV.toString());
  }

  @Test
  void testToString_CmdParserAdd(){
    CmdParserAdd testAdd = new CmdParserAdd();
    String expectedString = "CmdParserAdd{}";
    assertEquals(expectedString, testAdd.toString());
  }

  @Test
  void testToString_CmdParserComplete(){
    CmdParserComplete testComplete = new CmdParserComplete();
    String expectedString = "CmdParserComplete{}";
    assertEquals(expectedString, testComplete.toString());
  }

  @Test
  void testToString_CmdParserDisplay(){
    CmdParserDisplay testDisplay = new CmdParserDisplay();
    String expectedString = "CmdParserDisplay{}";
    assertEquals(expectedString, testDisplay.toString());
  }

  @Test
  void testDisplayTodo_getDisplay(){
    assertEquals(Boolean.TRUE, testDisplay.getDisplay());
  }

  @Test
  void testDisplayTodo_toString(){
    String expected = "DisplayTodo{" +
        "isDisplay=" + Boolean.TRUE +
        ", isShowIncomplete=" + Boolean.TRUE +
        ", showCategory='" + null + '\'' +
        ", isSortByDate=" + Boolean.FALSE +
        ", isSortByPriority=" + Boolean.TRUE +
        '}';
    assertEquals(expected, testDisplay.toString());
  }

  @Test
  void testDisplayTodo_hashcode(){
    int expectedHashcode = Objects.hash(Boolean.TRUE, Boolean.TRUE, null, Boolean.FALSE, Boolean.TRUE);
    assertEquals(expectedHashcode, testDisplay.hashCode());
  }

  @Test
  void testDisplayTodo_equalsSameObject(){
    assertTrue(testDisplay.equals(testDisplay));
  }

  @Test
  void testDisplayTodo_equalsNullObject(){
    assertFalse(testDisplay.equals(null));
  }

  @Test
  void testDisplayTodo_equalsDifferentObject(){
    assertFalse(testDisplay.equals(1));
  }

  @Test
  void testDisplayTodo_equalsDifferentAttribute1(){
    DisplayTodo newDisplay = new DisplayTodo(Boolean.FALSE, Boolean.TRUE, null, Boolean.FALSE, Boolean.TRUE);
    assertFalse(testDisplay.equals(newDisplay));
  }

  @Test
  void testDisplayTodo_equalsDifferentAttribute2(){
    DisplayTodo newDisplay = new DisplayTodo(Boolean.TRUE, Boolean.FALSE, null, Boolean.FALSE, Boolean.TRUE);
    assertFalse(testDisplay.equals(newDisplay));
  }

  @Test
  void testDisplayTodo_equalsDifferentAttribute3(){
    DisplayTodo newDisplay = new DisplayTodo(Boolean.TRUE, Boolean.TRUE, "school", Boolean.FALSE, Boolean.TRUE);
    assertFalse(testDisplay.equals(newDisplay));
  }

  @Test
  void testDisplayTodo_equalsDifferentAttribute4(){
    DisplayTodo newDisplay = new DisplayTodo(Boolean.TRUE, Boolean.TRUE, null, Boolean.TRUE, Boolean.TRUE);
    assertFalse(testDisplay.equals(newDisplay));
  }

  @Test
  void testDisplayTodo_equalsDifferentAttribute5(){
    DisplayTodo newDisplay = new DisplayTodo(Boolean.TRUE, Boolean.TRUE, null, Boolean.FALSE, Boolean.FALSE);
    assertFalse(testDisplay.equals(newDisplay));
  }


}