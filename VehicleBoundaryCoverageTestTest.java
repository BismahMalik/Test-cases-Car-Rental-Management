import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleBoundaryCoverageTestTest {

    @Test
    public void testGetLastElementIndexEmptyRecords() {
        // Test getting the last element index when records are empty
        VehicleType carType = new VehicleType(5);
        Vehicle car = new Vehicle("C_001", 2020, "Toyota", "Corolla", 0, carType);
        assertEquals(-1, car.getLastElementIndex());
    }

    @Test
    public void testGetLastElementIndexNonEmptyRecords() {
        // Test getting the last element index when records are non-empty
        VehicleType carType = new VehicleType(5);
        Vehicle car = new Vehicle("C_001", 2020, "Toyota", "Corolla", 0, carType);
        DateTime rentDate1 = new DateTime(2024, 3, 7);
        DateTime rentDate2 = new DateTime(2024, 3, 8);
        RentalRecord record1 = new RentalRecord("R_001", rentDate1, rentDate2);
        car.records[0] = record1;
        assertEquals(0, car.getLastElementIndex());
    }
    @Test
    public void testGetDetailsCarAvailable() {
    	VehicleType VAN= new VehicleType();
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 5, VAN);
        assertEquals("Vehicle ID:\tC_123\n Year:\t 2022\n Make:\tToyota\n Model:\tCamry\n Number of Seats:\t5\n Status:\tAvailable", vehicle.getDetails());
    }

    @Test
    public void testGetDetailsVanRented() {
    	VehicleType VAN= new VehicleType();
        Vehicle vehicle = new Vehicle("V_456", 2020, "Ford", "Transit", 8, VAN);
        assertEquals("Vehicle ID:\tV_456\n Year:\t 2020\n Make:\tFord\n Model:\tTransit\n Number of Seats:\t8\n Status:\tRented", vehicle.getDetails());
    }

    @Test
    public void testGetDetailsVanMaintenance() {
    	VehicleType VAN= new VehicleType();
        Vehicle vehicle = new Vehicle("V_789", 2019, "Chevrolet", "Express", 12, VAN);
        assertEquals("Vehicle ID:\tV_789\n Year:\t 2019\n Make:\tChevrolet\n Model:\tExpress\n Number of Seats:\t12\n Status:\tMaintenance", vehicle.getDetails());
    }
}
