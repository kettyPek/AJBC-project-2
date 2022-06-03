package ajbc.iot_project.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.enums.HardwareType;

class HardwareTest {

	private final HardwareType TYPE = HardwareType.ACTUATOR;
	private final String MODEL = "ACE";
	private final String MANUFACTURER = "solar100";
	
	private Hardware hardware;
	
	public HardwareTest() {
		hardware = new Device(TYPE,MODEL,MANUFACTURER);
	}
	
	@Test
	@DisplayName("Constractor test")
	void testCostractor() {
		assertAll(
				() -> assertEquals(TYPE, hardware.hardwareType),
				() -> assertEquals(MODEL, hardware.model),
				() -> assertEquals(MANUFACTURER, hardware.manufacturer));
	}
	
	@Test
	@DisplayName("getHardwareType() test")
	void testGetHardwareType() {
		assertEquals(hardware.hardwareType, hardware.getHardwareType());
	}
	
	@Test
	@DisplayName("getModel() test")
	void testGetModel() {
		assertEquals(hardware.model, hardware.getModel());
	}
	
	@Test
	@DisplayName("getManufacturer() test")
	void testGetManufacturer() {
		assertEquals(hardware.manufacturer, hardware.getManufacturer());
	}
	
	@Test
	@DisplayName("getUuid() test")
	void testGetUuid() {
		assertEquals(hardware.ID, hardware.getUuid());
	}
	

}
