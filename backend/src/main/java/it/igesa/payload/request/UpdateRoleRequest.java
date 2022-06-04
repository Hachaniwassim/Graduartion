package it.igesa.payload.request;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Tarchoun Abir
 *
 */

@Setter
@Getter
public class UpdateRoleRequest {
    private Long id_account;
    private Long id_role;

    public Long getId_account() {
        return id_account;
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }
}
