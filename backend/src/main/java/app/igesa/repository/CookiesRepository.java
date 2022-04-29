package app.igesa.repository;

import app.igesa.entity.Cookies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CookiesRepository extends JpaRepository<Cookies, Long> {
}
