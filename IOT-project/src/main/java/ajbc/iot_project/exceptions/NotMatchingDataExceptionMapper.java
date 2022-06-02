package ajbc.iot_project.exceptions;

import ajbc.iot_project.enums.InternalErrorCode;
import ajbc.iot_project.models.ErrorMassage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotMatchingDataExceptionMapper implements ExceptionMapper<NotMatchingDataException>{

	@Override
	public Response toResponse(NotMatchingDataException e) {
		ErrorMassage errorMessage = new ErrorMassage(e.getMessage(), InternalErrorCode.UNMATCHED_DATA);
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
}
