import org.junit.Test;
import static org.junit.Assert.*;

public class RentalRecordStatementCoverageTest {

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

}
