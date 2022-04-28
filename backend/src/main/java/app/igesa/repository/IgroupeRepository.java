package app.igesa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.Groupe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IgroupeRepository extends JpaRepository<Groupe, Long> {


    @Query("SELECT f FROM Groupe f WHERE f.active=:active" )
    List<Groupe> findByActive( @Param("active") boolean active);

    @Query("SELECT f FROM Groupe f WHERE f.confirmed=:confirmed" )
    List<Groupe> findByConfirmed(@Param("confirmed") boolean confirmed);

    @Query("SELECT f FROM Groupe f WHERE f.deleted=:deleted" )
    List<Groupe> findByDeleted(@Param("deleted") boolean deleted);

    List<Groupe> findAllById(Long id);
}