package ru.skillbox.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skillbox.hotelbooking.model.Room;

/**
 * RoomRepository
 *
 * @author alex90bar
 */

public interface RoomRepository extends JpaRepository<Room, Long> {

}
