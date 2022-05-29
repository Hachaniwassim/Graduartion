package app.igesa.metiers.implement;

import app.igesa.dto.LiensDTO;
import app.igesa.dto.NewsDTO;
import app.igesa.entity.Nwes;
import app.igesa.enumerations.ErrorCode;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.metiers.INwes;
import app.igesa.repository.InewsRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/***
 *
 * @author Tarchoun Abir
 *
 */
@Service
@NoArgsConstructor
@AllArgsConstructor
public class NewsImp  implements INwes {

    @Autowired
    InewsRepository inewsRepository;

    @Override
    public NewsDTO save(NewsDTO p) {
        Nwes saved = inewsRepository.save(NewsDTO.toEntity(p));
        return  NewsDTO.fromEntity(saved);

    }

    @Override
    public Collection<NewsDTO> view(Long id_entreprise) {

        return inewsRepository.findByEntrepriseId(id_entreprise).stream()
                .map(NewsDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public NewsDTO findById(Long id) {
        if ( id == null) {
            return null ;
        }

        return  inewsRepository.findById(id).map(NewsDTO::fromEntity).orElseThrow(()->
                new ResourceNotFoundException(" No Language   with  Id = :: " +id+ " was founded {} ..!",
                        ErrorCode.LANGUAGE_NOT_FOUND));

    }

    @Override
    public void delete(Long id) {
        if ( id == null) {
            return;
        }
       inewsRepository.deleteById(id);
    }



}
