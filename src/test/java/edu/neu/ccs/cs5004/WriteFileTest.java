package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

public class WriteFileTest {
  private List<Customer> customers = new ArrayList<>();
  private Map<String, String> customer1 = new HashMap<>();
  private ReadTextFile readTextFile = new ReadTextFile();
  private List<String> template = readTextFile.getWholeText();
  private ReadCsvFile readCsvFile = new ReadCsvFile(customers);
  private WriteFile writeFile = new WriteFile(template, customers);
  private String currentDir = System.getProperty("user.dir");


  @Before
  public void setUp() throws Exception {
    readTextFile.readFile(currentDir + "/emailtemplate.txt");
    customer1.put("first_name", "Jenny");
    customer1.put("last_name", "Lee");
    customer1.put("address", "4115 Roosevelt Way NE");
    customer1.put("city", "Seattle");
    customer1.put("county", "King");
    customer1.put("state", "WA");
    customer1.put("zip", "98105");
    customer1.put("email", "yejeelee94@gmail.com");
    customer1.put("company_name", "yej INC");
  }


  @Test
  public void writeOnTemplate() {
    Assert.assertEquals("{zip=98105, address=4115 Roosevelt Way NE,"
        + " city=Seattle, company_name=yej INC, county=King, last_name=Lee, state=WA,"
        + " first_name=Jenny, email=yejeelee94@gmail.com}", customer1.toString());
    List<String> finalText = writeFile.writeOnTemplate(new Customer(customer1));
    Assert.assertEquals("From:insuranceCompany@ic.com", finalText.get(0));
    Assert.assertEquals("To:yejeelee94@gmail.com", finalText.get(1));
    Assert.assertEquals("Dear Jenny Lee,", finalText.get(3));


    ReadTextFile readLetterFile = new ReadTextFile();
    readLetterFile.readFile("lettertemplate.txt");
    List<String> letter = readLetterFile.getWholeText();
    WriteFile writeLetterFile = new WriteFile(letter, customers);
    List<String> LetterText = writeLetterFile.writeOnTemplate(new Customer(customer1));
    Assert.assertEquals("yej INC.", LetterText.get(0));
    Assert.assertEquals("Jenny Lee", LetterText.get(1));
  }


  @Test
  public void writeToAllCustomersTest() {
    List<Customer> testList = new ArrayList<>();
    Map<String, String> info1 = new HashMap<>();
    info1.put("first_name", "Jewel");
    info1.put("last_name", "Yuzon");
    info1.put("address", "Somewhere");
    info1.put("city", "Over");
    info1.put("county", "The");
    info1.put("state", "Rainbow");
    info1.put("zip", "Where");
    info1.put("email", "jewelyuzon");
    info1.put("company_name", "northeastern");
    Customer customer1 = new Customer(info1);

    Map<String, String> info2 = new HashMap<>();
    info2.put("first_name", "Jenny");
    info2.put("last_name", "Lee");
    info2.put("address", "4115 Roosevelt Way NE");
    info2.put("city", "Seattle");
    info2.put("county", "King");
    info2.put("state", "WA");
    info2.put("zip", "98105");
    info2.put("email", "yejeelee94@gmail.com");
    info2.put("company_name", "yej INC");
    Customer customer2 = new Customer(info2);
    testList.add(customer1);
    testList.add(customer2);

    WriteFile test2 = new WriteFile(template, testList);
    try {
    test2.writeToAllCustomers("testDir1");
      WriteFile test3 = new WriteFile(template, testList);
      test3.writeToAllCustomers(null);
      //test3.writeToAllCustomers(");
    } catch (IOException e) {
      System.err.println(e.getMessage());
      assertEquals("Output filepath is null.", e.getMessage());
    }

  }

  @Test
  public void getTemplateTest() {
    Assert.assertEquals(template, writeFile.getTemplate());
  }

  @Test
  public void getCustomerListTest() {
    Assert.assertEquals(customers, writeFile.getCustomers());
  }

  @Test
  public void hashCodeTest() {
    int hash = 31 * template.hashCode() * customers.hashCode();
    assertEquals(hash, writeFile.hashCode());

  }

  @Test
  public void toStringTest() {
    String text = "Write on this template: \n" + "From:insuranceCompany@ic.com\n" +
            "To:[[email]]\n" +
            "Subject: Insurance company – information about recent data breach\n" +
            "Dear [[first_name]] [[last_name]],\n" +
            "As you may have heard or read, last month we learned that criminals forced their way into our systems, \n" +
            "and stole information about our customers. Late last week, as part of our ongoing investigation, \n" +
            "we learned that the taken information includes names, mailing addresses, phone numbers or email addresses.\n" +
            " \n" +
            "I am writing to make you aware that your name, mailing address, phone number or email address may have been \n" +
            "taken during the intrusion. \n" +
            "\n" +
            "I am truly sorry this incident occurred, and I sincerely regret any inconvenience it may cause you. \n" +
            "\n" +
            "Because we value you as a customer, and because your trust is important to us, our company is offering you one \n" +
            "year of free credit monitoring through Experian’s ProtectMyID product, which includes identity theft insurance \n" +
            "where available. You will receive more information about this offer via regular mail.\n" +
            "\n" +
            "You can find additional information and FAQs about this incident at our website. If you have further questions, \n" +
            "you may call us at 866-852-8680. \n" +
            "\n" +
            "Thank you for your patience and your loyalty. \n" +
            "Sincerely,\n" +
            "Insurance Company CEO\n" +
            "\n";

    Assert.assertEquals(text, writeFile.toString());



  }

  @Test
  public void equalsToTest() {
    WriteFile copy = new WriteFile(template, customers);
    String letterPath = currentDir + "/lettertemplate.txt";

    List<Customer> differentCustomer = new ArrayList<>();
    Map<String, String> customer2 = new HashMap<>();
    customer2.put("first_name", "Jewel");
    customer2.put("last_name", "Yuzon");
    customer2.put("address", "Somewhere");
    customer2.put("city", "Over");
    customer2.put("county", "The");
    customer2.put("state", "Rainbow");
    customer2.put("zip", "Where");
    customer2.put("email", "jewelyuzon");
    customer2.put("company_name", "northeastern");

    Customer jewel = new Customer(customer2);
    differentCustomer.add(jewel);

    WriteFile different = new WriteFile(readTextFile.getWholeText(), differentCustomer);

    Assert.assertTrue(copy.equals(writeFile));
    Assert.assertFalse(different.equals(writeFile));
    Assert.assertFalse(copy.equals(null));
    Assert.assertTrue(copy.equals(copy));
    assertEquals(copy.getTemplate(), writeFile.getTemplate());
    assertEquals(copy.getCustomers(), writeFile.getCustomers());
    assertNotEquals(different.getCustomers(), writeFile.getCustomers());


  }

}