package app.igesa.metiers;

import app.igesa.dto.*;
import app.igesa.entity.*;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.InvalideEntityException;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.*;
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


@NoArgsConstructor
@AllArgsConstructor
@Service
public class PostImp implements Ipost {


    @Autowired
    private IpostRepository ipostRepository;
    @Autowired
    IpagesRepository ipagesRepository;
    @Autowired
    ImetaRepository imetaRepository;
    @Autowired
    ItagsRepository itagsRepository;
    private static final Logger log = LoggerFactory.getLogger(PostImp.class);


    @Override
    public PostDTO save(PostDTO p) {
        List<String> errors = Postvalidator.validate(p);
        if (p.getId()!= null) {

        }
        if(!errors.isEmpty()) {
            log.error("POST not valid !" ,p);
            throw new InvalideEntityException("Post not valid !", ErrorCode.POST_NOT_VALID,errors);}

        Optional<Pages> page = ipagesRepository.findById(p.getPage().getId());

        if( page == null) {

            log.warn("Page with id =  was not found  in the database", p.getPage().getId());
            throw new ResourceNotFoundException("Page not Found with Id  = " + p.getPage().getId()+ " In the data base" ,ErrorCode.PAGES_NOT_FOUND);
        }

        if(page.isPresent()) {
           p.setPage(PageDTO.fromEntity(page.get()));
        }
        Optional<Tags> tag = itagsRepository.findById(p.getTags().getId());

        if( tag== null) {

            log.warn("Tag with id =  was not found  in the database", p.getTags().getId());
            throw new ResourceNotFoundException("Tag not Found with Id  = " + p.getTags().getId()+ " In the data base" ,ErrorCode.TAGS_NOT_FOUND);
        }

        if(tag.isPresent()) {
            p.setTags(TagsDTO.fromEntity((tag.get())));
        }


        Post saved =ipostRepository.save(PostDTO.toEntity(p));
        return PostDTO.fromEntity(saved);

    }


    @Override
    public Collection<PostDTO> view() {
        return ipostRepository.findAll().stream()
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
