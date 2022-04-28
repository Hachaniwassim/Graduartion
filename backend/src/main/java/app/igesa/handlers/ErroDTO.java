package app.igesa.handlers;

import java.util.ArrayList;
import java.util.List;
import app.igesa.enumerations.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "Hols error code, error messages and related error messages of an error")
public class ErroDTO {
	
	@ApiModelProperty(value = "The error code." , required = true)
	private Integer HttpCode;
	@ApiModelProperty(value = "The error code." , required = true)
	private ErrorCode code;
	@ApiModelProperty(value = "A detaed error message." )
	private String message;
	@ApiModelProperty(value = "The input file related to the error.") 
	@Builder.Default
	 List<String>errors = new ArrayList<>();

}
