package it.igesa.services.logo;

import it.igesa.dto.LogoDTO;
import it.igesa.domaine.Logo;
import it.igesa.repository.IbrandRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Tarchoun Abir
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Service
public class IlogoImp implements ILogo{
    @Autowired
    IbrandRepository ibrandRepository;
    @Override
    public LogoDTO save(LogoDTO brand) {

        Logo saved = ibrandRepository.save(LogoDTO.toEntity(brand));
        return  LogoDTO.fromEntity(saved);
    }

    @Override
    public Collection<LogoDTO> getAllByEntreprise(Long enterprise_id) {
        return ibrandRepository.findByEntrepriseId(enterprise_id).stream()
                .map(LogoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ibrandRepository.deleteById(id);

    }
}
