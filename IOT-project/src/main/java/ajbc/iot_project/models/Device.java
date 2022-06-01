package ajbc.iot_project.models;

import ajbc.iot_project.enums.HardwareType;
/**
 * This class represents a Device
 * @author ketty
 *
 */
public class Device extends Hardware{
	

	public Device(HardwareType hardwareType, String model, String manufacturer) {
		super(hardwareType, model, manufacturer);
	}
	
	public Device() {
		super();
	}

	@Override
	public String toString() {
		return "Device [ " + super.toString()+ " ]";
	}

	
}
