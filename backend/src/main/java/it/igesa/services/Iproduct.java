package it.igesa.services;

import it.igesa.dto.ProductDTO;

import java.util.Collection;
import java.util.List;


public interface Iproduct {
    public ProductDTO save (ProductDTO p);
    public Collection<ProductDTO> view(Long enterprise_id);
    public ProductDTO findById(Long id);
    public void delete(Long id);
    public void assignTags(Long product_id,Long tag_id);
    //public ProductDTO update(ProductDTO p, Long id);
    public List<String> getTagsByProduct(Long product_id);
}
