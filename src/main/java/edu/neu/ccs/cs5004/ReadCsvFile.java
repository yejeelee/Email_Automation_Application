package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents a ReadCsvFile.
 */
public class ReadCsvFile implements IReadFile {

  private List<String> header;
  private List<Customer> customers;

  /**
   * Construct a new ReadCsvFile with given input.
   *
   * @param list list given to store information.
   */
  public ReadCsvFile(List<Customer> list) {
    this.header = new ArrayList<>();
    this.customers = list;
  }

  /**
   * Gets header from this ReadCsvFile.
   *
   * @return list of attribute of csv from this ReadCsvFile.
   */
  public List<String> getHeader() {
    return this.header;
  }

  /**
   * Gets the CustomerList from this ReadCsvFile.
   * @return List of Customer from this ReadCsvFile.
   */
  public List<Customer> getCustomerList() {
    return this.customers;
  }

  /**
   * Read given file to this ReadCsvFile.
   * @param file file given to this ReadCsvFile.
   */
  @Override
  public void readFile(String file) {
    BufferedReader inputFile = null;
    try {
      InputStream inputStream = new FileInputStream(file);
      Reader fileToRead = new InputStreamReader(inputStream, "UTF-8");
      inputFile = new BufferedReader(fileToRead);
      String line;
      while ((line = inputFile.readLine()) != null) {
        String[] sentence = line.split("\",");
        for (int i = 0; i < sentence.length; i++) {
          sentence[i] = sentence[i].replace("\"", "");
        }
        if (header.size() == 0) {
          for (String word : sentence) {
            header.add(word);
          }
        } else {
          addInfo(sentence);
        }
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
    } catch (IOException ioe) {
      System.out.println("Something went wrong! : " + ioe.getMessage());
    } finally {
      if (inputFile != null) {
        try {
          inputFile.close();
        } catch (IOException e) {
          System.out.println("Failed to close input stream");
        }
      }
    }
  }

  /**
   * Add information to store into a list of this ReadCsvFile.
   *
   * @param info info given to this ReadCsvFile.
   */
  private void addInfo(String[] info) {
    if (info.length == header.size()) {
      Map<String, String> customerInfo = new HashMap<>();
      for (int i = 0; i < header.size(); i++) {
        customerInfo.put(header.get(i), info[i]);
      }
      customers.add(new Customer(customerInfo));
    }
  }

  /**
   * Hashcode function for ReadCsvFile.
   *
   * @return hashcode integer based on the hashcode for the header and customers.
   */
  @Override
  public int hashCode() {
    return 31 * header.hashCode() * customers.hashCode();

  }

  /**
   * A string representation of the ReadCsvFile class.
   *
   * @return the string of customers read in from the CSV.
   */
  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("Current customers: \n");
    int customerNum = 1;
    for (Customer customer : customers) {
      buf.append("Customer " + customerNum + "\n");
      for (String variable : header) {
        buf.append(variable + ": " + customer.findValueOfAttributes(variable) + "\n");
      }
      customerNum++;
    }
    return buf.toString();
  }

  /**
   * Compares two objects and determines if they are equal.
   *
   * @param compare the object to be compared
   * @return true if the object header and customer list are equal, false otherwise.
   */

  @Override
  public boolean equals(Object compare) {
    if (this == compare) {
      return true;
    }
    if (compare == null || getClass() != compare.getClass()) {
      return false;
    }
    ReadCsvFile object = (ReadCsvFile) compare;
    return this.getHeader().equals(object.getHeader())
        && this.getCustomerList().equals(object.getCustomerList());
  }
}

