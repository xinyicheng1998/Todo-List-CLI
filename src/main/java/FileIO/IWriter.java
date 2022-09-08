package FileIO;

import java.io.IOException;

/**
 * Required operations for write files
 */
public interface IWriter {

   /**
    * Write the information to the given file path
    * @param filepath - where the information should be stored
    * @throws IOException when the i/o stream is interrupted
    */
   void write(String filepath)throws IOException;

}
