package ajbc.iot_project.models;

import java.util.Objects;
import java.util.UUID;

import ajbc.iot_project.enums.HardwareType;

/**
 * Represent a hardware properties
 * 
 * @author ketty
 *
 */
public abstract class Hardware {

	private final UUID ID;
	private HardwareType hardwareType;
	private String model;
	private String manufacturer;

	protected Hardware() {
		ID = UUID.randomUUID();
	}
	
	protected Hardware(HardwareType hardwareType, String model, String manufacturer) {
		ID = UUID.randomUUID();
		this.hardwareType = hardwareType;
		this.model = model;
		this.manufacturer = manufacturer;
	}

	public UUID getUuid() {
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

	@Override
	public int hashCode() {
		return Objects.hash(ID, hardwareType, manufacturer, model);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hardware other = (Hardware) obj;
		return Objects.equals(ID, other.ID);
	}
	
	

}
