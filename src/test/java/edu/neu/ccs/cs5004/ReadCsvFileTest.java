package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReadCsvFileTest {
  List<Customer> list = new ArrayList<>();
  ReadCsvFile csvFile = new ReadCsvFile(list);
  String filepath = System.getProperty("user.dir");
  List<Customer> smallList = new ArrayList<>();
  ReadCsvFile rearranged = new ReadCsvFile(smallList);

  @Before
  public void setUp() throws Exception {
    System.out.println(filepath);
    csvFile.readFile(filepath + "/insurance_company_members.csv");

    rearranged.readFile(filepath + "/small_insurance_company_members.csv");


  }

  @Test
  public void getHeader() {
    List<String> keys = csvFile.getHeader();
    Assert.assertEquals(true, keys.contains("first_name"));
    Assert.assertEquals("first_name", csvFile.getHeader().get(0));

    List<String> smallKeys = rearranged.getHeader();
    Assert.assertEquals(true, smallKeys.contains("email"));
    Assert.assertEquals("email", rearranged.getHeader().get(0));

  }

  @Test
  public void readFile() throws FileNotFoundException {

    try {
      csvFile.readFile(filepath + "/hello.csv");
      BufferedReader inputFile = new BufferedReader(new FileReader("hello.csv"));
      fail("Expected an FileNotFoundException to be thrown");

    } catch (FileNotFoundException e) {

    }

    try {
      csvFile.readFile(filepath + "/small_insurance_company_members.csv");
      BufferedReader inputFile =
          new BufferedReader(new FileReader(filepath
              + "/small_insurance_company_members.csv"));
    } catch (IOException e) {
      fail("Expected an FileNotFoundException should not be thrown");

    }


    try {
      csvFile.readFile(filepath + "/small_insurance_company_members.csv");
      BufferedReader inputFile =
          new BufferedReader(new FileReader("hi"
              + filepath + "/small_insurance_company_members.csv"));
      fail("Expected an IOException should be thrown");
    } catch (IOException e) {

    }

    try {
      BufferedReader br = new BufferedReader(new FileReader("small_insurance_company_members.csv"));
      br.close();
    } catch (IOException e) {
      fail("IOException should not be thrown");
    }

  }

  @Test
  public void toStringTest() {
    boolean stringCheck = csvFile.toString().contains("Current customers: \n" +
            "Customer 1\n" +
            "first_name: James\n" +
            "last_name: Butt\n" +
            "company_name: Benton, John B Jr\n" +
            "address: 6649 N Blue Gum St\n" +
            "city: New Orleans\n" +
            "county: Orleans\n" +
            "state: LA\n" +
            "zip: 70116\n" +
            "phone1: 504-621-8927\n" +
            "phone2: 504-845-1427\n" +
            "email: jbutt@gmail.com\n" +
            "web: http://www.bentonjohnbjr.com\n" +
            "Customer 2\n" +
            "first_name: Josephine\n" +
            "last_name: Darakjy\n");

    assertTrue(stringCheck);

    boolean rearrangedStringCheck = rearranged.toString().contains("Current customers: \n" +
            "Customer 1\n" +
            "email: jbutt@gmail.com\n" +
            "first_name: James\n" +
            "last_name: Butt\n" +
            "company_name: Benton, John B Jr\n" +
            "address: 6649 N Blue Gum St\n" +
            "city: New Orleans\n" +
            "county: Orleans\n" +
            "state: LA\n" +
            "zip: 70116\n" +
            "phone1: 504-621-8927\n" +
            "phone2: 504-845-1427\n" +
            "web: http://www.bentonjohnbjr.com\n" +
            "Customer 2\n" +
            "email: josephine_darakjy@darakjy.org\n" +
            "first_name: Josephine\n" +
            "last_name: Darakjy\n");

    assertTrue(rearrangedStringCheck);

  }

  @Test
  public void hashCodeTest() {
    int hash = 31 * csvFile.getHeader().hashCode() * csvFile.getCustomerList().hashCode();
    assertEquals(hash, csvFile.hashCode());

  }


  @Test
  public void equalsTest() {
    ReadCsvFile copy = new ReadCsvFile(list);
    copy.readFile(filepath + "/insurance_company_members.csv");

    List<Customer> list2 = new ArrayList<>();

    ReadCsvFile different = new ReadCsvFile(list2);
    different.readFile(filepath + "/small_insurance_company_members.csv");
    assertEquals(csvFile, csvFile);
    assertNotNull(csvFile);
    assertNotEquals(csvFile, different);
   assertFalse(csvFile.equals(list));
    assertEquals(csvFile, copy);
    assertNotEquals(null, csvFile);


  }
}