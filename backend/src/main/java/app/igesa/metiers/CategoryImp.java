package app.igesa.metiers;

import app.igesa.dto.CategoryDTO;
import app.igesa.entity.Category;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.IcategoryRepository;
import app.igesa.validators.CategoryValidators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoryImp  implements  Icategory{
    @Autowired
    IcategoryRepository icategoryRepository;

    private static final Logger log = LoggerFactory.getLogger(CategoryImp.class);

    @Override
    public CategoryDTO save(CategoryDTO c) {
        List<String> errors = CategoryValidators.validateCategory(c);
        if (c.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("Categorynot valid !" ,c);
            throw new InvalideEntityException("Categorynot valid !",ErrorCode.CATEGORY_NOT_VALID,errors);}


      Category saved =icategoryRepository.save(CategoryDTO.toEntity(c));
        return CategoryDTO.fromEntity(saved);

    }


    @Override
    public Collection<CategoryDTO> view() {
        return icategoryRepository.findAll().stream()
                .map(CategoryDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(Long id) {
        if (id == null) {
            log.error(" Category Id is NULL .. !!");
            return null;
        }

        return icategoryRepository.findById(id).map(CategoryDTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No Category with  Id = :: " + id + " was founded {} ..!",
                        ErrorCode.CATEGORY_NOT_FOUND));
    }
        @Override
        public void delete(Long id) {
        if ( id == null) {
            log.error(" Category ID IS NULL ");
            return;
        }
        icategoryRepository.deleteById(id);
    }

    @Override
    public CategoryDTO update(CategoryDTO c, Long id) {
        return null;
    }
}
