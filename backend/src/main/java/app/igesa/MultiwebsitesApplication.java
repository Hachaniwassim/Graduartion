package app.igesa;

import app.igesa.entity.Account;
import app.igesa.entity.CompanyBusiness;
import app.igesa.entity.Groupe;
import app.igesa.entity.Role;
import app.igesa.enumerations.AccountStatus;
import app.igesa.enumerations.GroupStatus;
import app.igesa.metiers.Irole;
import app.igesa.repository.IcomapnybusRepository;
import app.igesa.repository.AccountRepository;
import app.igesa.repository.IgroupeRepository;
import app.igesa.upload.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import app.igesa.metiers.AuditorAwareImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.annotation.Resource;
import java.util.Date;


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class MultiwebsitesApplication{

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

		Role r1 = new Role("ROLE_ADMIN");
		iroleRepository.save(r1);
		CompanyBusiness companyBusiness = new CompanyBusiness() ;
		companyBusiness.setDomainename("test");
		companyBusiness.setDescription("test");
		icomapnybusRepository.save(companyBusiness);
		Groupe groupe= new Groupe();
		groupe.setCompanyBusiness(companyBusiness);
		groupe.setDescription("mss");
		groupe.setGroupStatus(GroupStatus.ACTIVE);
		groupe.setName("mss");
		groupe.setId(groupe.getId());
		igroupeRepository.save(groupe);
		Account user = new Account();
		encoder = new BCryptPasswordEncoder();
		user.setUsername("ottavio lucifero");
		user.setPassword(encoder.encode("mss123#"));
		user.getRoles().add(r1);
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
