package ajbc.iot_project.exceptions;


public class MissingDataException extends RuntimeException {
	
	private static final long serialVersionUID = 3222379957696637684L;

	public MissingDataException(String msg) {
		super(msg);
	}

}
