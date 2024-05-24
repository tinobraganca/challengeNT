package com.ntconsulting.hotel.userservice.repository;


import com.ntconsulting.hotel.userservice.repository.entity.RoomEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>, JpaSpecificationExecutor<RoomEntity> {

    Optional<RoomEntity> findOne(Specification<RoomEntity> specification);

}
