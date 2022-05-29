package app.igesa.metiers.implement;

import app.igesa.dto.*;
import app.igesa.entity.*;
import app.igesa.entity.pages.Pages;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.Ipost;
import app.igesa.repository.*;
import app.igesa.repository.pages.IpagesRepository;
import app.igesa.validators.Postvalidator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
public class PostImp implements Ipost {


    @Autowired
    private IpostRepository ipostRepository;
    @Autowired
    IpagesRepository ipagesRepository;

    @Autowired
    ItagsRepository itagsRepository;
    private static final Logger log = LoggerFactory.getLogger(PostImp.class);


    @Override
    public PostDTO save(PostDTO p) {


        Post saved =ipostRepository.save(PostDTO.toEntity(p));
        return PostDTO.fromEntity(saved);

    }


    @Override
    public Collection<PostDTO> view(Long id_entreprise) {
        return ipostRepository.findByEntrepriseId(id_entreprise).stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public PostDTO findById(Long id) {
        if ( id == null) {
            log.error(" Post Id is NULL .. !!");
            return null ;
        }

        return  ipostRepository.findById(id).map(PostDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Post with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.POST_NOT_FOUND));

    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            log.error(" Post ID IS NULL ");
            return;
        }
        ipostRepository.deleteById(id);
    }


    @Override
    public PostDTO update( PostDTO p,Long id ) {

        return null;
    }
}
