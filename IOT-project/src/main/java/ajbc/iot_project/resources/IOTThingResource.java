package ajbc.iot_project.resources;

import java.util.UUID;

import ajbc.iot_project.DBservice.DBService;
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
@Path("thing")
public class IOTThingResource {
	
	DBService dbService = new DBService();
	
//	@GET
//	@Path("/{id}")
//	public Response getStudentByID(@PathParam("id") UUID id) {
//		return dbService.getIOTThingByID(id);
//	}
//	
	
	

}
