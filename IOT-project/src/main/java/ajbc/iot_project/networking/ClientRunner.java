package ajbc.iot_project.networking;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ajbc.iot_project.enums.HardwareType;
import ajbc.iot_project.models.Device;
import ajbc.iot_project.models.IOTThing;

public class ClientRunner {
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		
		final String SERVER_NAME = "localhost";
		final int SERVER_PORT = 9090;
		
		List<IOTThing> thingsList = createIOTThings();
		
		
		ExecutorService clientsService = Executors.newFixedThreadPool(thingsList.size());
		for(int i=0; i<2;i++) 
			thingsList.forEach(thing -> {
				try {
					thing.simulateInventoryChange();
					clientsService.execute(new InventoryReport(thing,new Socket(SERVER_NAME,SERVER_PORT)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		

		
		clientsService.shutdown();
		
		clientsService.awaitTermination(20, TimeUnit.SECONDS);
	}
	
	public static List<IOTThing> createIOTThings() {
		List<IOTThing> thingsList = Arrays.asList(
//				new IOTThing(HardwareType.SOLAR_DEVICE,"solar100","solar",createListOfDevices()),
//				new IOTThing(HardwareType.SOLAR_DEVICE,"solar500","solar",createListOfDevices()),
//				new IOTThing(HardwareType.ELECTRIC_MACHINE,"electro","EL",createListOfDevices()),
				new IOTThing(HardwareType.ELECTRIC_MACHINE,"electro500","EL",createListOfDevices()));
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
