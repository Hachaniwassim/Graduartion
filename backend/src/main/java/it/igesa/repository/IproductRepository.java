package it.igesa.repository;
import it.igesa.domaine.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Tarchoun.Abir
 *
 */
public interface IproductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    Optional<Product> findFirstByEntrepriseId(Long id);
    List<Product> findByEntrepriseId(Long id);



    @Modifying
    @Transactional
    @Query(value="insert into products_tags  VALUES (:product_id,:tag_id)",nativeQuery = true)
    public void assignTags(@Param("product_id") Long product_id, @Param("tag_id") Long tag_id);

    @Query(value="select t.description from product p , tags t, products_tags pt where pt.product_id=p.id and pt.tag_id=t.id and pt.product_id = :product_id",nativeQuery = true)
    public List<String> getTagsByProduct(@Param("product_id") Long product_id);
}