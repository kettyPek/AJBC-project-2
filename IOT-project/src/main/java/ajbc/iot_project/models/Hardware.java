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

	private UUID uuid;
	private HardwareType hardwareType;
	private String model;
	private String manufacturer;

	public Hardware() {
		uuid = UUID.randomUUID();
	}
	
	public Hardware(HardwareType hardwareType, String model, String manufacturer) {
		uuid = UUID.randomUUID();
		this.hardwareType = hardwareType;
		this.model = model;
		this.manufacturer = manufacturer;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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
		return "Hardware [uuid=" + uuid + ", hardwareType=" + hardwareType + ", model=" + model + ", manufacturer="
				+ manufacturer + "]";
	}

}
