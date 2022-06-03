package ajbc.iot_project.DBservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ajbc.iot_project.DB.DBMock;
import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.exceptions.MissingDataException;
import ajbc.iot_project.exceptions.NotMatchingDataException;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

class DBServiceTest {

	private final Map<UUID, IOTThing> DB_IOT_THINGS_MAP = DBMock.getInstance().getIotThings();
	private final Map<UUID, Device> DB_DEVICE_MAP = DBMock.getInstance().getDevices();
	private final List<IOTThing> DB_IOT_THINGS = DB_IOT_THINGS_MAP.values().stream().collect(Collectors.toList());
	private final List<Device> DB_DEVICES = DB_DEVICE_MAP.values().stream().collect(Collectors.toList());
	private final IOTThing THING_FROM_DB = DB_IOT_THINGS.get(0);
	private DBService dbService;

	@BeforeEach
	void setUp() {
		dbService = new DBService();
	}

	@Test
	@DisplayName("Constructor test")
	void testConstructor() {
		assertAll(() -> assertEquals(DB_IOT_THINGS_MAP, dbService.iotThingsMap),
				() -> assertEquals(DB_DEVICE_MAP, dbService.devicesMap));
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
		assertAll(() -> assertTrue(dbService.containsIOTThing(THING_FROM_DB)),
				() -> assertFalse(dbService.containsIOTThing(newIOTthing)));
	}

	@Test
	@DisplayName("Add IOT thing to DB")
	void testAddToDB() {
		IOTThing newIOTthing = new IOTThing();
		dbService.addToDB(newIOTthing);

		// check DB for new IOT thing
		assertTrue(DB_IOT_THINGS_MAP.containsKey(newIOTthing.getUuid()));
		// check DB for new devices of IOT thing
		newIOTthing.getDevices().stream()
				.forEach(device -> assertTrue(DB_DEVICE_MAP.containsKey(device.getUuid()), device.getModel()));
	}

	@Test
	@DisplayName("Ensure receiving correct IOT thing from DB by given ID")
	void testGetIOTThingByID() {
		assertEquals(THING_FROM_DB, dbService.getIOTThingByID(THING_FROM_DB.getUuid()));
	}

	@Test
	@DisplayName("Ensure correct exceptionn handling for getIOTThingByID()")
	void testGetIOTThingByIDException() {
		assertThrows(MissingDataException.class, () -> dbService.getIOTThingByID(UUID.randomUUID()), "ID is not id DB");
	}

	@Test
	@DisplayName("getIOTThingByProperties() - receving correct IOT things list")
	void testGetIOTThingByProperties() {
		String type = THING_FROM_DB.getHardwareType().toString();
		String model = THING_FROM_DB.getModel();
		String manufacturer = THING_FROM_DB.getManufacturer();
		List<IOTThing> receivedThings = dbService.getIOTThingByProperties(type, model, manufacturer);
		receivedThings.forEach(thing -> assertAll(
				() -> assertEquals(type, thing.getHardwareType().toString()),
				() -> assertEquals(model, thing.getModel()),
				() -> assertEquals(manufacturer, thing.getManufacturer())));
	}
	
	@Test
	@DisplayName("Ensure correct exceptionn handling for getIOTThingByProperties()")
	void testGetIOTThingByPropertiesException() {
		String type = HardwareType.SENSOR.toString();
		String model = "";
		String manufacturer = "";
		assertThrows(NotMatchingDataException.class, () -> dbService.getIOTThingByProperties(type,model,manufacturer), "given data doesn't match any IOT thing");
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
		assertEquals(expectedDevice, dbService.getDeviceByID(expectedDevice.getUuid()));
	}

	@Test
	@DisplayName("Ensure correct exceptionn handling for getDeviceByID()")
	void testGetDeviceByIDException() {
		assertThrows(MissingDataException.class, () -> dbService.getDeviceByID(UUID.randomUUID()), "ID is not id DB");
	}

	@Test
	@DisplayName("getDevicesByProperties() - receving correct divces list")
	void testGetDevicesByProperties() {
		Device deviceFromDB = THING_FROM_DB.getDevices().get(0);
		String type = deviceFromDB.getHardwareType().toString();
		String model = deviceFromDB.getModel();
		String manufacturer = deviceFromDB.getManufacturer();
		UUID thingID = THING_FROM_DB.getUuid();
		List<Device> receivedDevices = dbService.getDevicesByProperties(type, model, manufacturer,thingID);
		receivedDevices.forEach(device -> assertAll(
				() -> assertEquals(type, device.getHardwareType().toString()),
				() -> assertEquals(model, device.getModel()),
				() -> assertEquals(manufacturer, device.getManufacturer())));
	}
	
	@Test
	@DisplayName("Ensure correct exceptionn handling for getDevicesByProperties() ")
	void testGetDevicesByPropertiesException() {
		Device deviceFromDB = THING_FROM_DB.getDevices().get(0);
		String type = deviceFromDB.getHardwareType().toString();
		String model = "";
		String manufacturer = "";
		UUID thingID = THING_FROM_DB.getUuid();
		UUID randomID = UUID.randomUUID();
		assertAll(
		() -> assertThrows(NotMatchingDataException.class, () -> dbService.getDevicesByProperties(type,model,manufacturer,thingID), "given data doesn't match any Device"),
		() -> assertThrows(MissingDataException.class, () -> dbService.getDevicesByProperties(type,model,manufacturer,randomID), "Things ID is not id DB"));	
	}
	

}
