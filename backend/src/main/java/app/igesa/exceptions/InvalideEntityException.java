package app.igesa.exceptions;

import java.util.List;

import app.igesa.enumerations.ErrorCode;
import lombok.Getter;

public class InvalideEntityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCode errorCode;
	@Getter
	private List<String>errors;

	public InvalideEntityException(String message) {
		super(message);
	}
	public InvalideEntityException(String message, ErrorCode errorCode) {
	    super(message) ;
		this.errorCode=errorCode;
	}
	
	public InvalideEntityException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public InvalideEntityException(String message, Throwable cause , ErrorCode errorCode) {
		super(message,cause);
		this.errorCode=errorCode;
	
	}
	public InvalideEntityException(String message, Throwable cause , ErrorCode errorCode,List<String>errors) {
		super(message,cause);
		this.errorCode=errorCode;
		this.errors=errors;
	}
	public InvalideEntityException(String message, ErrorCode errorCode,List<String>errors) {
		super(message);
		this.errorCode=errorCode;
		this.errors=errors;
	}
	
	public ErrorCode getErrorCode() {return errorCode;}
	public List<String>getError(){
		return errors;
	}
}

