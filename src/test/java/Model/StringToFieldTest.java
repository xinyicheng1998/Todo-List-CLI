package Model;

import static org.junit.jupiter.api.Assertions.*;

import Model.Todo.Builder;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import jdk.vm.ci.meta.Local;
import org.junit.jupiter.api.Test;

class StringToFieldTest {

  @Test
  void hashmapToTodo_NoException() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2");
      item.put("text", "abc");
      item.put("completed", "true");
      item.put("due", "2022-04-24");
      item.put("priority", "2");
      item.put("category", "home");
      LocalDate expectedDue = LocalDate.of(2022, 4, 24);
      Todo expectedTodo = new Builder("abc").addID(2)
          .addCompleted(true)
          .addDue(expectedDue)
          .addPriority(2)
          .addCategory("home")
          .build();
      assertEquals(expectedTodo, StringToField.HashmapToTodo(item));
    }
    catch (IllegalArgumentException | InvalidTodoException iae){
      fail("An exception should not have been throw");
    }
  }

  @Test
  void hashmapToTodo_StringToInteger_NumberFormatException() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2a");
      item.put("text", "abc");
      item.put("completed", "true");
      item.put("due", "2022-04-24");
      item.put("priority", "2");
      item.put("category", "home");
      StringToField.HashmapToTodo(item);
      fail("An exception should have been throw");
    }
    catch (NumberFormatException nfe){
    } catch (InvalidTodoException e) {
      e.printStackTrace();
    }
  }

  @Test
  void hashmapToTodo_StringToBoolean_Null() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2");
      item.put("text", "abc");
      item.put("completed", "?");
      item.put("due", "2022-04-24");
      item.put("priority", "2");
      item.put("category", "home");
      LocalDate expectedDue = LocalDate.of(2022, 4, 24);
      Todo expectedTodo = new Builder("abc").addID(2)
          .addCompleted(false)
          .addDue(expectedDue)
          .addPriority(2)
          .addCategory("home")
          .build();
      assertEquals(expectedTodo, StringToField.HashmapToTodo(item));
    }
    catch (IllegalArgumentException | InvalidTodoException iae){
      fail("An exception should not have been throw");
    }
  }

  @Test
  void hashmapToTodo_StringToBoolean_False() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2");
      item.put("text", "abc");
      item.put("completed", "false");
      item.put("due", "2022-04-24");
      item.put("priority", "2");
      item.put("category", "home");
      LocalDate expectedDue = LocalDate.of(2022, 4, 24);
      Todo expectedTodo = new Builder("abc").addID(2)
          .addCompleted(false)
          .addDue(expectedDue)
          .addPriority(2)
          .addCategory("home")
          .build();
      assertEquals(expectedTodo, StringToField.HashmapToTodo(item));
    }
    catch (IllegalArgumentException | InvalidTodoException iae){
      fail("An exception should not have been throw");
    }
  }

  @Test
  void hashmapToTodo_StringToBoolean_IllegalArgumentException() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2");
      item.put("text", "abc");
      item.put("completed", "abc");
      item.put("due", "2022-04-24");
      item.put("priority", "2");
      item.put("category", "home");
      StringToField.HashmapToTodo(item);
      fail("An exception should have been throw");
    }
    catch (IllegalArgumentException iae){
    } catch (InvalidTodoException e) {
      e.printStackTrace();
    }
  }

  @Test
  void hashmapToTodo_StringToLocalDate_DateTimeParseException() {
    try{
      HashMap<String, String> item = new HashMap<>();
      item.put("id", "2");
      item.put("text", "abc");
      item.put("completed", "true");
      item.put("due", "abc");
      item.put("priority", "2");
      item.put("category", "home");
      StringToField.HashmapToTodo(item);
      fail("An exception should have been throw");
    }
    catch (DateTimeParseException dtpe){
    } catch (InvalidTodoException e) {
      e.printStackTrace();
    }
  }
}