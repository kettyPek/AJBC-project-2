package ajbc.iot_project.beans;

import jakarta.ws.rs.QueryParam;

public class IOTThingFilterBean {
	
	@QueryParam("type") String type;
	@QueryParam("model") String model;
	@QueryParam("manufacturer") String manufacturer;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModle() {
		return model;
	}
	public void setModle(String modle) {
		this.model = modle;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	

}
