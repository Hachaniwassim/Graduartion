package app.igesa.metiers;

/**
 * @author Tarchoun Abir
 */
import app.igesa.dto.CookiesDTO;
import app.igesa.dto.EntrepriseDTO;
import app.igesa.entity.Cookies;
import app.igesa.exceptions.ResourceNotFoundException;
import app.igesa.repository.CookiesRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CookiesIImp implements  Icookies{

    private static final Logger log = LoggerFactory.getLogger(CookiesIImp.class);

    @Autowired
    EntrepriseImpl entrepriseService ;
    @Autowired
    CookiesRepository cookiesRepository;
    @Override
    public CookiesDTO updateCookies(CookiesDTO cookiesDto) {
        Cookies cookies= new Cookies();
        if (cookiesDto.getId()!=null){
            cookies = cookiesRepository.findById(cookiesDto.getId()).orElseThrow(IllegalAccessError::new);
        }
         cookies.setEntreprise(entrepriseService.getCurrentEnterprise());
         cookies.setTitle(cookiesDto.getTitle());
         cookies.setHtmlContent(cookiesDto.getHtmlContent());
         Cookies saved = cookiesRepository.save(CookiesDTO.toEntity(cookiesDto));
        return CookiesDTO.fromEntity(saved);

    }


    @Override
    public Collection<CookiesDTO> getCookies() {
        log.debug("HTTP GET ALL {} ..");
        return cookiesRepository.findFirstByEntreprise(entrepriseService.getCurrentEnterprise().getId()).stream()
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


