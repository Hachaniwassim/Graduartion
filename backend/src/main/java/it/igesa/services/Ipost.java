package it.igesa.services;

import it.igesa.dto.PostDTO;

import java.util.Collection;

public interface Ipost {
    public PostDTO save (PostDTO p);
    public Collection<PostDTO> view(Long id_entreprise);
    public PostDTO findById(Long id);
    public void delete(Long id);
    public PostDTO update(PostDTO p, Long id);
}
