import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleStatementCoverageTest {

    // Statement Coverage Test Cases

    @Test
    public void testRentAvailableCar() {
        VehicleType carType = new VehicleType(5);
        Vehicle car = new Vehicle("C_001", 2020, "Toyota", "Corolla", 0, carType);
        DateTime rentDate = new DateTime(2024, 3, 7);
        assertTrue(car.rent("Customer001","Bismah","03311000","Lahore", rentDate, 3));
    }

    @Test
    public void testRentAvailableVan() {
        VehicleType vanType = new VehicleType(15);
        Vehicle van = new Vehicle("V_001", 2021, "Mercedes", "Sprinter", 0, vanType);
        DateTime rentDate = new DateTime(2024, 3, 7);
        assertTrue(van.rent("Customer002","Bismah","03311000","Lahore", rentDate, 2));
    }

    @Test
    public void testRentUnavailableVehicle() {
        VehicleType carType = new VehicleType(5);
        Vehicle car = new Vehicle("C_002", 2020, "Toyota", "Corolla", 1, carType);
        DateTime rentDate = new DateTime(2024, 3, 7);
        assertFalse(car.rent("Customer003", "Bismah","03311000","Lahore",rentDate, 5));
    }

    @Test
    public void testPerformMaintenanceAvailableVehicle() {
        VehicleType vanType = new VehicleType(15);
        Vehicle van = new Vehicle("V_002", 2021, "Mercedes", "Sprinter", 0, vanType);
        assertTrue(van.performMaintenance());
    }

    @Test
    public void testPerformMaintenanceRentedVehicle() {
        VehicleType vanType = new VehicleType(15);
        Vehicle van = new Vehicle("V_003", 2021, "Mercedes", "Sprinter", 1, vanType);
        assertFalse(van.performMaintenance());
    }

    @Test
    public void testPerformMaintenanceMaintenanceVehicle() {
        VehicleType vanType = new VehicleType(15);
        Vehicle van = new Vehicle("V_004", 2021, "Mercedes", "Sprinter", 2, vanType);
        assertFalse(van.performMaintenance());
    }
    @Test
    public void testToString() {
        // Test when Vehicle_id contains "V_"
    	VehicleType v= new VehicleType(5);
        Vehicle vehicle1 = new Vehicle("V_001", 2022, "Toyota", "Camry", 0, v);
        assertEquals("V_001:2022:Toyota:Camry:15:Available", vehicle1.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 0
        Vehicle vehicle5 = new Vehicle("C_002", 2022, "Toyota", "Sienna", 0, v);
        assertEquals("C_002:2022:Toyota:Sienna:0:Available", vehicle5.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 1
        Vehicle vehicle6 = new Vehicle("C_003", 2022, "Toyota", "Camry", 1, v);
        assertEquals("C_003:2022:Toyota:Camry:1:Rented", vehicle6.toString());
        
        // Test when Vehicle_id contains "C_" and vehicleStatus is 2
        Vehicle vehicle7 = new Vehicle("C_004", 2022, "Toyota", "Camry", 2, v);
        assertEquals("C_004:2022:Toyota:Camry:2:Maintenance", vehicle7.toString());
    }

    @Test
    public void testGetDetails() {
        // Test when Vehicle_id contains "C_"
    	VehicleType v= new VehicleType(5);
        Vehicle vehicle1 = new Vehicle("C_001", 2022, "Toyota", "Camry", 0, v);
        assertEquals("Vehicle ID:\tC_001\n Year:\t 2022\n Make:\tToyota\n Model:\tCamry\n Number of Seats:\t5\n Status:\tAvailable", vehicle1.getDetails());
        
        // Test when Vehicle_id contains "V_"
        Vehicle vehicle2 = new Vehicle("V_002", 2022, "Toyota", "Sienna", 0, v);
        assertEquals("Vehicle ID:\tV_002\n Year:\t 2022\n Make:\tToyota\n Model:\tSienna\n Number of Seats:\t15\n Status:\tAvailable", vehicle2.getDetails());
        
        Vehicle vehicle3 = new Vehicle("V_002", 2022, "Toyota", "Sienna", 1, v);
        assertEquals("Vehicle ID:\tV_002\n Year:\t 2022\n Make:\tToyota\n Model:\tSienna\n Number of Seats:\t15\n Status:\tRented", vehicle3.getDetails());
    
        Vehicle vehicle4 = new Vehicle("V_002", 2022, "Toyota", "Sienna", 2, v);
        assertEquals("Vehicle ID:\tV_002\n Year:\t 2022\n Make:\tToyota\n Model:\tSienna\n Number of Seats:\t15\n Status:\tMaintenance", vehicle4.getDetails());
    }
    
    @Test
    public void getVehicleId() {
        // Test when Vehicle_id contains "C_"
    	VehicleType v= new VehicleType(5);
        Vehicle vehicle1 = new Vehicle("C_001", 2022, "Toyota", "Camry", 0, v);
        assertEquals("C_001", vehicle1.getVehicleId());     
    }
    }

  