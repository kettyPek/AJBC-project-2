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

	protected final UUID ID;
	protected HardwareType hardwareType;
	protected String model;
	protected String manufacturer;

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

	public String getModel() {
		return model;
	}

	public String getManufacturer() {
		return manufacturer;
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
