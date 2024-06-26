import org.junit.Test;
import static org.junit.Assert.*;

public class VanStatementCoverageTest {
	
	 @Test
	    public void testVanConstructor1() {
	        // Test the first constructor of Van class
	        Van van = new Van("V_001", 2020, "Mercedes", "Sprinter", 0, new VehicleType(15));
	        assertNotNull(van);
	        assertEquals("V_001", van.getVehicleId());
	        assertEquals(2020, van.Year);
	        assertEquals("Mercedes", van.Make);
	        assertEquals("Sprinter", van.Model);
	        assertEquals(0, van.vehicleStatus);
	        assertNotNull(van.vehicleType);
	    }
	@Test
    public void testVanConstructor2() {
        // Test the second constructor of Van class
        DateTime lastMaintenance = new DateTime(2024, 2, 1);
        Van van = new Van("V_002", 2020, "Mercedes", "Sprinter", 0, new VehicleType(15, lastMaintenance));
        assertNotNull(van);
        assertEquals("V_002", van.getVehicleId());
        assertEquals(2020, van.Year);
        assertEquals("Mercedes", van.Make);
        assertEquals("Sprinter", van.Model);
        assertEquals(0, van.vehicleStatus);
        assertNotNull(van.vehicleType);
        assertEquals(lastMaintenance, van.vehicleType.getLastMaintenance());
    }

    @Test
    public void testGetLateFeeStatementCoverage() {
        // Test cases for statement coverage in getLateFee method
        DateTime startDate = new DateTime(2024, 3, 1);
        DateTime endDate1 = new DateTime(2024, 3, 10);
        DateTime endDate2 = new DateTime(2024, 3, 1);
        Van van = new Van("V_001", 2020, "Mercedes", "Sprinter", 0, new VehicleType(15));
        assertEquals(2990.0, van.getLateFee(endDate1, startDate), 0.01); // Positive late fee
        assertEquals(0.0, van.getLateFee(endDate2, startDate), 0.01); // Zero late fee
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
