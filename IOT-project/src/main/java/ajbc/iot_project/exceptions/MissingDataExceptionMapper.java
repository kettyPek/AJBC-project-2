package ajbc.iot_project.exceptions;

import ajbc.iot_project.enums.InternalErrorCode;
import ajbc.iot_project.models.ErrorMassage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MissingDataExceptionMapper implements ExceptionMapper<MissimgDataException>{

	@Override
	public Response toResponse(MissimgDataException e) {
		ErrorMassage errorMessage = new ErrorMassage(e.getMessage(), InternalErrorCode.INVALID_ID, "google.com");
		return Response.status(Status.NOT_FOUND)
		.entity(errorMessage)
		.build();
	}
}
