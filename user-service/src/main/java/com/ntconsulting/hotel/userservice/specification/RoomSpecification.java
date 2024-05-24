package com.ntconsulting.hotel.userservice.specification;

import com.ntconsulting.hotel.userservice.repository.entity.BookingEntity;
import com.ntconsulting.hotel.userservice.repository.entity.RoomEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class RoomSpecification {
    public static Specification<RoomEntity> filter(Long id, LocalDateTime startAt, LocalDateTime endAt) {
        return (root, query, criteriaBuilder) -> {
            // Predicado para comparar IDs
            Predicate idPredicate = criteriaBuilder.equal(root.get("id"), id);

            // Subquery para verificar se existem reservas para o quarto nessas datas
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<BookingEntity> subRoot = subquery.from(BookingEntity.class);
            subquery.select(criteriaBuilder.literal(1L));
            subquery.where(
                    criteriaBuilder.equal(subRoot.get("room").get("id"), id),
                    criteriaBuilder.lessThanOrEqualTo(subRoot.get("startAt"), Date.from(startAt.atZone(ZoneId.systemDefault()).toInstant())),
                    criteriaBuilder.greaterThanOrEqualTo(subRoot.get("endAt"), Date.from(endAt.atZone(ZoneId.systemDefault()).toInstant()))
            );

            // Predicado principal para garantir que a subquery não retorne nenhum resultado
            Predicate noBookingPredicate = criteriaBuilder.not(criteriaBuilder.exists(subquery));

            // Combinar os predicados
            return criteriaBuilder.and(idPredicate, noBookingPredicate);
        };
    }
}
