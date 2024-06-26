import static org.junit.Assert.*;
import org.junit.Test;

public class CustomerTest {

    // Test case for constructor and accessor methods
    @Test
    public void testConstructorAndAccessors() {
        Customer customer = new Customer("C_001", "John Doe", "1234567890", "New York");
        assertEquals("C_001", customer.getCustomerId());
        assertEquals("John Doe", customer.getName());
        assertEquals("1234567890", customer.getContactNumber());
        assertEquals("New York", customer.getLocation());
    }

    // Test case for toString method
    @Test
    public void testToString() {
        Customer customer = new Customer("C_001", "John Doe", "1234567890", "New York");
        String expectedString = "Customer ID: C_001\nName: John Doe\nContact Number: 1234567890\nLocation: New York";
        assertEquals(expectedString, customer.toString());
    }
}
