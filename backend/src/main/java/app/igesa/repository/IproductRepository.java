package app.igesa.repository;

import app.igesa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IproductRepository extends JpaRepository<Product,Long> {

}
