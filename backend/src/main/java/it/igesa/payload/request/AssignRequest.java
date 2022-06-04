package it.igesa.payload.request;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Data
@Setter
@Getter
public class AssignRequest {

    private Long id_groupe;
    private Long id_account;


//   public Long getId_groupe() {
//        return id_groupe;
//    }
}
