package it.igesa;
import it.igesa.services.Irole;
import it.igesa.repository.IcomapnybusRepository;
import it.igesa.repository.AccountRepository;
import it.igesa.repository.IentrepriseRepository;
import it.igesa.repository.IgroupeRepository;
import it.igesa.upload.FilesStorageService;
import it.igesa.services.implement.AuditorAwareImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.Resource;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class MultiwebsitesApplication /*implements CommandLineRunner */{

	@Resource
    FilesStorageService storageService;

	@Autowired
    Irole iroleRepository;
	@Autowired
    AccountRepository userRepository;
	@Autowired
    IcomapnybusRepository icomapnybusRepository;
	@Autowired
    IgroupeRepository igroupeRepository;
	@Autowired
    IentrepriseRepository ientrepriseRepository;

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(MultiwebsitesApplication.class, args);

	}

	/*@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
		BCryptPasswordEncoder encoder;
		encoder = new BCryptPasswordEncoder();
		Role role = new Role("ROLE_ADMIN");
		iroleRepository.save(RoleDTO.fromEntity(role));
		CompanyBusiness companyBusiness = new CompanyBusiness() ;
		companyBusiness.setDomainename("test");
		companyBusiness.setDescription("test");
		icomapnybusRepository.save(companyBusiness);
		Groupe groupe= new Groupe();
		groupe.setDescription("mss");
		groupe.setGroupStatus(GroupStatus.ACTIVE);
		groupe.setName("mss");
		groupe.setMaxOperateur("40");
		groupe.setId(groupe.getId());
		igroupeRepository.save(groupe);
		Entreprise entreprise = new Entreprise();
		entreprise.setEmail("mssinfo@tn.net");
		entreprise.setCodefiscale("5555555555");
		entreprise.setCompanyname("MSS");
		entreprise.setNote("test test test ");
		entreprise.setCreatedDate(new Date());
		entreprise.setLastModifiedDate(new Date());
		entreprise.setGroupe(groupe);
		entreprise.setStreet("test");
		entreprise.setCity("test");
		entreprise.setRefrente("test");
		entreprise.setCompanyBusiness(companyBusiness);
		entreprise.setAdresse("test sounine");
		entreprise.setWebsiteUrl("http://mss.net/");
		entreprise.setPhone("22145879631");
		entreprise.setFax("789546213");
		ientrepriseRepository.save(entreprise);
		Account user = new Account();
		encoder = new BCryptPasswordEncoder();
		user.setUsername("ottavio lucifero");
		user.setPassword(encoder.encode("mss123#"));
		user.setEmail("lucifero@mss.tn");
		user.setMatchingPassword(encoder.encode("mss123#"));
		user.setFiscaleCode("11397488");
		user.setCreatedDate(new Date());
		user.setLastModifiedDate(new Date());
		user.setAccountStatus(AccountStatus.ACTIVE);
		user.setGroupe(groupe);
		userRepository.save(user);
	}*/
}
