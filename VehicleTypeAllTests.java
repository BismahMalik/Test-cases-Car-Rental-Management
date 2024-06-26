import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ VehicleTypeBoundaryCoveargeTestTest.class, VehicleTypeDecisionCoveargeTest.class,
		VehicleTypeStatementCoverageTest.class })
public class VehicleTypeAllTests {

}
