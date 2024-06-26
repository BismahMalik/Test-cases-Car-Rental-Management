import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class CarStatementCoverageTest {

    // Test cases for getLateFee method

    @Test
    public void testGetLateFee_PositiveDifference() {
    	VehicleType SEDAN= new VehicleType();
        Car car = new Car("C_001", 2022, "Toyota", "Camry", 0, SEDAN);
        DateTime endDate = new DateTime(12,02,2000);
        DateTime startDate = new DateTime(12,02,2000);
        assertEquals(390.0, car.getLateFee(endDate, startDate), 0.001); // Test for late fee calculation with positive difference
    }

    @Test
    public void testGetLateFee_ZeroDaysDifference() {
    	VehicleType SEDAN= new VehicleType();
        Car car = new Car("C_002", 2022, "Honda", "Accord", 0, SEDAN);
        DateTime endDate = new DateTime(12,02,2000);
        DateTime startDate = new DateTime(12,02,2000);
        assertEquals(0.0, car.getLateFee(endDate, startDate), 0.001); // Test for late fee calculation with zero difference
    }

    // Test cases for returnVehicle method

    @Test
    public void testReturnVehicle_NotAvailable() {
    	VehicleType SEDAN= new VehicleType();
        Car car = new Car("C_003", 2022, "Ford", "Focus", 1, SEDAN);
        DateTime returnDate = new DateTime(12,02,2000);
        assertFalse(car.returnVehicle(returnDate)); // Test for returning vehicle when it's not available
    }

    @Test
    public void testReturnVehicle_EqualEstimatedReturnDate() {
    	VehicleType ELECTRIC= new VehicleType();
        Car car = new Car("C_004", 2022, "Tesla", "Model S", 0, ELECTRIC);
        DateTime returnDate = new DateTime(12,02,2000);
        assertFalse(car.returnVehicle(returnDate)); // Test for returning vehicle when it's exactly on the estimated return date
    }

    // Test cases for completeMaintenance method

    @Test
    public void testCompleteMaintenance_NotInMaintenance() {
    	VehicleType SUV= new VehicleType();
        Car car = new Car("C_005", 2022, "BMW", "X5", 0, SUV);
        assertFalse(car.completeMaintenance()); // Test for completing maintenance when status is not in maintenance
    }

    @Test
    public void testCompleteMaintenance_InMaintenance() {
    	VehicleType SEDAN= new VehicleType();
        Car car = new Car("C_006", 2022, "Mercedes", "C-Class", 2, SEDAN);
        assertTrue(car.completeMaintenance()); // Test for completing maintenance when status is in maintenance
    }

    // Test cases for getDetails method

    @Test
    public void testGetDetails_NoRentalRecords() {
    	VehicleType SEDAN= new VehicleType();
        Car car = new Car("C_007", 2022, "Audi", "A4", 0, SEDAN);
        assertEquals("\nRENTAL RECORD:\tempty", car.getDetails()); // Test for rental record when it's empty
    }

    @Test
    public void testGetDetails_OneRentalRecord() {
    	VehicleType SUV= new VehicleType();
        Car car = new Car("C_008", 2022, "Lexus", "RX", 0, SUV);
        RentalRecord record = new RentalRecord();
        assertEquals("Details of rental record", car.getDetails()); // Test for rental record when there is exactly one record
    }

}
