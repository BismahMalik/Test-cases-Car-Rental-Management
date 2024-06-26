import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DateTimeStatementCoverageTest {

    @Test
    public void testDefaultConstructor() {
        DateTime dateTime = new DateTime();
        assertTrue(dateTime.getTime() <= System.currentTimeMillis());
    }

    @Test
    public void testParameterizedConstructorDaysForward() {
        DateTime dateTime = new DateTime(3);
        long currentTime = System.currentTimeMillis();
        long expectedTime = currentTime + (3 * 24 * 60 * 60 * 1000);
        assertEquals(expectedTime, dateTime.getTime());
    }

    @Test
    public void testParameterizedConstructorDaysBackward() {
        DateTime dateTime = new DateTime(-3);
        long currentTime = System.currentTimeMillis();
        long expectedTime = currentTime - (3 * 24 * 60 * 60 * 1000);
        assertEquals(expectedTime, dateTime.getTime());
    }

    @Test
    public void testDateTimeFromDate() {
        DateTime startDate = new DateTime(10, 3, 2023);
        DateTime dateTime = new DateTime(startDate, 5);
        long expectedTime = startDate.getTime() + (5 * 24 * 60 * 60 * 1000);
        assertEquals(expectedTime, dateTime.getTime());
    }

    @Test
    public void testGetNameOfDay() {
        DateTime dateTime = new DateTime(2, 3, 2024);
        assertEquals("Monday", dateTime.getNameOfDay());
    }

    @Test
    public void testGetFormattedDate() {
        DateTime dateTime = new DateTime(2, 3, 2024);
        assertEquals("02/03/2024", dateTime.getFormattedDate());
    }

    @Test
    public void testGetEightDigitDate() {
        DateTime dateTime = new DateTime(2, 3, 2024);
        assertEquals("02032024", dateTime.getEightDigitDate());
    }

    @Test
    public void testDiffDays() {
        DateTime startDate = new DateTime(2, 3, 2024);
        DateTime endDate = new DateTime(10, 3, 2024);
        assertEquals(8, DateTime.diffDays(endDate, startDate));
    }

    @Test
    public void testSetAdvance() {
        DateTime dateTime = new DateTime(2, 3, 2024);
        dateTime.setAdvance(1, 3, 30);
        long expectedTime = dateTime.getTime() + ((1 * 24 + 3) * 60 * 60 * 1000 + 30 * 60 * 1000);
        assertEquals(expectedTime, dateTime.getTime());
    }
}
