import org.junit.Test;
import static org.junit.Assert.*;

public class VanDecisionCoverageTest {

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
    public void testRent() {
        // Create a Vehicle object
        VehicleType vehicleType = new VehicleType(5);
        Vehicle vehicle = new Vehicle("C_001", 2020, "Toyota", "Camry", 0, vehicleType);

        // Test rent for a car
        DateTime rentDate = new DateTime(2024, 3, 1); // Rent date
        assertTrue(vehicle.rent("Customer1","Bismah","03311000","Lahore", rentDate, 3)); // Renting for 3 days
        assertFalse(vehicle.rent("Customer2","Bismah","03311000","Lahore", rentDate, 0)); // Renting for 0 days (should fail)
        assertFalse(vehicle.rent("Customer3","Bismah","03311000","Lahore", rentDate, 15)); // Renting for 15 days (should fail)
    }
    @Test
    public void testPerformMaintenance() {
        // Create a Vehicle object
        VehicleType vehicleType = new VehicleType(5);
        Vehicle vehicle = new Vehicle("C_001", 2020, "Toyota", "Camry", 0, vehicleType);

        // Test performMaintenance
        assertTrue(vehicle.performMaintenance()); // First maintenance, should succeed
        assertFalse(vehicle.performMaintenance()); // Already in maintenance, should fail
    }

    @Test
    public void testToString() {
        // Create a Vehicle object
        VehicleType vehicleType = new VehicleType(5);
        Vehicle vehicle = new Vehicle("C_001", 2020, "Toyota", "Camry", 0, vehicleType);

        // Test toString
        String expectedAvailable = "C_001:2020:Toyota:Camry:5:Available";
        assertEquals(expectedAvailable, vehicle.toString());

        // Update vehicle status to rented
        vehicle.vehicleStatus = 1;
        String expectedRented = "C_001:2020:Toyota:Camry:5:Rented";
        assertEquals(expectedRented, vehicle.toString());

        // Update vehicle status to maintenance
        vehicle.vehicleStatus = 2;
        String expectedMaintenance = "C_001:2020:Toyota:Camry:5:Maintenance";
        assertEquals(expectedMaintenance, vehicle.toString());
    }
    @Test
    public void testReturnVehicleWhenVehicleStatusNotZero() {
        // Create a van with vehicleStatus not equal to 0
        VehicleType VAN=new VehicleType();
        Van van = new Van("V_001", 2022, "Toyota", "Sienna", 1, VAN);
        
        // Call returnVehicle with any return date
        assertFalse(van.returnVehicle(new DateTime(2022, 1, 15)));
    }

    @Test
    public void testReturnVehicleWhenVehicleTypeIsVanAndDiffDaysLessThanOne() {
        // Create a van with vehicleStatus equal to 0
    	VehicleType VAN=new VehicleType();
        Van van = new Van("V_002", 2022, "Honda", "Odyssey", 0, VAN);
        
        // Set rent date to 2022-01-10
        DateTime rentDate = new DateTime(2022, 1, 10);
        
        // Call returnVehicle with return date less than rent date
        assertFalse(van.returnVehicle(new DateTime(2022, 1, 9)));
    }

    @Test
    public void testReturnVehicleWhenVehicleStatusZeroAndVehicleTypeIsNotVanOrDiffDaysGreaterThanEqualOne() {
        // Create a van with vehicleStatus equal to 0
    	VehicleType VAN=new VehicleType();
        Van van = new Van("V_003", 2022, "Chrysler", "Pacifica", 0, VAN);
        
        // Set rent date to 2022-01-10
        DateTime rentDate = new DateTime(2022, 1, 10);
        
        // Call returnVehicle with return date greater than or equal to rent date
        assertTrue(van.returnVehicle(new DateTime(2022, 1, 10)));
    }
    @Test
    public void testGetDetailsCarAvailable() {
    	VehicleType VAN=new VehicleType();
        Vehicle vehicle = new Vehicle("C_123", 2022, "Toyota", "Camry", 5, VAN);
        assertEquals("Vehicle ID:\tC_123\n Year:\t 2022\n Make:\tToyota\n Model:\tCamry\n Number of Seats:\t5\n Status:\tAvailable", vehicle.getDetails());
    }

    @Test
    public void testGetDetailsVanRented() {
    	VehicleType VAN=new VehicleType();
        Vehicle vehicle = new Vehicle("V_456", 2020, "Ford", "Transit", 8, VAN);
        assertEquals("Vehicle ID:\tV_456\n Year:\t 2020\n Make:\tFord\n Model:\tTransit\n Number of Seats:\t8\n Status:\tRented", vehicle.getDetails());
    }

    @Test
    public void testGetDetailsVanMaintenance() {
    	VehicleType VAN=new VehicleType();
        Vehicle vehicle = new Vehicle("V_789", 2019, "Chevrolet", "Express", 12, VAN);
        assertEquals("Vehicle ID:\tV_789\n Year:\t 2019\n Make:\tChevrolet\n Model:\tExpress\n Number of Seats:\t12\n Status:\tMaintenance", vehicle.getDetails());
    }
}
