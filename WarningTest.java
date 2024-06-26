import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class WarningTest {

    // Test case for adding warned vehicle
    @Test
    public void testAddWarnedVehicle() {
        Warning.addWarnedVehicle("Vehicle_001");
        ArrayList<String> warnedVehicles = Warning.getWarnedVehicles();
        assertTrue(warnedVehicles.contains("Vehicle_001"));
    }

    // Test case for retrieving warned vehicles
    @Test
    public void testGetWarnedVehicles() {
        Warning.addWarnedVehicle("Vehicle_002");
        Warning.addWarnedVehicle("Vehicle_003");
        ArrayList<String> warnedVehicles = Warning.getWarnedVehicles();
        assertTrue(warnedVehicles.contains("Vehicle_002"));
        assertTrue(warnedVehicles.contains("Vehicle_003"));
    }
}
