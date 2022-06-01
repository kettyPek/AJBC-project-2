package ajbc.iot_project.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import ajbc.iot_project.DB.DBMock;
import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

/**
 * This class performs operation on the DB
 * @author ketty
 *
 */
public class DBService {
	
	private DBMock db;
	private volatile Map<UUID,IOTThing> iotThings;
	private volatile Map<UUID,Device> devices;
	
	public DBService() {
		db = DBMock.getInstance();
		iotThings = db.getIotThings();
		devices = db.getDevices();
	}
	
	public List<IOTThing> getAllIOTThings(){
		return iotThings.values().stream().collect(Collectors.toList());
	}

	public void updateDB(IOTThing thing) {

		if(iotThings.containsKey(thing.getUuid())) {

		List<Device> oldDevices = iotThings.get(thing.getUuid()).getDevices();
		List<Device> updatedDevices = thing.getDevices();
		
		oldDevices.forEach(device -> {
			if(!updatedDevices.contains(device)) 
				devices.remove(device.getUuid());});
		
		updatedDevices.forEach(device-> {
			if(!oldDevices.contains(device)) 
				devices.put(device.getUuid(), device);});
		}
	}

	public boolean containsIOTThing(IOTThing thing) {
		return iotThings.containsKey(thing.getUuid());
	}

	public void addToDB(IOTThing thing) {
		if(!containsIOTThing(thing)) {
			iotThings.put(thing.getUuid(), thing);
			thing.getDevices().forEach(device -> devices.put(device.getUuid(), device));
		}
	}

	public IOTThing getIOTThingByID(UUID id) {
		return iotThings.get(id);
	}

	public IOTThing getIOTThingByProperties(String type, String model, String manufacturer) {
		IOTThing thing = null;
		try{
			HardwareType hardwareType = HardwareType.valueOf(type.toUpperCase());
			List<IOTThing> thingsList = iotThings.values().stream().collect(Collectors.toList());
			for(IOTThing iotThing : thingsList) 
				if(iotThing.getHardwareType()==hardwareType && iotThing.getModel().equalsIgnoreCase(model) && iotThing.getManufacturer().equalsIgnoreCase(manufacturer)) {
					thing = iotThing;
					break;
				}
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}

		return thing;
	}

	public List<Device> getAllDevices() {
		return devices.values().stream().collect(Collectors.toList());
	}

	public Device getDeviceByID(UUID id) {
		return devices.get(id);
	}

	public List<Device> getDevicesByProperties(String type, String model, String manufacturer, UUID thingID) {
		List<Device> devices = new ArrayList<Device>();
		try{
			devices.addAll(iotThings.get(thingID).getDevices());
			HardwareType hardwareType = HardwareType.valueOf(type.toUpperCase());
			return devices.stream().filter(device -> device.getHardwareType()==hardwareType && device.getModel().equalsIgnoreCase(model) &&
				device.getManufacturer().equalsIgnoreCase(manufacturer)).collect(Collectors.toList());
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
