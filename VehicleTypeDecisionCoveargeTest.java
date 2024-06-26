import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleTypeDecisionCoveargeTest {

    @Test
    public void testCanBeRentedForMinimumDaysCarWeekday() {
        VehicleType car = new VehicleType(5);
        DateTime rentDate = new DateTime(2024, 3, 7); // Wednesday
        assertEquals(2, car.canBeRentedForMinimumDays(rentDate, "car"));
    }

    @Test
    public void testCanBeRentedForMinimumDaysCarWeekend() {
        VehicleType car = new VehicleType(5);
        DateTime rentDate = new DateTime(2024, 3, 11); // Sunday
        assertEquals(3, car.canBeRentedForMinimumDays(rentDate, "car"));
    }

    @Test
    public void testCanBeRentedForMinimumDaysVan() {
        VehicleType van = new VehicleType(15);
        DateTime rentDate = new DateTime(2024, 3, 1); // Monday
        assertEquals(1, van.canBeRentedForMinimumDays(rentDate, "van"));
    }

    @Test
    public void testIsUnderMaintenanceNotVan() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        DateTime rentDate = new DateTime(2024, 3, 1);
        VehicleType car = new VehicleType(5);
        assertFalse(car.IsUnderMaintenance(rentDate, "car", 10));
    }

    @Test
    public void testIsUnderMaintenanceVanWithin12Days() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        DateTime rentDate = new DateTime(2024, 3, 1);
        VehicleType van = new VehicleType(15, lastMaintenance);
        assertFalse(van.IsUnderMaintenance(rentDate, "van", 10));
    }

    @Test
    public void testIsUnderMaintenanceVanExceeds12Days() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        DateTime rentDate = new DateTime(2024, 3, 1);
        VehicleType van = new VehicleType(15, lastMaintenance);
        assertTrue(van.IsUnderMaintenance(rentDate, "van", 13));
    }

    
}
