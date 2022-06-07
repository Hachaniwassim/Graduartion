package it.igesa.dto;
import it.igesa.domaine.Entreprise;
import it.igesa.domaine.PostRevendeur;
import it.igesa.domaine.Product;
import it.igesa.enumerations.RevendeursStatus;
import lombok.Builder;
import lombok.Data;
import javax.persistence.Column;
import java.util.Date;


/**
 *
 * @author Tarchoun Abir
 *
 */


@Data
@Builder
public class DelearsDTO {

        private Long id ;
        private String companyname;
        private String mobile ;
        private String cap ;
        private String email ;
        private String adresse ;
        private String nationality ;
        private String referent ;
        protected Date createdDate;
        protected Date lastModifiedDate;
        private Long entrepriseId;
        private Long productId;
        @Column(columnDefinition="text")
        private String message ;
        private RevendeursStatus revendeursStatus;



        public static DelearsDTO fromEntity(PostRevendeur contact) {
            if ( contact == null) {
                return null;
            }
            return DelearsDTO.builder()
                    .id(contact.getId())
                    .email(contact.getEmail())
                    .cap(contact.getCap())
                    .referent(contact.getReferent())
                    .adresse(contact.getAdresse())
                    .companyname(contact.getCompanyname())
                    .nationality(contact.getNationality())
                    .createdDate(contact.getCreatedDate())
                    .lastModifiedDate(contact.getLastModifiedDate())
                    .mobile(contact.getMobile())
                    .entrepriseId(contact.getEntreprise().getId())
                    .revendeursStatus(contact.getRevendeursStatus())
                    .productId(contact.getProduct().getId())
                    .message(contact.getMessage())
                    .build();
        }

        public static PostRevendeur toEntity(DelearsDTO dto) {

            if (dto == null) {
                return null;
            }

            PostRevendeur contact = new PostRevendeur();
            contact.setId(dto.getId());
            contact.setCompanyname(dto.getCompanyname());
            contact.setEmail(dto.getEmail());
            contact.setCap(dto.getCap());
            contact.setMobile(dto.getMobile());
            contact.setNationality(dto.getNationality());
            contact.setReferent(dto.getReferent());
            contact.setAdresse(dto.getAdresse());
            contact.setLastModifiedDate(dto.getLastModifiedDate());
            contact.setCreatedDate(dto.getCreatedDate());
            contact.setRevendeursStatus(dto.getRevendeursStatus());
            contact.setMessage(dto.getMessage());
            //=======================> entreprise
            Entreprise entreprise = new Entreprise();
            entreprise.setId(dto.getEntrepriseId());
            contact.setEntreprise(entreprise);
            //=======================> Product
            Product product = new Product();
            product.setId(dto.getProductId());
            contact.setProduct(product);
            return contact;
        }


    }

