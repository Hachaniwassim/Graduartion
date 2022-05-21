package app.igesa.metiers.implement;

import app.igesa.dto.CategoryDTO;
import app.igesa.entity.Category;
import app.igesa.enumerations.ErrorCode;
import app.igesa.enumerations.ImageTypes;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Icategory;
import app.igesa.metiers.images.ImageService;
import app.igesa.repository.IcategoryRepository;
import app.igesa.validators.CategoryValidators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.type.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author  Wassim Hachaani
 *
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class CategoryImp  implements Icategory {
    @Autowired
    IcategoryRepository icategoryRepository;

    @Autowired
    ImageService imageService;
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
        @Transactional
        @Override
        public void delete(Long id) {
        Optional<Category> optionalCategory = icategoryRepository.findById(id);
        if (!optionalCategory.isPresent()) throw new ResourceNotFoundException("Category " + id + " not exist");
        icategoryRepository.deleteById(id);
    }

    @Override
    public void deleteImage(Long categoryId, String type) {
        Category category = icategoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category " + categoryId + " not exist"));
        Long eid = category.getEnterprise().getId();
        Long gid = category.getEnterprise().getGroupe().getId();
        String name;
        if (type.equalsIgnoreCase("MENU")) {
            name = category.getMenuimage().substring(category.getMenuimage().indexOf("/category/") + "/category/".length(), category.getMenuimage().indexOf("?enterpriseId"));
            category.setMenuimage(null);
        }
        else {
            name = category.getBannerimage().substring(category.getBannerimage().indexOf("/category/") + "/category/".length(), category.getBannerimage().indexOf("?enterpriseId"));
            category.setBannerimage(null);
        }
        imageService.deleteImage(ImageTypes.CATEGORY, name, gid, eid);
        icategoryRepository.save(category);
    }


    @Override
    public CategoryDTO update(CategoryDTO c, Long id) {
        return null;
    }
}
