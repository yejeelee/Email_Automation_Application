package edu.neu.ccs.cs5004;



/**
 * This class represents Automation.
 */
public class Automation {
  static final int ARGUMENT_LENGTH = 7;

  /**
   * Takes arguments and generate message with given template and
   * given customer information to the given output directory.
   *
   * @param args arguments given to this Automation.
   */
  public static void main(String[] args) {
    Parser parseCommand = new Parser();
    if (args.length != ARGUMENT_LENGTH) {
      throw new IllegalArgumentException("too small amount of arguments");
    } else {
      String output = parseCommand.parseCommand(args);
      if (!output.equals("")) {
        System.out.println(output);
        throw new IllegalArgumentException("wrong format of arguments");
      }
    }
    Processor.process(parseCommand);
  }

  /**
   * Hashcode function for automation. All automation classes should have the same hash code.
   * @return the hashcode of this automation.
   */
  @Override
  public int hashCode() {
    return 29;
  }

  /**
   * A string representation of the class.
   * @return a description of automation class.
   */
  @Override
  public String toString() {
    return "Email Automation Application";
  }

  /**
   * A function to determine whether two objects are equal.
   * @param compare the object to compare
   * @return boolean true for all new Automation objects, false otherwise.
   */
  @Override
  public boolean equals(Object compare) {
    if (this == compare) {
      return true;
    }
    if (compare == null || getClass() != compare.getClass()) {
      return false;
    }

    Automation object = (Automation) compare;

    return this.toString().equals(object.toString());
  }

}
