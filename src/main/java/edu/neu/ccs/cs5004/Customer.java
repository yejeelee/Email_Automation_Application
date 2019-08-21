package edu.neu.ccs.cs5004;


import java.util.Map;

/**
 * This class represents Customer that contains information of a customer.
 * key will be the attribute such as first_name,
 * last_name and values are values are Jenny and Lee.
 */
public class Customer {

  private Map<String, String> customerInfo;

  /**
   * Construct a new Customer.
   * @param customer a map of the header values (keys) and customer data (values),
   */
  public Customer(Map<String, String> customer) {
    this.customerInfo = customer;
  }

  /**
   * Gets the Customer information from this Customer.
   *
   * @return a map of this Customer.
   */
  public Map<String, String> getCustomers() {
    return this.customerInfo;
  }


  /**
   * Find value of a given attribute key of this Customer.
   *
   * @param key key given to this Customer.
   * @return value that corresponds to the key of this Customer.
   */
  public String findValueOfAttributes(String key) {
    return this.customerInfo.get(key);
  }


  /**
   * Equals function for the Customer.
   * @param object the object to be compared too.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object == null || getClass() != object.getClass()) {
      return false;
    }
    Customer compare = (Customer) object;

    if (!this.customerInfo.equals(compare.customerInfo)) {
      return false;
    }
    return true;
  }

  /**
   * The hashCode function for the Customer.
   * @return int representing the hashCode.
   */
  @Override
  public int hashCode() {
    return this.customerInfo.hashCode();
  }

  /**
   * The toString function for the Customer.
   * @return the String representation of a Customer.
   */
  @Override
  public String toString() {
    return (this.customerInfo.toString());
  }


}
