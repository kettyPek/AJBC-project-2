package ajbc.iot_project.DB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

/**
 * This class represents a DB of IOT things and devices.
 * IOT things and Devices stored in a map with UUID as the key;
 * @author ketty
 *
 */
public class DBMock {
	
	private static DBMock instance = null;
	private static Map<String,IOTThing> iotThings;
	private static Map<String,Device> devices;
	
	public static synchronized DBMock getInstance() {
		if(instance==null)
			instance = new DBMock();
		return instance;
	}
	
	private DBMock() {
		iotThings = new HashMap<String,IOTThing>();
		devices = new HashMap<String,Device>();
	}

	public Map<String, IOTThing> getIotThings() {
		return iotThings;
	}

	public Map<String, Device> getDevices() {
		return devices;
	}
	
}
