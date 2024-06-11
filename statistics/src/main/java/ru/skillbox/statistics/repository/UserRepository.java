package ru.skillbox.statistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skillbox.statistics.model.User;

/**
 * UserRepository
 *
 * @author alex90bar
 */

public interface UserRepository extends MongoRepository<User, String> {

}
