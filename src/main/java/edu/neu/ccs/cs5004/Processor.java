package edu.neu.ccs.cs5004;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represent Processor that accepts Parser object from main().
 * Helps main function read template, read csv file
 * and write files to output directory.
 */
public class Processor {


  /**
   * This method handles the whole process of reading and writing.
   * @param parseCommand parseCommand Object from the main().
   */
  public static void process(Parser parseCommand) {
    List<String> template = verifyTemplate(parseCommand);

    List<Customer> customers = verifyCsv(parseCommand);

    WriteFile writeFile = new WriteFile(template, customers);
    try {
      writeFile.writeToAllCustomers(parseCommand.getOutputPath());
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }


  /**
   * Verify the type of template and read the template.
   * @param parseCommand parseCommand Object from the main() that
   *        contains all parsed information.
   * @return the template in list of String.
   */
  public static List<String> verifyTemplate(Parser parseCommand) {
    ReadTextFile textFile = new ReadTextFile();
    if (parseCommand.isEmail()) {
      textFile.readFile(parseCommand.getEmailTemplate());
    } else if (parseCommand.isLetter()) {
      textFile.readFile(parseCommand.getLetterTemplate());
    }

    List<String> template = textFile.getWholeText();

    return template;
  }

  /**
   * Verify if it is a written csv or empty csv.
   * @param parseCommand parseCommand Object from the main() that
   *        contains all parsed information.
   * @return the list of Customers that are going to receive a template.
   */
  public static List<Customer> verifyCsv(Parser parseCommand) {
    List<Customer> customers = new ArrayList<>();
    ReadCsvFile csvFile = new ReadCsvFile(customers);
    if (!parseCommand.getCsv().equals("")) {
      csvFile.readFile(parseCommand.getCsv());
    }
    return customers;
  }

  /**
   * Returns the hashCode of this Processor class.
   * @return 42, the hashCode of this Processor.
   */
  @Override
  public int hashCode() {
    return 42;
  }

  /**
   * Returns the String representation of this Processor.
   * @return the String representation of this Processor.
   */
  @Override
  public String toString() {
    return "Processor\n";
  }


  /**
   * Checks if the object is equal to Processor.
   * @param obj - object given to compare.
   * @return true if the object is equal to processor, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || obj instanceof Processor;
  }


}
