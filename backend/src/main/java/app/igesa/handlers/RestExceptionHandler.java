package app.igesa.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;

@RestControllerAdvice //yraja3 type Json 
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErroDTO> handleException (ResourceNotFoundException exception ,WebRequest webRequest){
		logger.error(exception);
		final HttpStatus notfound = HttpStatus.NOT_FOUND;
		final ErroDTO errorDto =
     	ErroDTO.builder().code(exception.getErrorCode())
     	.HttpCode(notfound.value())
     	.message(exception.getMessage())  
		.build();
	
      return new ResponseEntity<>(errorDto,notfound);
}
	
	@ExceptionHandler(InvalideEntityException.class)
	public ResponseEntity<ErroDTO> handleException (InvalideEntityException exception ,WebRequest webRequest){
		logger.error(exception);
		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		final ErroDTO errorDto =
     	ErroDTO.builder()
     	.code(exception.getErrorCode())
     	.HttpCode(badRequest.value())
     	.message(exception.getMessage())
		.build();
	
      return new ResponseEntity<>(errorDto,badRequest);
}
}