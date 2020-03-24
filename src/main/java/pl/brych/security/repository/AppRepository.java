package pl.brych.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brych.security.dto.AppUser;

@Repository
public interface AppRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
