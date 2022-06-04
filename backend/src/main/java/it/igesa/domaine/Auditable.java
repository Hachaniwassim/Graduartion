package it.igesa.domaine;
/** @author Tarchoun Abir
 *
 *
 */
import static javax.persistence.TemporalType.TIMESTAMP;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 ****@author Tarchoun Abir
 **/

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {


    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "CreationDate",nullable = false,updatable = false)
    protected Date createdDate;


    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}