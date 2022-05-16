package app.igesa.repository;


import app.igesa.enumerations.GroupStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import app.igesa.entity.Groupe;

import java.util.List;

public interface IgroupeRepository extends JpaRepository<Groupe, Long> {




    List<Groupe> findByGroupStatus(GroupStatus status);


    List<Groupe> findAllById(Long id);
}