package ajbc.iot_project.resources;

import java.util.List;
import java.util.UUID;

import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.beans.DeviceFilterBeans;
import ajbc.iot_project.beans.IOTThingFilterBean;
import ajbc.iot_project.models.Device;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("device")
public class DeviceResource {
	
	DBService dbService = new DBService();
	
	
	@GET
	public Response getAllDevices() {
		List<Device> device = dbService.getAllDevices();
		return Response.ok().entity(device).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getDeviceByID(@PathParam("id") UUID id) {
		Device device = dbService.getDeviceByID(id);
		return Response.ok().entity(device).build();
	}
	
//	@GET
//	@Path("/{type}/{model}/{manufacturer}/{thingID}")
//	public Response getDevicesByProperties(@PathParam("type") String type,@PathParam("model") String model,@PathParam("manufacturer") String manufacturer,@PathParam("thingID") UUID thingID) {
//		List<Device> devices = dbService.getDevicesByProperties(type,model,manufacturer,thingID);
//		return Response.ok().entity(devices).build();
//	}
	
	@GET
	@Path("/filter")
	public Response getDevicesByProperties(@BeanParam DeviceFilterBeans DeviceFilterBea) {
		List<Device> devices = dbService.getDevicesByProperties(DeviceFilterBea.getType(),DeviceFilterBea.getModel(),DeviceFilterBea.getManufacturer(),DeviceFilterBea.getThingID());
		return Response.ok().entity(devices).build();
	}
	
	

}
