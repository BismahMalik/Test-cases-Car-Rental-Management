import org.junit.Test;
import static org.junit.Assert.*;

public class VehicleTypeStatementCoverageTest {

    @Test
    public void testConstructorCar() {
        VehicleType car = new VehicleType(5);
        assertEquals(5, car.getSeats("car"));
    }

    @Test
    public void testConstructorVan() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        VehicleType van = new VehicleType(15, lastMaintenance);
        assertEquals(15, van.getSeats("van"));
        assertEquals(lastMaintenance, van.getLastMaintenance());
    }

    @Test
    public void testGetSeatsCar() {
        VehicleType car = new VehicleType(5);
        assertEquals(5, car.getSeats("car"));
    }

    @Test
    public void testGetSeatsVan() {
        VehicleType van = new VehicleType(15);
        assertEquals(15, van.getSeats("van"));
    }

    @Test
    public void testGetCarSeats() {
        VehicleType car = new VehicleType(5);
        assertEquals(5, car.getCarSeats());
    }

    @Test
    public void testSetCarSeats() {
        VehicleType car = new VehicleType(5);
        car.setCarSeats(4);
        assertEquals(4, car.getCarSeats());
    }

    @Test
    public void testLastMaintenance() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        VehicleType van = new VehicleType(15);
        van.setLastMaintenance(lastMaintenance);
        assertEquals(lastMaintenance, van.getLastMaintenance());
    }

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
    public void testIsUnderMaintenance() {
        DateTime lastMaintenance = new DateTime(2023, 1, 1);
        DateTime rentDate = new DateTime(2024, 3, 1);
        VehicleType van = new VehicleType(15, lastMaintenance);
        assertFalse(van.IsUnderMaintenance(rentDate, "van", 10));
    }

    
}
