package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AutomationTest {
  Automation automation = new Automation();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void main() {
    String[] args2 = {"--email", "--email-template", "emailtemplate.txt", "--output-dir"};
    try {
      automation.main(args2);
      fail("exception should throw.");
    } catch (IllegalArgumentException e) {
    }

    String[] args3 = {"--email", "--letter-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    try {
      automation.main(args3);
      fail("exception should throw.");
    } catch (IllegalArgumentException e) {

    }


    try {
      String[] args4 = {"--letter", "letter-template.txt", "--output-dir",
          "emails", "--csv-file", "customer.csv"};
      automation.main(args4);
      fail("exception should throw.");
    } catch (IllegalArgumentException e) {

    }

    try {
      Automation automationTest = new Automation();
      String[] args5 = {"--email-template", "emailtemplate.txt",
          "--output-dir", "testDir2", "--email", "--csv-file", "insurance_company_members.csv"};
      automationTest.main(args5);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args5 = {"--letter-template", "lettertemplate.txt",
          "--output-dir", "letterDir", "--letter", "--csv-file", "insurance_company_members.csv"};
      automationTest.main(args5);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args6 = {"--letter-template", "lettertemplate.txt",
              "--output-dir", "testDirSmallLetter", "--letter", "--csv-file", "small_insurance_company_members.csv"};
      automationTest.main(args6);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args7 = {"--email-template", "emailtemplate.txt",
              "--output-dir", "testDirSmallEmail", "--email", "--csv-file", "small_insurance_company_members.csv"};
      automationTest.main(args7);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args9 = {"--letter-template", "letter-information.txt",
              "--output-dir", "testDirInformationLetter", "--letter", "--csv-file", "insurance_company_members.csv"};
      automationTest.main(args9);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args8 = {"--email-template", "email-information.txt",
              "--output-dir", "testDirInformationEmail", "--email", "--csv-file", "small_insurance_company_members.csv"};
      automationTest.main(args8);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args9 = {"--letter-template", "letter-information.txt",
              "--output-dir", "testDirInformationLetter", "--letter", "--csv-file", "small_insurance_company_members.csv"};
      automationTest.main(args9);
    } catch (IllegalArgumentException e) {
      fail("exception should not be throw.");
    }

    try {
      Automation automationTest = new Automation();
      String[] args9 = {"--letter-template", "information.txt",
              "--output-dir", "testDirInformationLetter", "--letter", "--csv-file", "small_insurance_company_members.csv"};
      automationTest.main(args9);
      fail("exception should be throw.");
    } catch (IllegalArgumentException e) {
    }


  }



  @Test
  public void toStringTest(){
    assertEquals("Email Automation Application", automation.toString());

  }

  @Test
  public void hashCodeTest(){
    assertEquals(29, automation.hashCode());

  }

  @Test
  public void equalsTest(){
    Automation copy = new Automation();
    Assert.assertTrue(copy.equals(automation));
    Assert.assertTrue(automation.equals(automation));
    Assert.assertFalse(automation.equals(null));

  }
}