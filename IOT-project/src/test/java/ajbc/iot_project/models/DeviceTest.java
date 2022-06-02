package ajbc.iot_project.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.enums.HardwareType;

class DeviceTest {
	
	private static final HardwareType TYPE = HardwareType.SENSOR;
	private static final String MODEL = "Sensor";
	private static final String MANUFACTURER = "sensor100";
	
	private Device device;
	
	public DeviceTest() {
		device = new Device(TYPE,MODEL,MANUFACTURER);
	}

	@Test
	@DisplayName("Constractor test")
	void testConstructor() {
		assertAll(
				() -> assertEquals(TYPE, device.getHardwareType()),
				() -> assertEquals(MODEL, device.getModel()),
				() -> assertEquals(MANUFACTURER, device.getManufacturer()));
	}
	
	

}
