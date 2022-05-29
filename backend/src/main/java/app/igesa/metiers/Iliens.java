package app.igesa.metiers;
import app.igesa.dto.LiensDTO;
import java.util.Collection;

public interface Iliens {

        public LiensDTO save (LiensDTO  p);
        public Collection<LiensDTO > view(Long id_entreprise);
        public LiensDTO findById(Long id);
        public void delete(Long id);
    }

