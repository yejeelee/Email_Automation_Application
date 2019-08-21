package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReadTextFileTest {

  ReadTextFile bufferFile = new ReadTextFile();
  String filepath;


  @Before
  public void setUp() throws Exception {
    filepath = System.getProperty("user.dir");
    bufferFile.readFile(filepath + "/emailtemplate.txt");

  }

  @Test
  public void getWholeText() {
    List<String> text = bufferFile.getWholeText();
    Assert.assertEquals("From:insuranceCompany@ic.com", text.get(0));
  }

  @Test
  public void readFile() {
    try {
      bufferFile.readFile(filepath + "/hello.csv");
      BufferedReader inputFile = new BufferedReader(new FileReader("hello.csv"));
      fail("Expected an FileNotFoundException to be thrown");
    } catch (FileNotFoundException e) {

    }


    try {
      // open input stream test.txt for reading purpose.
      BufferedReader br = new BufferedReader(new FileReader("emailtemplate.txt"));
      String thisLine = null;
      while ((thisLine = br.readLine()) != null) {
        System.out.println(thisLine);
      }
    } catch(IOException e) {
      fail("IOException should not be thrown");
    }

    try {
      BufferedReader br = new BufferedReader(new FileReader("emailtemplate.txt"));
      br.close();
    } catch (IOException e) {
      fail("IOException should not be thrown");
    }


  }

  @Test
  public void toStringTest() {
    String email = "From:insuranceCompany@ic.com\n" +
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
      assertEquals(email, bufferFile.toString());


  }

  @Test
  public void hashCodeTest() {
    assertEquals(31* bufferFile.getWholeText().hashCode(), bufferFile.hashCode());

  }


  @Test
  public void equalsTest() {
    ReadTextFile copy = new ReadTextFile();
    ReadTextFile different = new ReadTextFile();

    copy.readFile(filepath + "/emailtemplate.txt");
    different.readFile(filepath + "/lettertemplate.txt");

    Assert.assertTrue(bufferFile.equals(copy));
    Assert.assertFalse(bufferFile.equals(different));

    Assert.assertTrue(bufferFile.equals(bufferFile));
    Assert.assertFalse(bufferFile.equals(null));
    Assert.assertFalse(bufferFile.equals(filepath));

  }
}