package it.igesa.services.implement;
import it.igesa.dto.legale.CookiesDTO;
import it.igesa.domaine.Cookies;
import it.igesa.exceptions.ResourceNotFoundException;
import it.igesa.services.Icookies;
import it.igesa.services.Ientreprise;
import it.igesa.repository.CookiesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Tarchoun Abir
 *
 */

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CookiesImp implements Icookies {

    private static final Logger log = LoggerFactory.getLogger(CookiesImp.class);

    @Autowired
    Ientreprise ientrepriseService;
    @Autowired
    CookiesRepository cookiesRepository;

    @Override
    public CookiesDTO updateCookies(CookiesDTO cookiesDto) {
       // Cookies cookies = new Cookies();
        //if (cookies.getId()!=null){
        //    cookies = cookiesRepository.findById(cookiesDto.getId()).orElseThrow(IllegalAccessError::new);
       // }
       // cookies.setEntreprise((Entreprise) cookiesRepository.findByEntrepriseId(id_entreprise));
        Cookies saved = cookiesRepository.save(CookiesDTO.toEntity(cookiesDto));
        return CookiesDTO.fromEntity(saved);

    }

    @Override
    public CookiesDTO findByEntrepriseId(Long id_entreprise) {
        log.debug("HTTP GET BY ID {} ..", id_entreprise);
        if (id_entreprise== null) {
            log.error(" cookies Id is NULL .. !!");
            return null;
        }

        return (CookiesDTO) cookiesRepository.findByEntrepriseId(id_entreprise).stream().map(CookiesDTO::fromEntity);
    }


    @Override
    public Collection<CookiesDTO> getCookiesByEntrepriseId(Long id_entreprise) {

        return cookiesRepository.findByEntrepriseId(id_entreprise).stream()
                .map(CookiesDTO::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public CookiesDTO findById(Long id) {
        log.debug("HTTP GET BY ID {} ..", id);
        if (id == null) {
            log.error(" cookies Id is NULL .. !!");
            return null;
        }

        return cookiesRepository.findById(id).map(CookiesDTO::fromEntity).orElseThrow(() ->
                new ResourceNotFoundException(" No cookies   with  Id = :: " + id + " was founded {} ..!"));

    }

}






