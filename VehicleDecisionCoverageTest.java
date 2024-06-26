import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class VehicleDecisionCoverageTest {

    @Test
    public void testRentDecisionCoverage() {
        // Test renting with different rental durations and vehicle statuses
        VehicleType carType = new VehicleType(5);
        VehicleType vanType = new VehicleType(15);
        
        // Test renting a car with different rental durations
        Vehicle car = new Vehicle("C_001", 2020, "Toyota", "Corolla", 0, carType);
        DateTime rentDate = new DateTime(2024, 3, 7);
        assertTrue(car.rent("Customer001", "John Doe", "1234567890", "New York", rentDate, 3)); // Renting for 3 days should succeed
        assertFalse(car.rent("Customer002", "Jane Doe", "0987654321", "Los Angeles", rentDate, 0)); // Renting for 0 days should fail
        
        // Test renting a van with different vehicle statuses
        Vehicle van = new Vehicle("V_001", 2021, "Mercedes", "Sprinter", 2, vanType);
        assertFalse(van.rent("Customer003", "Alice Smith", "4567890123", "Chicago", rentDate, 5)); // Renting a vehicle under maintenance should fail
    }

    @Test
    public void testMaintenanceDecisionCoverage() {
        // Test maintenance decision points
        VehicleType vanType = new VehicleType(15);
        
        // Test performing maintenance on different vehicle statuses
        Vehicle availableVan = new Vehicle("V_002", 2021, "Mercedes", "Sprinter", 0, vanType);
        assertTrue(availableVan.performMaintenance()); // Performing maintenance on an available vehicle should succeed
        
        Vehicle rentedVan = new Vehicle("V_003", 2021, "Ford", "Transit", 1, vanType);
        assertFalse(rentedVan.performMaintenance()); // Performing maintenance on a rented vehicle should fail
    }
    
    @Test
    public void testToString() {
        // Test when Vehicle_id contains "V_"
    	VehicleType SEDAN=new VehicleType();
        Vehicle vehicle1 = new Vehicle("V_001", 2022, "Toyota", "Camry", 0,SEDAN, "Red", "John Doe");
        assertEquals("V_001:2022:Toyota:Camry:Red:John Doe:Available", vehicle1.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 0
        VehicleType VAN=new VehicleType();
        Vehicle vehicle2 = new Vehicle("C_002", 2022, "Toyota", "Sienna", 0, VAN, "Blue", "Jane Smith");
        assertEquals("C_002:2022:Toyota:Sienna:Blue:Jane Smith:Available", vehicle2.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 1
        Vehicle vehicle3 = new Vehicle("C_003", 2022, "Toyota", "Camry", 1, SEDAN, "Black", "Alice Johnson");
        assertEquals("C_003:2022:Toyota:Camry:Black:Alice Johnson:Rented", vehicle3.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 2
        Vehicle vehicle4 = new Vehicle("C_004", 2022, "Toyota", "Camry", 2, SEDAN, "White", "Bob Brown");
        assertEquals("C_004:2022:Toyota:Camry:White:Bob Brown:Maintenance", vehicle4.toString());
    }

    @Test
    public void testGetDetails() {
    	VehicleType SEDAN=new VehicleType();
        // Test when Vehicle_id contains "C_"
        Vehicle vehicle1 = new Vehicle("C_001", 2022, "Toyota", "Camry", 0, SEDAN, "Red", "John Doe");
        assertEquals("Vehicle ID:\tC_001\n Year:\t 2022\n Make:\tToyota\n Model:\tCamry\n Color:\tRed\n Owner Name:\tJohn Doe\n Status:\tAvailable", vehicle1.getDetails());
        
        // Test when Vehicle_id contains "V_"
        VehicleType VAN=new VehicleType();
        Vehicle vehicle2 = new Vehicle("V_002", 2022, "Toyota", "Sienna", 0, VAN, "Blue", "Jane Smith");
        assertEquals("Vehicle ID:\tV_002\n Year:\t 2022\n Make:\tToyota\n Model:\tSienna\n Color:\tBlue\n Owner Name:\tJane Smith\n Status:\tAvailable", vehicle2.getDetails());
    }
}
