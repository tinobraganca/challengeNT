package com.ntconsulting.hotel.searchservice.specification;

import com.ntconsulting.hotel.searchservice.repository.entity.RoomEntity;
import jakarta.persistence.criteria.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RoomSpecification {
    public static Specification<RoomEntity> filter(String country, String city, Integer quantityPeople,
                                                   BigDecimal priceStart, BigDecimal priceEnd, Integer rating, LocalDateTime startAt, LocalDateTime endAt) {
        return (root, query, criteriaBuilder) -> {
            Predicate countryPredicate =
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("hotel").get("country")), StringUtils.isBlank(country)
                            ? likePattern("") : likePattern(country.toLowerCase()));
            Predicate cityPredicate =
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("hotel").get("city")), StringUtils.isBlank(city)
                            ? likePattern("") : likePattern(city.toLowerCase()) );
            Predicate roomSizePredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("peopleCapacity"), quantityPeople != null
                            ? quantityPeople : 0);
            Predicate priceStartPredicate =
                    criteriaBuilder.between(root.get("price"), priceStart != null
                            ? priceStart : BigDecimal.ZERO, priceEnd != null
                            ? priceEnd : new BigDecimal(99999999));
            Predicate roomHotelRatingPredicate =
                    criteriaBuilder.greaterThanOrEqualTo(root.get("hotel").get("rating"), rating != null
                            ? rating : 0);

            Predicate dtBooking =
                    criteriaBuilder.not(criteriaBuilder.between(root.get("booking").get("startAt"), startAt, endAt));


            return criteriaBuilder.and(countryPredicate, cityPredicate, roomSizePredicate, priceStartPredicate, roomHotelRatingPredicate);

        };
    }

    private static String likePattern(String value) {
        return "%" + value + "%";
    }
}
