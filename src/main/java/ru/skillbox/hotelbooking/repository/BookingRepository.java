package ru.skillbox.hotelbooking.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.skillbox.hotelbooking.model.Booking;

/**
 * BookingRepository
 *
 * @author alex90bar
 */

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking b "
                 + "WHERE ((b.dt_start >= :dtStart AND b.dt_start < :dtEnd) "
                 + "OR (b.dt_end > :dtStart AND b.dt_end <= :dtEnd)"
                 + "OR (b.dt_start <= :dtStart AND b.dt_end >= :dtEnd)) "
                 + "AND b.room = :roomId", nativeQuery = true)
    List<Booking> findBookingsByDates(@Param("dtStart") LocalDate dtStart,
                                      @Param("dtEnd") LocalDate dtEnd,
                                      @Param("roomId") Long roomId);
}
