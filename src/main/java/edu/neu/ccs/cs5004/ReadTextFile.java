package edu.neu.ccs.cs5004;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents ReadTextFile.
 */
public class ReadTextFile implements IReadFile {

  private List<String> wholeText;

  /**
   * Construct a new ReadTextFile.
   */
  public ReadTextFile() {
    wholeText = new ArrayList<>();
  }

  /**
   * Gets list of strings of this file from this ReadTextFile.
   *
   * @return list of strings of this file from this ReadTextFile.
   */
  public List<String> getWholeText() {
    return this.wholeText;
  }

  /**
   * Read given file of this ReadTextFile.
   * @param file file given to this ReadTextFile.
   */
  public void readFile(String file) {
    BufferedReader inputFile = null;
    try {
      InputStream inputStream = new FileInputStream(file);
      Reader fileToRead = new InputStreamReader(inputStream, "UTF-8");
      inputFile = new BufferedReader(fileToRead);

      String line;
      while ((line = inputFile.readLine()) != null) {
        wholeText.add(line);
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
   * Hashcode function for the ReadTextFile class.
   * @return a hash code as an integer based on the wholeText.
   */
  @Override
  public int hashCode() {
    return 31 * wholeText.hashCode();

  }

  /**
   * A string representation of this ReadTextFile class.
   * @return the wholeText as a string.
   */
  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    //String text = "";
    for (String string : wholeText) {
      buf.append(string + "\n");
    }
    return buf.toString();
  }

  /**
   * Compares whether two objects are equal.
   * @param compare the object to be compared
   * @return true if the wholeText is equal, false otherwise.
   */
  @Override
  public boolean equals(Object compare) {
    if (this == compare) {
      return true;
    }
    if (compare == null || getClass() != compare.getClass()) {
      return false;
    }
    ReadTextFile object = (ReadTextFile) compare;
    return this.getWholeText().equals(object.getWholeText());
  }

}
