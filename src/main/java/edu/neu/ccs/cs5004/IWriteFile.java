package edu.neu.ccs.cs5004;

import java.io.IOException;

/**
 * This interface represents IWriteFile.
 */
public interface IWriteFile {

  /**
   * Write text file to the given path.
   * @param path output directory name given to this IWriteFile.
   * @throws IOException with errors to input/output stream
   */
  void writeToAllCustomers(String path) throws IOException;

}
