package ajbc.iot_project.resources;

import java.util.List;
import java.util.UUID;

import ajbc.iot_project.DBservice.DBService;
import ajbc.iot_project.beans.IOTThingFilterBean;
import ajbc.iot_project.models.IOTThing;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * This class handles Api requests of IOT thing
 * @author ketty
 *
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("iot")
public class IOTThingResource {
	
	DBService dbService = new DBService();
	
	
	@GET
	public Response getAllIOTThings() {
		List<IOTThing> thing = dbService.getAllIOTThings();
		return Response.ok().entity(thing).build();
	}
	
	@GET
	@Path("/{id}")
	public Response getIOTThingByID(@PathParam("id") UUID id) {
		IOTThing thing = dbService.getIOTThingByID(id);
		return Response.ok().entity(thing).build();
	}
	
//	@GET
//	@Path("/{type}/{model}/{manufacturer}")
//	public Response getIOTThingByProperties(@PathParam("type") String type,@PathParam("model") String model,@PathParam("manufacturer") String manufacturer) {
//		IOTThing thing = dbService.getIOTThingByProperties(type,model,manufacturer);
//		return Response.ok().entity(thing).build();
//	}
	
	@GET
	@Path("/filter")
	public Response getIOTThingByProperties(@BeanParam IOTThingFilterBean iotThingFilterBean) {
		IOTThing thing = dbService.getIOTThingByProperties(iotThingFilterBean.getType(),iotThingFilterBean.getModle(),iotThingFilterBean.getManufacturer());
		return Response.ok().entity(thing).build();
	}
	

}
