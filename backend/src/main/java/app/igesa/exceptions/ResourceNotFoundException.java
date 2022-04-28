package app.igesa.exceptions;

import app.igesa.enumerations.ErrorCode;
import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    @Getter
    private ErrorCode errorCode;

    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause ){
        super(message, cause);
    }
    
    
    public ResourceNotFoundException(String message, Throwable cause, ErrorCode errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }
    public ResourceNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }


}