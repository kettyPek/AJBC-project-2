package ajbc.iot_project.models;

import java.util.ArrayList;
import java.util.List;

import ajbc.iot_project.enums.HardwareType;
/**
 * This class represents an IOT thing 
 * @author ketty
 *
 */
public class IOTThing extends Hardware{
	
	protected List<Device> devices;

	public IOTThing() {
		super();
		devices = new ArrayList<Device>();
	}
	
	public IOTThing(HardwareType hardwareType, String model, String manufacturer, List<Device> devices) {
		super(hardwareType, model, manufacturer);
		this.devices = devices;
	}
	
	
	public List<Device> getDevices() {
		return devices;
	}
	
	/**
	 * Simulates a chnage in devices list.
	 * Removes random device from the list if the list isn't empty
	 * Adds new device to the list in random place
	 */
	public void simulateInventoryChange() {
		if(!devices.isEmpty()) {
			int removeIndex = (int)(Math.random()*devices.size());
			devices.remove(removeIndex);
		}
		int addIndex = (int)(Math.random()*devices.size());
		devices.add(addIndex, new Device(HardwareType.ACTUATOR,"acw","BF"));	
	}

	@Override
	public String toString() {
		return super.toString()+"IOTThing [devices=" + devices + "]";
	}
}
