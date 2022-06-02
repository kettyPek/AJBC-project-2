package ajbc.iot_project.BDservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.DB.DBMock;
import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.exceptions.MissingDataException;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

class DBServiceTest {
	
	private final List<IOTThing> IOT_THINGS =  DBMock.getInstance().getIotThings().values().stream().collect(Collectors.toList());
	private final List<Device> DEVICES = DBMock.getInstance().getDevices().values().stream().collect(Collectors.toList());
	private final UUID THING_ID_FROM_DB = IOT_THINGS.get(0).getUuid();
	private final UUID device_ID_FROM_DB = DEVICES.get(0).getUuid();
	private final UUID RANDOM_UUID = UUID.randomUUID();
	private DBService dbService;

	@BeforeEach
	void setUp() {
		dbService = new DBService();
	}
	
	@Test
	@DisplayName("Constructor test")
	void testConstructor() {
		 assertAll(
			        () -> assertEquals(IOT_THINGS,dbService.getAllIOTThings()),
			        () -> assertEquals(DEVICES, dbService.getAllDevices()));
	}
	
	@Test
	void testUpdateDB() {
		IOTThing thing = IOT_THINGS.get(0);
		List<Device> devices = thing.getDevices();
		thing.simulateInventoryChange();
		dbService.updateDB(thing);
		
	}

//	@Test
//	@DisplayName("Test MissingDataException for getIOTThingByID method ")
//	void testGetIOTThingByIDExeption() {
//		assertThrows(MissingDataException.class,()-> dbService.getIOTThingByID(RANDOM_UUID));
//	}
//	
//	@Test
//	@DisplayName("Test getIOTThingByID method ")
//	void testGetIOTThingByID() {
//		assertThrows(MissingDataException.class,()-> dbService.getIOTThingByID(RANDOM_UUID));
//	}


}
