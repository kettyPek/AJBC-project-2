package ajbc.iot_project.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import ajbc.iot_project.DB.DBMock;
import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.exceptions.MissingDataException;
import ajbc.iot_project.exceptions.NotMatchingDataException;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

/**
 * This class performs operation on the DB
 * @author ketty
 *
 */
public class DBService {
	
	private DBMock db;
	protected volatile Map<UUID,IOTThing> iotThingsMap;
	protected volatile Map<UUID,Device> devicesMap;
	
	public DBService() {
		db = DBMock.getInstance();
		iotThingsMap = db.getIotThings();
		devicesMap = db.getDevices();
	}
	

	/**
	 * Returns list of IOT things from DB
	 * @return list of IOT things from DB
	 */
	public List<IOTThing> getAllIOTThings(){
		return iotThingsMap.values().stream().collect(Collectors.toList());
	}

	/**
	 * Replace IOT thing in the  DB with a given IOT thing only if the thing exist in DB.
	 * @param thing - IOT thing to replace
	 */
	public void updateDB(IOTThing thing) {

		iotThingsMap.put(thing.getUuid(), thing);
		
		if(iotThingsMap.containsKey(thing.getUuid())) {

			List<Device> oldDevices = iotThingsMap.get(thing.getUuid()).getDevices();
			List<Device> updatedDevices = thing.getDevices();
			
			oldDevices.forEach(device -> {
				if(!updatedDevices.contains(device)) 
					devicesMap.remove(device.getUuid());});
			
			updatedDevices.forEach(device-> {
				if(!oldDevices.contains(device)) 
					devicesMap.put(device.getUuid(), device);});
		}
	}

	/**
	 * Checks if DB contains given IOT thing
	 * @param thing - IOT thing to search
	 * @return true if IOT things exist in DB, otherwise return false
	 */
	public boolean containsIOTThing(IOTThing thing) {
		return iotThingsMap.containsKey(thing.getUuid());
	}

	/**
	 * Adding IOT thing to DB only if it doesn't exist in DB 
	 * @param thing - IOT thing to add
	 */
	public void addToDB(IOTThing thing) {
		if(!containsIOTThing(thing)) {
			iotThingsMap.put(thing.getUuid(), thing);
			if(thing.getDevices() != null)
				thing.getDevices().forEach(device -> devicesMap.put(device.getUuid(), device));
		}
	}

	/**
	 * Returns IOTThing object from DB by given id
	 * @param id - id of IOTThing 
	 * @return IOTThing if id exist in DB
	 * @throws 
	 * MissingDataException if id doesn't exist in DB
	 */
	public IOTThing getIOTThingByID(UUID id) {
		IOTThing thing = iotThingsMap.get(id);
		if(thing==null)
			throw new MissingDataException("id " + id + " doesn't exist in DB");
		return thing;
	}

	/**
	 * Return a IOTThing by given parameters
	 * @param type - type of the IOTThing
	 * @param model - model of the IOTThing
	 * @param manufacturer - manufacturer of the IOTThing
	 * @return IOTThing if it exist in DB
	 */
	public List<IOTThing> getIOTThingByProperties(String type, String model, String manufacturer) {
		List<IOTThing> things = new ArrayList<IOTThing>();
		try{
			HardwareType hardwareType = HardwareType.valueOf(type.toUpperCase());
			List<IOTThing> thingsList = iotThingsMap.values().stream().collect(Collectors.toList());
			for(IOTThing iotThing : thingsList) {
				if(iotThing.getHardwareType()==hardwareType && iotThing.getModel().equalsIgnoreCase(model) &&
				iotThing.getManufacturer().equalsIgnoreCase(manufacturer)) {
					things.add(iotThing);
				}
			}
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		if(things.isEmpty())
			throw new NotMatchingDataException("given data doesn't match any IOT thing");
		else
			return things;
	}

	/**
	 * Returns list of Devices from DB
	 * @return list of Devices things from DB
	 */
	public List<Device> getAllDevices() {
		return devicesMap.values().stream().collect(Collectors.toList());
	}

	/**
	 * Returns Device object from DB by given id
	 * @param id - id of Device 
	 * @return Device if id exist in DB
	 * @throws 
	 * MissingDataException if id doesn't exist in DB
	 */
	public Device getDeviceByID(UUID id) {
		Device device = devicesMap.get(id);
		if(device==null)
			throw new MissingDataException("id " + id + " doesn't exist in DB");
		return device;
	}

	/**
	 * Return a Device by given parameters
	 * @param type - type of the device
	 * @param model - model of the device
	 * @param manufacturer - manufacturer of the device
	 * @param thingID - ID of IOTThing which the device connected to
	 * @return Device if it exist in DB
	 */
	public List<Device> getDevicesByProperties(String type, String model, String manufacturer, UUID thingID) {
		if(!iotThingsMap.containsKey(thingID))
			throw new MissingDataException("IOT thing id " + thingID + " doesn't exist in DB");
		else {
			List<Device> devices = iotThingsMap.get(thingID).getDevices();
			HardwareType hardwareType = HardwareType.valueOf(type.toUpperCase());
			devices = devices.stream().filter(device -> device.getHardwareType()==hardwareType && device.getModel().equalsIgnoreCase(model) &&
					device.getManufacturer().equalsIgnoreCase(manufacturer)).collect(Collectors.toList());
			if(devices.isEmpty())
				throw new NotMatchingDataException("given data doesn't match any Device");
			return devices;
		}

	}	
}
