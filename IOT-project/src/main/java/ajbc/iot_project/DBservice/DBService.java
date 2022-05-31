package ajbc.iot_project.DBservice;

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
	private Map<UUID,IOTThing> iotThings;
	private Map<UUID,Device> devices;
	
	public DBService() {
		db = DBMock.getInstance();
		iotThings = db.getIotThings();
		devices = db.getDevices();
	}
	
	//TODO write functions for api
	

}
