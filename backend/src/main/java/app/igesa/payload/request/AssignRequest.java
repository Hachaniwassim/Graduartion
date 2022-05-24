package app.igesa.payload.request;
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

    private Long id_entreprise;
    private Long id_account;


    public Long getIdEntreprise() {
        return id_entreprise;
    }

    public void setIdEntreprise(Long id_entreprise) {
        this.id_entreprise = id_entreprise;
    }


    public Long getIdAccount() {
        return id_account;
    }

    public void setIdAccount(Long id_account) {
        this.id_account = id_account;
    }


}
