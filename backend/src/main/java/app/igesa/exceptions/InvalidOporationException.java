package app.igesa.exceptions;

import app.igesa.enumerations.ErrorCode;
import lombok.Getter;

import java.util.List;

public class InvalidOporationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	@Getter
	private List<String>errors;

	public InvalidOporationException(String message) {
		super(message);
	}
	public InvalidOporationException(String message, ErrorCode errorCode) {
	    super(message) ;
		this.errorCode=errorCode;
	}

	public InvalidOporationException(String message, Throwable cause) {
		super(message,cause);
	}

	public InvalidOporationException(String message, Throwable cause , ErrorCode errorCode) {
		super(message,cause);
		this.errorCode=errorCode;

	}
	public InvalidOporationException(String message, Throwable cause , ErrorCode errorCode, List<String>errors) {
		super(message,cause);
		this.errorCode=errorCode;
		this.errors=errors;
	}
	public InvalidOporationException(String message, ErrorCode errorCode, List<String>errors) {
		super(message);
		this.errorCode=errorCode;
		this.errors=errors;
	}
	
	public ErrorCode getErrorCode() {return errorCode;}
	public List<String>getError(){
		return errors;
	}
}

