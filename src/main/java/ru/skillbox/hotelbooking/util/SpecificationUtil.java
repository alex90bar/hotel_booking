package ru.skillbox.hotelbooking.util;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import java.time.LocalDate;
import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.hotelbooking.model.Booking;
import ru.skillbox.hotelbooking.model.Room;

/**
 * SpecificationUtil
 *
 * @author alex90bar
 */

public class SpecificationUtil {

    public static <T> Specification<T> stringFieldLike(String fieldName, String value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase(Locale.ROOT) + "%");
        };
    }


    public static <T,V> Specification<T> fieldIsEqual(String fieldName, V value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.equal(root.get(fieldName), value);
        };
    }

    public static Specification<Room> isNotBookedInDateRange(String dtStart, String dtEnd) {
        return (root, query, criteriaBuilder) -> {
            if (dtStart == null || dtEnd == null) return criteriaBuilder.conjunction();
            LocalDate parsedDtStart = LocalDate.parse(dtStart);
            LocalDate parsedDtEnd = LocalDate.parse(dtEnd);
            if (!parsedDtEnd.isAfter(parsedDtStart)) return criteriaBuilder.conjunction();

            Subquery<Long> subQuery = query.subquery(Long.class);
            Root<Booking> bookingRoot = subQuery.from(Booking.class);

            subQuery.select(bookingRoot.get("room").get("id"));
            subQuery.where(
                criteriaBuilder.and(
                    criteriaBuilder.equal(bookingRoot.get("room").get("id"), root.get("id")),
                    criteriaBuilder.or(
                        criteriaBuilder.and(
                            criteriaBuilder.greaterThanOrEqualTo(bookingRoot.get("dtStart"), parsedDtStart),
                            criteriaBuilder.lessThan(bookingRoot.get("dtStart"), parsedDtEnd)
                        ),
                        criteriaBuilder.and(
                            criteriaBuilder.greaterThan(bookingRoot.get("dtEnd"), parsedDtStart),
                            criteriaBuilder.lessThanOrEqualTo(bookingRoot.get("dtEnd"), parsedDtEnd)
                        ),
                        criteriaBuilder.and(
                            criteriaBuilder.lessThanOrEqualTo(bookingRoot.get("dtStart"), parsedDtStart),
                            criteriaBuilder.greaterThanOrEqualTo(bookingRoot.get("dtEnd"), parsedDtEnd)
                        )
                    )
                )
            );

            return criteriaBuilder.not(criteriaBuilder.exists(subQuery));
        };
    }

    public static <T,V> Specification<T> childFieldIsEqual(String fieldName, String childFieldName, V value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.equal(root.get(fieldName).get(childFieldName), value);
        };
    }

    public static <T> Specification<T> integerIsMoreOrEqual(String fieldName, Integer value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.greaterThanOrEqualTo(root.get(fieldName), value);
        };
    }

    public static <T> Specification<T> integerIsLessOrEqual(String fieldName, Integer value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.lessThanOrEqualTo(root.get(fieldName), value);
        };
    }
}
