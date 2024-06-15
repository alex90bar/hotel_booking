package ru.skillbox.booking.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skillbox.booking.model.mongo.User;

/**
 * UserRepository
 *
 * @author alex90bar
 */

public interface UserStatsRepository extends MongoRepository<User, String> {

}
