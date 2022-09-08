package FileIO;

import java.io.IOException;

/**
 * Required operations for read files
 */
public interface IReader {

  /**
   * Read the information in the given file, and return the structure where the information stored
   * @param filepath - the file to be read, encoded as String
   * @throws IOException           - throws when the i/o stream meets problem
   */
  void read(String filepath) throws IOException;
}
