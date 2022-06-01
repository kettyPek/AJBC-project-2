package ajbc.iot_project.DBservice;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import ajbc.iot_project.DB.DBMock;
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
}
