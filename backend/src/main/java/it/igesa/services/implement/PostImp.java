package it.igesa.services.implement;

import it.igesa.enumerations.ErrorCode;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Ipost;
import it.igesa.repository.pages.IpagesRepository;
import it.igesa.domaine.Post;
import it.igesa.dto.PostDTO;
import it.igesa.repository.IpostRepository;
import it.igesa.repository.ItagsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
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
