package app.igesa.metiers;

import app.igesa.dto.ProductDTO;
import java.util.Collection;
import java.util.Optional;


public interface Iproduct {
    public ProductDTO save (ProductDTO p);
    public Collection<ProductDTO> view();
    public ProductDTO findById(Long id);
    public void delete(Long id);
    public ProductDTO update(ProductDTO p, Long id);
}
