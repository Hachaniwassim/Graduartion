package it.igesa.services.implement;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
/**
 *
 * @author Tarchoun Abir
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Abir");
        // Use below commented code when will use Spring Security.
    }
}

// ------------------ Use below code for spring security --------------------------

/*class SpringSecurityAuditorAware implements AuditorAware<User> {

 public User getCurrentAuditor() {

  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

  if (authentication == null || !authentication.isAuthenticated()) {
   return null;
  }

  return ((MyUserDetails) authentication.getPrincipal()).getUser();
 }
}*/