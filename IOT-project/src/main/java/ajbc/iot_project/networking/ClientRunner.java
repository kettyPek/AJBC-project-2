package ajbc.iot_project.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

public class ClientRunner {
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		
		final String SERVER_NAME = "localhost";
		final int SERVER_PORT = 9090;
		final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
		final int INITIAL_DELAY = 0;
		final int PERIOD = 10;
		
		List<IOTThing> thingsList = createIOTThings();
		
		
		ScheduledExecutorService clientsService = Executors.newScheduledThreadPool(thingsList.size());
		
		thingsList.forEach(thing -> {
			clientsService.scheduleAtFixedRate(new InventoryReport(thing,SERVER_NAME,SERVER_PORT),INITIAL_DELAY,PERIOD,TIME_UNIT);});

		

	}
	
	public static List<IOTThing> createIOTThings() {
		List<IOTThing> thingsList = Arrays.asList(
				new IOTThing(HardwareType.SOLAR_DEVICE,"clientSOL","solar",createListOfDevices()),
				new IOTThing(HardwareType.SOLAR_DEVICE,"clientSOL","solar",createListOfDevices()),
				new IOTThing(HardwareType.ELECTRIC_MACHINE,"clientABS","EL",createListOfDevices()),
				new IOTThing(HardwareType.ELECTRIC_MACHINE,"clientABS","ABB",createListOfDevices()));
		return thingsList;
	}
	
	public static List<Device> createListOfDevices(){
		List<Device> devicesList = new ArrayList<Device>();
		devicesList.add(new Device(HardwareType.ACTUATOR,"Actuator100","ABX"));
		devicesList.add(new Device(HardwareType.CONTROLLER,"RaspberryPI","Raspberry"));
		devicesList.add(new Device(HardwareType.SENSOR,"sense","GD"));
		return devicesList;
	}

}
