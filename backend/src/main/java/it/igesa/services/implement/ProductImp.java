package it.igesa.services.implement;

import it.igesa.domaine.Product;
import it.igesa.dto.ProductDTO;
import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Iproduct;
import it.igesa.repository.IcategoryRepository;
import it.igesa.repository.IproductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Wassim Hachani
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
public class ProductImp  implements Iproduct {

    @Autowired
    IproductRepository iproductRepository;
    @Autowired
    IcategoryRepository icategoryRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductImp.class);


    @Override
    public ProductDTO save(ProductDTO p) {

        Product saved =iproductRepository.save(ProductDTO.toEntity(p));
        return ProductDTO.fromEntity(saved);

    }


    @Override
    public Collection<ProductDTO> view( Long enterprise_id) {
        return iproductRepository.findByEntrepriseId(enterprise_id).stream()
                .map(ProductDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        if ( id == null) {
            log.error(" Post Id is NULL .. !!");
            return null ;
        }

        return  iproductRepository.findById(id).map(ProductDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Post with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.PRODUCT_NOT_FOUND));

    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Product Id is NULL .. !!");
            return  ;
        }
      iproductRepository.deleteById(id);
    }
    @Override
    public void assignTags(Long product_id,Long tag_id){
        iproductRepository.assignTags(product_id,tag_id);
    }
    @Override
    public List<String> getTagsByProduct(Long product_id){
        return iproductRepository.getTagsByProduct(product_id);
    }
}
