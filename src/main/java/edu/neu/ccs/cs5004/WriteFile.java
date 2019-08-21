package edu.neu.ccs.cs5004;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class WriteFile, which takes a template and a list of customers and replaces the template text
 * with customer data for each customer.
 */
public class WriteFile implements IWriteFile {

  private List<String> template;
  private List<Customer> customers;

  /**
   * Constructor for WriteFile.
   *
   * @param template the template as a list of strings
   * @param customers the list of customers
   */
  public WriteFile(List<String> template, List<Customer> customers) {
    this.template = template;
    this.customers = customers;
  }

  /**
   * Gets the Template that was given to write of this WriteFile.
   *
   * @return the Template that was given to write of this WriteFile.
   */
  public List<String> getTemplate() {
    return this.template;
  }

  /**
   * Gets the list of customers that was given to write of this WriteFile.
   *
   * @return the list of customers that was given to write of this WriteFile.
   */
  public List<Customer> getCustomers() {
    return this.customers;
  }

  /**
   * Writes a template for all customers in the list of customers.
   * @param path the filepath to write to
   * @throws IOException when the filepath is null
   */
  @Override
  public void writeToAllCustomers(String path) throws IOException {
    for (Customer customer : this.customers) {

      // creating file name for each customer.
      String fileName = customer.findValueOfAttributes("first_name")
          + customer.findValueOfAttributes("last_name") + ".txt";

      // Getting the full path including given path.
      if (path != null) {
        String currentDir = System.getProperty("user.dir") + File.separator + path;
        File directory = new File(currentDir);
        boolean isExist = directory.exists();
        if (!isExist) {
          isExist = directory.mkdir();
        }
        if (isExist) {
          File file = new File(directory + File.separator + fileName);
          List<String> finalTemplate = writeOnTemplate(customer);

          // send this to output directory here!!!
          writeToDirectory(file, finalTemplate);
        }
      } else {
        throw new IOException("Output filepath is null.");
      }
    }
  }

  /**
   * Writes the customer data to the specified template.
   *
   * @param customer the customer to write to the template
   * @return the template, with fields replaced with customer data
   */
  protected List<String> writeOnTemplate(Customer customer) {
    List<String> finalTemplate = new ArrayList<>();
    for (String line : this.template) {
      Pattern pattern = Pattern.compile("\\[\\[(.*?)\\]\\]");
      Matcher matcher = pattern.matcher(line);
      StringBuffer buffer = new StringBuffer();
      while (matcher.find()) {
        String matching = matcher.group(1);
        String value = customer.findValueOfAttributes(matching);
        matcher.appendReplacement(buffer, value);
      }
      matcher.appendTail(buffer);
      if (buffer.toString().equals("")) {
        finalTemplate.add(line);
      } else {
        finalTemplate.add(buffer.toString());
      }
    }
    return finalTemplate;
  }

  /**
   * Write template to the given file and to the given directory.
   * @param file file given to this WriteFile.
   * @param finalTemplate finishedTemplate given to this WriteFile.
   */
  private void writeToDirectory(File file, List<String> finalTemplate) {
    Writer bufferedWriter = null;
    try {
      OutputStream outputStream = new FileOutputStream(file);
      bufferedWriter = new OutputStreamWriter(outputStream, "UTF-8");

      for (String line : finalTemplate) {
        bufferedWriter.write(line);
        bufferedWriter.write("\n");

      }
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    } finally {
      if (bufferedWriter != null) {
        try {
          bufferedWriter.flush();
          bufferedWriter.close();
        } catch (IOException e) {
          System.out.println("Failed to close input stream");
        }
      }
    }
  }

  /**
   * Hashcode function for WriteFile.
   * All automation classes should have the same hash code.
   * @return the hashcode
   */
  @Override
  public int hashCode() {
    return 31 * template.hashCode() * customers.hashCode();

  }

  /**
   * Represents WriteFile as a string.
   * @return a string representation of the writeFile.
   */
  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("Write on this template: \n");
    for (String line : this.template) {
      buf.append(line + "\n");
    }

    return buf.toString();
  }

  /**
   * Compares whether the two objects are equal.
   * @param compare the object to be compared
   * @return true if the objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object compare) {
    if (this == compare) {
      return true;
    }
    if (compare == null || getClass() != compare.getClass()) {
      return false;
    }
    WriteFile object = (WriteFile) compare;
    return this.template.equals(object.template) && this.customers.equals(object.customers);
  }


}
