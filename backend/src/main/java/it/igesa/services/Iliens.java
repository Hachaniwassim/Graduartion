package it.igesa.services;
import it.igesa.dto.LiensDTO;
import java.util.Collection;

public interface Iliens {

        public LiensDTO save (LiensDTO  p);
        public Collection<LiensDTO > view(Long id_entreprise);
        public LiensDTO findById(Long id);
        public void delete(Long id);
    }

