package ru.skillbox.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.hotelbooking.model.Hotel;

/**
 * HotelRepository
 *
 * @author alex90bar
 */

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
