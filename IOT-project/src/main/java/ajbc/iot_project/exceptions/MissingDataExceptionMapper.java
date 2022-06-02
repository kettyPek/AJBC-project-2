package ajbc.iot_project.exceptions;

import ajbc.iot_project.enums.InternalErrorCode;
import ajbc.iot_project.models.ErrorMassage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MissingDataExceptionMapper implements ExceptionMapper<MissingDataException>{

	@Override
	public Response toResponse(MissingDataException e) {
		ErrorMassage errorMessage = new ErrorMassage(e.getMessage(), InternalErrorCode.INVALID_ID);
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}
}
