package ajbc.iot_project.models;

import java.util.List;
import java.util.Objects;

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
		devices.remove(removeIndex);
		int addIndex = (int)(Math.random()*devices.size());
		devices.add(addIndex, new Device(HardwareType.ACTUATOR,"acw","BF"));
	}

	@Override
	public String toString() {
		return super.toString()+"IOTThing [devices=" + devices + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IOTThing other = (IOTThing) obj;
		return this.getUuid() == other.getUuid();
	}

	
	

}
