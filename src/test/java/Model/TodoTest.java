package Model;

import static org.junit.jupiter.api.Assertions.*;

import Model.Todo.Builder;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TodoTest {

  private Todo testTodo;

  @BeforeEach
  void setUp() throws InvalidTodoException {
    try {
      LocalDate testDue = LocalDate.of(2022, 4, 24);
      testTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(testDue)
          .addPriority(1)
          .addCategory("home")
          .build();
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testConstructor_ValidPriority(){
    try {
      Todo otherTodo = new Builder("123").addPriority(2).build();
      assertEquals(2, otherTodo.getPriority());
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testConstructor_NullPriority(){
    try {
      Todo otherTodo = new Builder("123").addPriority(null).build();
      assertEquals(3,otherTodo.getPriority());
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testConstructor_LowerPriority_Exception(){
    try {
      Todo otherTodo = new Builder("123").addPriority(5).build();
      fail("An exception should not have been thrown.");
    }
    catch (InvalidTodoException e){
    }
  }

  @Test
  void testConstructor_HigherPriority_Exception(){
    try {
      Todo otherTodo = new Builder("123").addPriority(0).build();
      fail("An exception should not have been thrown.");
    }
    catch (InvalidTodoException e){
    }
  }

  @Test
  void getId() {
    assertEquals(1, testTodo.getId());
  }

  @Test
  void setId() {
    testTodo.setId(2);
    assertEquals(2, testTodo.getId());
  }

  @Test
  void getText() {
    assertEquals("123", testTodo.getText());
  }

  @Test
  void getCompleted() {
    assertEquals(false, testTodo.getCompleted());
  }

  @Test
  void setCompleted() {
    testTodo.setCompleted();
    assertEquals(true, testTodo.getCompleted());
  }

  @Test
  void getDue() {
    LocalDate expectedDue = LocalDate.of(2022, 4, 24);
    assertEquals(expectedDue, testTodo.getDue());
  }

  @Test
  void getPriority() {
    assertEquals(1, testTodo.getPriority());
  }

  @Test
  void getCategory() {
    assertEquals("home", testTodo.getCategory());
  }

  @Test
  void testEquals_SameMemoryAddress() {
    assertTrue(testTodo.equals(testTodo));
  }

  @Test
  void testEquals_SameFieldsOverall() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertTrue(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentDataType() {
    int num = 1;
    assertFalse(testTodo.equals(num));
  }

  @Test
  void testEquals_DifferentID() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(2)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentText() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("1234").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentCompleted() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(true)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentDue() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 25);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentPriority() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(2)
          .addCategory("home")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testEquals_DifferentCategory() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("work")
          .build();
      assertFalse(testTodo.equals(otherTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testHashCode() {
    try {
      LocalDate otherDue = LocalDate.of(2022, 4, 24);
      Todo otherTodo = new Builder("123").addID(1)
          .addCompleted(false)
          .addDue(otherDue)
          .addPriority(1)
          .addCategory("home")
          .build();
      assertTrue(otherTodo.hashCode() == testTodo.hashCode());
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown.");
    }
  }

  @Test
  void testToString() {
    String expectedString = "Model.Todo{" +
        "id=" + testTodo.getId() +
        ", text='" + testTodo.getText() + '\'' +
        ", completed=" + testTodo.getCompleted() +
        ", due=" + testTodo.getDue() +
        ", priority=" + testTodo.getPriority() +
        ", category='" + testTodo.getCategory() + '\'' +
        '}';
    assertEquals(expectedString, testTodo.toString());
  }
}