package ru.skillbox.booking.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skillbox.booking.model.mongo.Booking;

/**
 * BookingRepository
 *
 * @author alex90bar
 */

public interface BookingStatsRepository extends MongoRepository<Booking, String> {

}
