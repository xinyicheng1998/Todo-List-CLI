package FileIO;

import static org.junit.jupiter.api.Assertions.*;

import Model.InvalidTodoException;
import Model.Todo;
import Model.Todo.Builder;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileStateTest {

  private FileState testFileState;

  @BeforeEach
  void setUp() {
    testFileState = FileState.getInstance();
  }

  @Test
  void getInstance() {
    FileState otherFileState = FileState.getInstance();
    assertEquals(otherFileState, testFileState);
  }

  @Test
  void getCsvInfo() {
    assertEquals(null, testFileState.getCsvInfo());
  }

  // see test for setCsvInfo in CRUDTest.saveToFile

  @Test
  void read_EmptyFile() {
    try {
      testFileState.read("empty.csv");
    }
    catch (IOException e){
      fail("An exception should not have been thrown");
    }
  }

  // see test for other read in CRUDTest

  // see test for write in CRUDTest

}