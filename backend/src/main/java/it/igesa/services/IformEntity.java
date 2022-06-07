package it.igesa.services;
import it.igesa.dto.FormDTO;
import it.igesa.enumerations.ContactStatus;

import javax.mail.MessagingException;
import java.util.Collection;
import java.util.Optional;

public interface IformEntity {
    public FormDTO save(FormDTO f);
    public Collection<FormDTO> view(Long id_entreprise);
    public Optional<FormDTO> findById(Long id);
    public void delete(Long id);
    public FormDTO updateStatusContact(Long id, ContactStatus status) throws MessagingException;
}
