package edu.neu.ccs.cs5004;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
private Customer testCustomer;
private Customer testCustomer2;
private Customer testCustomer3;
private Customer testCustomer4;

private Map<String, String> testMap;
private Map<String, String> testMap2;
private Map<String, String > testMap3;
private Map<String, String > testMap4;

@Before
public void setUp() throws Exception {
    testMap = new HashMap<>();
    testMap2 = new HashMap<>();
    testMap3 = new HashMap<>();
    testMap4 = new HashMap<>();

    testMap.put("first_name", "Amy");
    testMap.put("last_name", "Eunson");

    testMap2.put("first_name", "Amy");
    testMap2.put("first_name", "Eunson");

    testMap3.put("first_name", "Jane");
    testMap3.put("last_name", "Eunson");

    testMap4.put("first_name", "Amy");
    testMap4.put("last_name", "Eunson");

    testCustomer = new Customer(testMap);
    testCustomer2 = new Customer(testMap2);
    testCustomer3 = new Customer(testMap3);
    testCustomer4 = new Customer(testMap4);
    }


  @Test
  public void getCustomers() {
  assertEquals(testMap, testCustomer.getCustomers());
  }

  @Test
  public void getCustomersInvalid() {
    assertNotEquals(testMap3, testCustomer.getCustomers());
  }

  @Test
  public void findValueOfAttributes() {
    Assert.assertEquals(testCustomer.findValueOfAttributes("first_name"), "Amy");
  }

  @Test
  public void findValueOfAttributesInvalid() {
    Assert.assertNotEquals(testCustomer.findValueOfAttributes("first_name"), "Jane");
  }

  @Test
  public void equalsTest() {
    Assert.assertTrue(testCustomer.equals(testCustomer));
    Assert.assertTrue(testCustomer.equals(testCustomer4));
  }

  @Test
  public void equalsInvalid() {
    Assert.assertFalse(testCustomer.equals(null));
    Assert.assertFalse(testCustomer.equals(testMap3));
    assertFalse(testMap.equals(testMap2));
    assertFalse(testMap2.equals(testMap3));
    assertFalse(testMap3.equals(testMap));
    assertFalse(testMap.equals(testCustomer));
  }

  @Test
  public void TestHashCode() {
    assertEquals(testCustomer.hashCode(), testCustomer4.hashCode());
    assertEquals(testCustomer2.hashCode(), testCustomer2.hashCode());
  }

  @Test
  public void TestHashCodeInvalid() {
//    assertEquals(testCustomer.hashCode(), testCustomer2.hashCode());
//    assertNotEquals(testCustomer2.hashCode(), testCustomer3.hashCode());
//    assertNotEquals(testCustomer.hashCode(), testCustomer3.hashCode());
  }

  @Test
  public void toStringTest() {
    Assert.assertEquals("{last_name=Eunson, first_name=Amy}", testCustomer.toString());
  }
}