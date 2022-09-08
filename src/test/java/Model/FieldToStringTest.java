package Model;

import static org.junit.jupiter.api.Assertions.*;

import Model.Todo.Builder;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldToStringTest {

  @Test
  void todoToString_null(){
    try{
      Todo nullTodo = new Builder("123")
          .addID(1)
          .addCompleted(null)
          .addDue(null)
          .addPriority(null)
          .addCategory(null)
          .build();
      String expectedString = "\"1\",\"123\",\"false\",\"?\",\"3\",\"?\"";
      assertEquals(expectedString, FieldToString.TodoToString(nullTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown");
    }
  }

  @Test
  void todoToString_NotNull(){
    try{
      LocalDate testDue = LocalDate.of(2022, 04, 24);
      Todo nullTodo = new Builder("123")
          .addID(1)
          .addCompleted(true)
          .addDue(testDue)
          .addPriority(null)
          .addCategory("home")
          .build();
      String expectedString = "\"1\",\"123\",\"true\",\"2022-04-24\",\"3\",\"home\"";
      assertEquals(expectedString, FieldToString.TodoToString(nullTodo));
    }
    catch (InvalidTodoException e){
      fail("An exception should not have been thrown");
    }
  }
}