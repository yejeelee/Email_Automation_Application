package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {
  String[] args = {"--email", "--email-template", "email-template.txt", "--output-dir",
                    "emails", "--csv-file", "customer.csv"};
  Parser parser = new Parser();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void getEmail() {
    Assert.assertEquals(false, parser.isEmail());
    parser.parseCommand(args);
    Assert.assertEquals(true, parser.isEmail());
  }

  @Test
  public void getEmailTemplate() {
    Assert.assertEquals("", parser.getEmailTemplate());
    parser.parseCommand(args);
    Assert.assertEquals("email-template.txt", parser.getEmailTemplate());
  }

  @Test
  public void getLetter() {
    Assert.assertEquals(false, parser.isLetter());
    parser.parseCommand(args);
    Assert.assertEquals(false, parser.isLetter());
  }

  @Test
  public void getLetterTemplate() {
    Assert.assertEquals("", parser.getLetterTemplate());
    parser.parseCommand(args);
    Assert.assertEquals("", parser.getLetterTemplate());
  }

  @Test
  public void getError() {
    Assert.assertEquals("", parser.getError());
    parser.parseCommand(args);
    Assert.assertEquals("", parser.getError());
  }

  @Test
  public void getCsv() {
    Assert.assertEquals("", parser.getCsv());
    parser.parseCommand(args);
    Assert.assertEquals("customer.csv", parser.getCsv());
  }

  @Test
  public void getOutputPath() {
    Assert.assertEquals("", parser.getOutputPath());
    parser.parseCommand(args);
    Assert.assertEquals("emails", parser.getOutputPath());
  }

  @Test
  public void parseCommand() {
    String[] test1 = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};

    parser.parseCommand(test1);
    Assert.assertEquals(true, parser.isLetter());
    Assert.assertEquals("letter-template.txt", parser.getLetterTemplate());



  }

  @Test
  public void generateErrorMessage() {
    String[] test2 = {"--letter", "--email-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    parser.parseCommand(test2);
    Assert.assertEquals("", parser.getLetterTemplate());

    String[] test3 = {"--letter", "--letter-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    parser.parseCommand(test3);
    Assert.assertEquals("", parser.getLetterTemplate());

    parser = new Parser();
    String[] test4 = {"--email", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    parser.parseCommand(test4);
    Assert.assertEquals(false, parser.isLetter());

    parser = new Parser();
    String[] test5 = {"--email", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "customer.csv"};
    parser.parseCommand(test5);
    Assert.assertEquals("", parser.getCsv());

    parser = new Parser();
    String[] test6 = {"--email", "--letter-template", "letter-template.txt", "--output-dir",
        "emails"};
    parser.parseCommand(test6);
    Assert.assertEquals("", parser.getCsv());

    parser = new Parser();
    String[] test7 = {"--email", "--letter-template", "letter-template.txt",
        "emails", "--csv-file", "customer.csv"};
    parser.parseCommand(test7);
    Assert.assertEquals("", parser.getOutputPath());

    parser = new Parser();
    String[] test8 = {"--email", "--letter-template", "letter-template.txt", "--output-dir",
        "--csv-file", "customer.csv"};
    parser.parseCommand(test8);
    Assert.assertEquals("", parser.getOutputPath());
  }


  @Test
  public void equalsTest() {
    Assert.assertFalse(parser.equals(null));
    Assert.assertFalse(parser.equals(args));
    Assert.assertTrue(parser.equals(parser));

    Parser test = new Parser();
    test.parseCommand(args);
    parser.parseCommand(args);
    Assert.assertTrue(parser.equals(test));

    String[] test2 = {"--letter", "--email-template", "email-template.txt", "--output-dir",
                      "emails", "--csv-file", "customer.csv"};
    test.parseCommand(test2);
    Assert.assertFalse(parser.equals(test));

    test = new Parser();
    String[] test3 = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    test.parseCommand(test3);
    Assert.assertFalse(parser.equals(test));

    test = new Parser();
    String[] test4 = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "gmail", "--csv-file", "customer.csv"};
    test.parseCommand(test4);
    Assert.assertFalse(parser.equals(test));

    test = new Parser();
    String[] test5 = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "client.csv"};
    test.parseCommand(test5);
    Assert.assertFalse(parser.equals(test));

    test = new Parser();
    String[] test6 = {"--email", "--letter-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    test.parseCommand(test6);
    Assert.assertFalse(parser.equals(test));

    test = new Parser();
    String[] test7 = {"--email", "--email-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    test.parseCommand(test7);
    Assert.assertFalse(parser.equals(test));



  }

  @Test
  public void hashCodeTest() {
    Assert.assertEquals(1127757375, parser.hashCode());
    parser.parseCommand(args);
    Assert.assertEquals(1586913511, parser.hashCode());
  }

  @Test
  public void toStringTest() {
    parser.parseCommand(args);
    Assert.assertEquals("Email template: email-template.txt,"
        + " csv file: customer.csv, output directory: emails. ", parser.toString());

    String[] test3 = {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    Parser test = new Parser();
    test.parseCommand(test3);
    Assert.assertEquals("Letter template: letter-template.txt,"
        + " csv file: customer.csv, output directory: emails. ", test.toString());

  }
}