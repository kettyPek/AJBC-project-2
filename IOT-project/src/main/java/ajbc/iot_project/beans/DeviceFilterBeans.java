package ajbc.iot_project.beans;

import java.util.UUID;

import jakarta.ws.rs.QueryParam;

public class DeviceFilterBeans {
	
	@QueryParam("type") String type;
	@QueryParam("model") String model;
	@QueryParam("manufacturer") String manufacturer;
	@QueryParam("thingID") UUID thingID;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public UUID getThingID() {
		return thingID;
	}
	public void setThingID(UUID thingID) {
		this.thingID = thingID;
	}
	

}
