package ru.skillbox.hotelbooking.util;

import java.util.Locale;
import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.hotelbooking.model.Hotel;

/**
 * SpecificationUtil
 *
 * @author alex90bar
 */

public class SpecificationUtil {

    public static Specification<Hotel> stringFieldLike(String fieldName, String value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.like(cb.lower(root.get(fieldName)), "%" + value.toLowerCase(Locale.ROOT) + "%");
        };
    }


    public static <T> Specification<Hotel> fieldIsEqual(String fieldName, T value) {
        return (root, cq, cb) -> {
            if (value == null) return cb.conjunction();
            return cb.equal(root.get(fieldName), value);
        };
    }
}
