package edu.neu.ccs.cs5004;

/**
 * Interface for Parser.
 */

public interface IParser {

  /**
   * * Method parseCommand to parse the commands.
   *
   * @param args The arguments to parse.
   * @return String of error message if the arguments cannot be parsed.
   */

  String parseCommand(String[] args);

}