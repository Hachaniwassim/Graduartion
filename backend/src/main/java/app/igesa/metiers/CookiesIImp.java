package app.igesa.metiers;

import app.igesa.dto.CookiesDTO;
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
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CookiesIImp implements  Icookies{

    private static final Logger log = LoggerFactory.getLogger(CookiesIImp.class);


    @Autowired
    CookiesRepository cookiesRepository;

    @Override
    public CookiesDTO saveCookies(CookiesDTO c) {
       Cookies saved = cookiesRepository.save(CookiesDTO.toEntity(c));
        return CookiesDTO.fromEntity(saved);


    }

    @Override
    public Collection<CookiesDTO> viewCookies() {
        log.debug("HTTP GET ALL {} ..");
        return cookiesRepository.findAll().stream()
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

    @Override
    public void deleteCookies(Long id) {
        log.debug("HTTP DELETE BY ID {} ..", id);
        if (id == null) {
            log.error(" ENTREPRISE ID IS NULL ");
            return;
        }
        cookiesRepository.deleteById(id);
}

}


