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
	private static Map<UUID,IOTThing> iotThings;
	private static Map<UUID,Device> devices;
	
	public static synchronized DBMock getInstance() {
		if(instance==null)
			instance = new DBMock();
		return instance;
	}
	
	private DBMock() {
		iotThings = new HashMap<UUID,IOTThing>();
		devices = new HashMap<UUID,Device>();
	}

	public Map<UUID, IOTThing> getIotThings() {
		return iotThings;
	}

	public Map<UUID, Device> getDevices() {
		return devices;
	}
	
}
