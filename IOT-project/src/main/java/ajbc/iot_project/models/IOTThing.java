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

	@Override
	public String toString() {
		return "IOTThing [devices=" + devices + ", getUuid()=" + getUuid() + ", getHardwareType()=" + getHardwareType()
				+ ", getModel()=" + getModel() + ", getManufacturer()=" + getManufacturer() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
