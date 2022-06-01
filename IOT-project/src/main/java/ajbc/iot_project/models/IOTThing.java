package ajbc.iot_project.models;

import java.util.List;

import ajbc.iot_project.enums.HardwareType;
/**
 * This class represents an IOT thing 
 * @author ketty
 *
 */
public class IOTThing extends Hardware{
	
	private List<Device> devices;

	public IOTThing() {
		super();
	}
	
	public IOTThing(HardwareType hardwareType, String model, String manufacturer, List<Device> devices) {
		super(hardwareType, model, manufacturer);
		this.devices = devices;
	}
	
	
	public List<Device> getDevices() {
		return devices;
	}
	
	public void simulateInventoryChange() {
		int removeIndex = (int)(Math.random()*devices.size());
		System.out.println("remove" + removeIndex);
		devices.remove(removeIndex);
		int addIndex = (int)(Math.random()*devices.size());
		System.out.println("add:"+addIndex);
		devices.add(addIndex, new Device(HardwareType.ACTUATOR,"acw","BF"));
	}

	@Override
	public String toString() {
		return super.toString()+"IOTThing [devices=" + devices + "]";
	}

	

}
