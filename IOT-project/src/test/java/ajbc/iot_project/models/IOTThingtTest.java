package ajbc.iot_project.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.enums.HardwareType;

class IOTThingtTest {
	
	private final HardwareType TYPE = HardwareType.ACTUATOR;
	private final String MODEL = "ACE";
	private final String MANUFACTURER = "solar100";
	private final List<Device> DEVICES =  creatDevicesList();
	
	private IOTThing thing;
	
	public IOTThingtTest() {
		thing = new IOTThing(TYPE,MODEL,MANUFACTURER,DEVICES);
	}
	

	@Test
	@DisplayName("Constructor test")
	void testCostructor() {
		assertAll(
				() -> assertEquals(MODEL, thing.model ),
				() -> assertEquals(MANUFACTURER, thing.manufacturer),
				() -> assertEquals(TYPE, thing.hardwareType),
				() -> assertEquals(DEVICES, thing.devices));	
	}
	
	@Test
	@DisplayName("getDevices() test")
	void testGetDevices(){
		assertEquals(DEVICES, thing.getDevices());
	}
	
	@Test
	@DisplayName("Ensures devices list changed after simulateInventoryChange()")
	void testSimulateInventoryChange() {
		List<Device> originalList = creatDevicesList();
		thing.simulateInventoryChange();
		assertNotEquals(originalList,thing.devices);
	}
	
	private List<Device> creatDevicesList(){
		List<Device> devices = new ArrayList<Device>();
		devices.add(new Device());
		devices.add(new Device());
		devices.add(new Device());
		return devices;
	}

}
