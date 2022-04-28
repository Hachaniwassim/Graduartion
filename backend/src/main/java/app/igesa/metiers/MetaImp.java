package app.igesa.metiers;
import app.igesa.dto.MetaDTO;
import app.igesa.dto.ProductDTO;
import app.igesa.entity.Meta;
import app.igesa.entity.Product;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.ImetaRepository;
import app.igesa.repository.IproductRepository;
import app.igesa.validators.MetaValidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@NoArgsConstructor
@AllArgsConstructor
public class MetaImp implements Imeta {
    @Autowired
    ImetaRepository imetaRepository;
    @Autowired
    IproductRepository iproductRepository;
    private static final Logger log = LoggerFactory.getLogger(PagesImp.class);


    @Override
    public MetaDTO save(MetaDTO m) {
        List<String> errors = MetaValidator.validate(m);
        if (m.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("Meta not valid !" ,m);
            throw new InvalideEntityException("meta not valid !", ErrorCode.META_NOT_VALID,errors);}

        Optional<Product> product = iproductRepository.findById(m.getProduct().getId());

        if( product== null) {

            log.warn("Product with id =  was not found  in the database", m.getProduct().getId());
            throw new ResourceNotFoundException("Product not Found with Id  = " +m.getProduct().getId()+ " In the data base" ,ErrorCode.PRODUCT_NOT_FOUND);
        }

        if(product.isPresent()) {
            m.setProduct(ProductDTO.fromEntity(product.get()));
        }

        Meta saved =imetaRepository.save(MetaDTO.toEntity(m));
        return MetaDTO.fromEntity(saved);

    }


    @Override
    public Collection<MetaDTO> view() {
        return imetaRepository.findAll().stream()
                .map(MetaDTO::fromEntity)
                .collect(Collectors.toList());    }

    @Override
    public Optional<MetaDTO> findById(Long id) {
        if ( id == null) {
            log.error(" Meta Id is NULL .. !!");
            return null ;
        }

        return  Optional.of(imetaRepository.findById(id).map(MetaDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Meta  with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.META_NOT_FOUND)));
    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Meta ID IS NULL ");
            return;
        }
        imetaRepository.deleteById(id);


    }

    @Override
    public MetaDTO update(MetaDTO m, Long id) {
        return null;
    }
}
