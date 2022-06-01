package ajbc.iot_project.DBservice;

import java.util.List;
import java.util.Map;

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
	private volatile Map<String,IOTThing> iotThings;
	private volatile Map<String,Device> devices;
	
	public DBService() {
		db = DBMock.getInstance();
		iotThings = db.getIotThings();
		devices = db.getDevices();
	}

//	public Response getIOTThingByID(UUID id) throws MissimgDataException {
//		IOTThing thing = iotThings.get(id);
//		if(thing == null)
//			throw new MissimgDataException("id "+ id +" doesnt exist in DB");
//		return null;
//	}

	public void updateDB(IOTThing thing) {

		if(!iotThings.containsKey(thing.getUuid())) {
			iotThings.put(thing.getUuid(), thing);
			thing.getDevices().forEach(device -> devices.put(device.getUuid(), device));
			System.out.println("thing added");
		}
		else {
			List<Device> oldDevices = iotThings.get(thing.getUuid()).getDevices();
			List<Device> updatedDevices = thing.getDevices();
			
			oldDevices.forEach(device -> {
				if(!updatedDevices.contains(device)) {
					devices.remove(device.getUuid());
					System.out.println("device removed");}});
			
			
			updatedDevices.forEach(device-> {
				if(!oldDevices.contains(device)) {
					devices.put(device.getUuid(), device);
					System.out.println("device added");}});
		}
	}	
}
