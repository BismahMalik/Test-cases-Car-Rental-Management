import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class StartTest {

    // Test cases for main method

    @Test
    public void testMain() {
        // Redirect System.in and System.out for testing
        String input = "7\n"; // Simulating user input "7" to exit the program
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the method to be tested
        String[] args = {};
        Start.main(args);

        // Assertions to check output
        assertTrue(outContent.toString().contains("ThriftyRent SYSTEM MENU"));
        assertTrue(outContent.toString().contains("Exit Program:           7"));
    }


}
