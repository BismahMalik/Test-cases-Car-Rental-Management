import org.junit.Test;
import static org.junit.Assert.*;

//Covered in VehicleTypeDecisionCoveargeTest

public class VehicleTypeBoundaryCoveargeTestTest {

    @Test
    public void testIndexOfValidDay() {
        VehicleType vehicle = new VehicleType(5);
        assertEquals(0, vehicle.indexOf("Sunday"));
    }

    @Test
    public void testIndexOfInvalidDay() {
        VehicleType vehicle = new VehicleType(5);
        assertEquals(-1, vehicle.indexOf("InvalidDay"));
    }
}
