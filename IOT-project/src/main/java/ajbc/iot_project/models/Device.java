package ajbc.iot_project.models;

import ajbc.iot_project.enums.HardwareType;
/**
 * This class represents a Device
 * @author ketty
 *
 */
public class Device extends Hardware{
	
	private final double MAX_READING = 100;
	private final double MIN_READING = 0;
	private double reading;

	public Device(HardwareType hardwareType, String model, String manufacturer) {
		super(hardwareType, model, manufacturer);
		this.reading = 0;
	}
	
	public Device() {
		super();
	}
	
	/**
	 * Simulates a reading.
	 * Changes reading value to random number in range [MIN_READING,MAX_READING].
	 */
	public void simulateReading() {
		reading = Math.random()*(MAX_READING-MIN_READING)+MAX_READING;
	}

	@Override
	public String toString() {
		return "Device [reading=" + reading + ", getUuid()=" + getUuid() + ", getHardwareType()=" + getHardwareType()
				+ ", getModel()=" + getModel() + ", getManufacturer()=" + getManufacturer() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
