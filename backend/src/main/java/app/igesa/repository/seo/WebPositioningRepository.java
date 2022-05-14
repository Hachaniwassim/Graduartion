package app.igesa.repository.seo;
import app.igesa.entity.seo.Meta;
import app.igesa.entity.seo.WebPositioningPk;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * @author Tarchoun Abir
 */
public interface WebPositioningRepository extends JpaRepository<Meta, WebPositioningPk> {
}
