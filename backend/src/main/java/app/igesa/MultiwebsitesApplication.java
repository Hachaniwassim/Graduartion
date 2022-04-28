package app.igesa;

import app.igesa.entity.Account;
import app.igesa.entity.Role;
import app.igesa.metiers.Irole;
import app.igesa.repository.IcomapnybusRepository;
import app.igesa.repository.AccountRepository;
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


@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class MultiwebsitesApplication implements CommandLineRunner {

	@Resource
	FilesStorageService storageService;

	@Autowired
	Irole iroleRepository;

	@Autowired
    AccountRepository userRepository;
	@Autowired
	IcomapnybusRepository icomapnybusRepository;


		@Bean
	    public AuditorAware<String> auditorAware() {
	        return new AuditorAwareImpl();
	    }

	public static void main(String[] args) {
		SpringApplication.run(MultiwebsitesApplication.class, args);

	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();

		BCryptPasswordEncoder encoder;
		encoder = new BCryptPasswordEncoder();

		Role r1 = new Role("ROLE_ADMIN");
		iroleRepository.save(r1);
		Account user = new Account();
		encoder = new BCryptPasswordEncoder();
		user.setUsername("ottavio lucifero");
		user.setPassword(encoder.encode("mss123#"));
		user.getRoles().add(r1);
		user.setEmail("lucifero@mss.tn");
		user.setMatchingPassword(encoder.encode("mss123#"));
		user.setFiscaleCode("11397488");
		userRepository.save(user);
	}

}
