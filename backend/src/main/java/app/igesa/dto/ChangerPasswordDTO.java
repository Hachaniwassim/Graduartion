package app.igesa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class ChangerPasswordDTO {
    private Long id ;
    private String password ;
    private String confirmPassword ;
    protected Date lastModifiedDate;
}
