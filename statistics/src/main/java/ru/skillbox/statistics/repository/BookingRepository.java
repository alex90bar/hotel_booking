package ru.skillbox.statistics.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.skillbox.statistics.model.Booking;

/**
 * BookingRepository
 *
 * @author alex90bar
 */

public interface BookingRepository extends MongoRepository<Booking, String> {

}
