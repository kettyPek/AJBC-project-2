package ajbc.iot_project.exceptions;
/**
 * This class produced by invalid input which doesn't exist in DB.
 * @author ketty
 *
 */
public class MissimgDataException extends Exception {
	
	private static final long serialVersionUID = -8559387576247588570L;

	public MissimgDataException(String msg) {
		super(msg);
	}
}
