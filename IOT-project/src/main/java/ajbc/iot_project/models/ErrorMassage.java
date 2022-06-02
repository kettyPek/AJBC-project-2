package ajbc.iot_project.models;

import ajbc.iot_project.enums.InternalErrorCode;

public class ErrorMassage {
	
	private String errorMessage;
	private InternalErrorCode errorCode;
	
	public ErrorMassage() {
		
	}
	
	
	public ErrorMassage(String errorMessage, InternalErrorCode errorCode) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public InternalErrorCode getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(InternalErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}
