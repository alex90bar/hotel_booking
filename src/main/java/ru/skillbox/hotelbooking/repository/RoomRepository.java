package ru.skillbox.hotelbooking.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skillbox.hotelbooking.model.Hotel;
import ru.skillbox.hotelbooking.model.Room;

/**
 * RoomRepository
 *
 * @author alex90bar
 */

public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    List<Room> findRoomsByHotel(Hotel hotel);

}
