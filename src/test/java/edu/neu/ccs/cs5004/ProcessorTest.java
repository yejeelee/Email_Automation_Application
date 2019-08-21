package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessorTest {
  Processor processor = new Processor();

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void process() {
    List<String> template = new ArrayList<>();
    List<Customer> customers = new ArrayList<>();
    WriteFile writeFile = new WriteFile(template, customers);
    try {
      writeFile.writeToAllCustomers("woohoolocal/");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    Parser parseCommand = new Parser();
    String[] args5 = {"--email-template", "emailtemplate.txt",
        "--output-dir", "testDir2", "--email", "--csv-file", "insurance_company_members.csv"};
    parseCommand.parseCommand(args5);
    processor.process(parseCommand);

  }

  @Test
  public void hashCodeTest() {
    Assert.assertEquals(42, processor.hashCode());
  }

  @Test
  public void toStringTest() {
    Assert.assertEquals("Processor\n", processor.toString());
  }

  @Test
  public void equalsTest() {
    Assert.assertEquals(true, processor.equals(processor));
    Processor test = new Processor();
    Assert.assertEquals(true, processor.equals(test));
    Assert.assertEquals(false, processor.equals(null));
  }
}