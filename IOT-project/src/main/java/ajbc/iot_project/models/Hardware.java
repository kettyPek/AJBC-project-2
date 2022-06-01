package ajbc.iot_project.models;

import java.util.UUID;

import ajbc.iot_project.enums.HardwareType;

/**
 * Represent a hardware properties
 * 
 * @author ketty
 *
 */
public abstract class Hardware {

	private final String ID;
	private HardwareType hardwareType;
	private String model;
	private String manufacturer;

	public Hardware() {
		ID = UUID.randomUUID().toString();
	}
	
	public Hardware(HardwareType hardwareType, String model, String manufacturer) {
		ID = UUID.randomUUID().toString();
		this.hardwareType = hardwareType;
		this.model = model;
		this.manufacturer = manufacturer;
	}

	public String getUuid() {
		return ID;
	}


	public HardwareType getHardwareType() {
		return hardwareType;
	}

	public void setHardwareType(HardwareType hardwareType) {
		this.hardwareType = hardwareType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Hardware [uuid=" + ID + ", hardwareType=" + hardwareType + ", model=" + model + ", manufacturer="
				+ manufacturer + "]";
	}

}
