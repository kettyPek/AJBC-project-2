package ajbc.iot_project.BDservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.DB.DBMock;
import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.exceptions.MissingDataException;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

class DBServiceTest {
	
	private final List<IOTThing> DB_IOT_THINGS =  DBMock.getInstance().getIotThings().values().stream().collect(Collectors.toList());
	private final List<Device> DB_DEVICES = DBMock.getInstance().getDevices().values().stream().collect(Collectors.toList());
	private final IOTThing THING_FROM_DB = DB_IOT_THINGS.get(0);
	private DBService dbService;

	@BeforeEach
	void setUp() {
		dbService = new DBService();
	}
	
	@Test
	@DisplayName("Constructor test")
	void testConstructor() {
		 assertAll(
			        () -> assertEquals(DB_IOT_THINGS,dbService.getAllIOTThings()),
			        () -> assertEquals(DB_DEVICES, dbService.getAllDevices()));
	}
	
	@Test
	@DisplayName("DB update after changee in devices list")
	void testUpdateDB() {
		THING_FROM_DB.simulateInventoryChange();
		dbService.updateDB(THING_FROM_DB);
		List<Device> devicesAfterChange = DBMock.getInstance().getIotThings().get(THING_FROM_DB.getUuid()).getDevices();
		assertEquals(THING_FROM_DB.getDevices(), devicesAfterChange);
	}
	
	@Test
	@DisplayName("checks if DB contains IOT thing")
	void testContainsIOTThing() {
		
		IOTThing newIOTthing = new IOTThing();
		assertAll(
		        () -> assertTrue(dbService.containsIOTThing(THING_FROM_DB)),
		        () -> assertFalse(dbService.containsIOTThing(newIOTthing)));
	}
	
	@Test
	@DisplayName("Add IOT thing to DB")
	void testAddToDB() {
		IOTThing newIOTthing = new IOTThing();
		dbService.addToDB(newIOTthing);
		
		//check DB for new IOT thing
		assertTrue(DBMock.getInstance().getIotThings().containsKey(newIOTthing.getUuid()));
		//check DB for new devices of IOT thing
		if(newIOTthing.getDevices() != null) {
			newIOTthing.getDevices().stream()
			.forEach(device -> assertTrue(DBMock.getInstance().getDevices().containsKey(device.getUuid()),device.getModel()));
		}
	}
	
	@Test
	@DisplayName("Ensure receiving correct IOT thing from DB by given ID")
	void testGetIOTThingByID() {
		assertEquals(THING_FROM_DB,dbService.getIOTThingByID(THING_FROM_DB.getUuid()));
	}
	
	@Test
	@DisplayName("Ensure correct exceptionn handling for getIOTThingByID()")
	void testGetIOTThingByIDException() {
		assertThrows(MissingDataException.class, () -> dbService.getIOTThingByID(UUID.randomUUID()),"ID is not id DB");
	}
	
	@Test
	@DisplayName("getIOTThingByProperties() - receving correct IOT things list")
	void testGetIOTThingByProperties() {
		//TODO
	}
	
	@Test
	@DisplayName("Ensure receiving correct devices list from DB")
	void testGetAllDevices() {
		assertEquals(DB_DEVICES, dbService.getAllDevices());
	}
	
	@Test
	@DisplayName("Ensure receiving correct Device from DB by given ID")
	void testGetDeviceByID() {
		Device expectedDevice = DB_DEVICES.get(0);
		assertEquals(expectedDevice,dbService.getDeviceByID(expectedDevice.getUuid()));
	}
	
	@Test
	@DisplayName("Ensure correct exceptionn handling for getDeviceByID()")
	void testGetDeviceByIDException() {
		assertThrows(MissingDataException.class, () -> dbService.getDeviceByID(UUID.randomUUID()),"ID is not id DB");
	}
	
	@Test
	@DisplayName("getDevicesByProperties() - receving correct divces list")
	void testGetDevicesByProperties() {
		//TODO
		
	}
	




}
