package app.igesa.repository;

import app.igesa.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Query("select po from Image po where po.name like :x")
	Optional<Image> findByName( @Param("x") String name);
}
