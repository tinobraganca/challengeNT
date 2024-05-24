package com.ntconsulting.hotel.userservice.service.impl;

import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;
import com.ntconsulting.hotel.userservice.repository.RoomRepository;
import com.ntconsulting.hotel.userservice.repository.entity.RoomEntity;
import com.ntconsulting.hotel.userservice.service.RoomService;
import com.ntconsulting.hotel.userservice.specification.RoomSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<RoomEntity> findAvailable(RequestBookingDTO request){
        Specification<RoomEntity> specification =
                RoomSpecification.filter(request.idRoom(), LocalDateTime.parse(request.startAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        LocalDateTime.parse(request.endAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        return roomRepository.findOne(specification);

    }

}
