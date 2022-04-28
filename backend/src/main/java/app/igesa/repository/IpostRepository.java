package app.igesa.repository;

import app.igesa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpostRepository extends JpaRepository<Post,Long> {

}
