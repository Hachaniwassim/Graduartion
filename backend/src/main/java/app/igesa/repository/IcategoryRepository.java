package app.igesa.repository;

import app.igesa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IcategoryRepository extends JpaRepository<Category,Long>{

}
