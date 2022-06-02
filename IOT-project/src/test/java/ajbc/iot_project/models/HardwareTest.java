package ajbc.iot_project.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.enums.HardwareType;

class HardwareTest {

	private final HardwareType TYPE = HardwareType.ACTUATOR;
	private final String MODEL = "ACE";
	private final String MANUFACTURER = "solar100";
	
	Hardware hardware;
	
	public HardwareTest() {
		hardware = new Device(TYPE,MODEL,MANUFACTURER);
	}
	
	@Test
	@DisplayName("Constractor test")
	void testCostractor() {
		//TODO
	}

}
