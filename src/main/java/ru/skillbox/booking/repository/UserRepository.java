package ru.skillbox.booking.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.booking.model.User;

/**
 * UserRepository
 *
 * @author alex90bar
 */

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    boolean existsByEmailOrLogin(String email, String login);

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

}
