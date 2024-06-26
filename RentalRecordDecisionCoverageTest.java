import org.junit.Test;
import static org.junit.Assert.*;

public class RentalRecordDecisionCoverageTest {

    @Test
    public void testToStringNoData() {
        RentalRecord record = new RentalRecord("R1", new DateTime(2024, 3, 1), new DateTime(2024, 3, 5));
        assertEquals("R1:01/03/2024:05/03/2024:none:none:none", record.toString());
    }

    @Test
    public void testToStringWithData() {
        DateTime rentDate = new DateTime(2024, 3, 1);
        DateTime estimatedReturnDate = new DateTime(2024, 3, 5);
        DateTime actualReturnDate = new DateTime(2024, 3, 6);
        RentalRecord record = new RentalRecord("R1", rentDate, estimatedReturnDate);
        record.setData(actualReturnDate, 50.0, 10.0);
        assertEquals("R1:01/03/2024:05/03/2024:06/03/2024:50.00:10.00", record.toString());
    }
    
    @Test
    public void testGetDetailsNoData() {
        // Create an instance of RentalRecord with no data
        RentalRecord record = new RentalRecord("R1", new DateTime(2024, 3, 1), new DateTime(2024, 3, 5));
        assertEquals("Record ID:             R1\nRent Date:             01/03/2024\nEstimated Return Date: 05/03/2024", record.getDetails());
    }

    @Test
    public void testGetDetailsWithData() {
        // Create an instance of RentalRecord with data
        RentalRecord record = new RentalRecord("R1", new DateTime(2024, 3, 1), new DateTime(2024, 3, 5));
        record.setData(new DateTime(2024, 3, 6), 50.0, 10.0);
        assertEquals("Record ID:             R1\nRent Date:             01/03/2024\nEstimated Return Date: 05/03/2024\nActual Return Date:    06/03/2024\nRental Fee:            50.00\nLate Fee:              10.00", record.getDetails());
    }
    }


   

