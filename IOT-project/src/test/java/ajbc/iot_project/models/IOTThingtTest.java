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
	

	@Test@DisplayName("Constractor test")
	void testCostractor() {
		assertEquals(MODEL, thing.getModel() );
		assertEquals(MANUFACTURER, thing.getManufacturer());
		assertEquals(TYPE, thing.getHardwareType());
		assertEquals(DEVICES, thing.getDevices());	
		
	}
	
//	@Test
//	@DisplayName("SimulatInventoryChange test ")
//	void testSimulateInventoryChange() {
//
//	}
	
	private List<Device> creatDevicesList(){
		List<Device> devices = new ArrayList<Device>();
		devices.add(new Device());
		devices.add(new Device());
		devices.add(new Device());
		return devices;
	}
	

}
