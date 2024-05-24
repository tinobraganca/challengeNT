package com.ntconsulting.hotel.hotelservices.service.impl;

import com.ntconsulting.hotel.hotelservices.dto.RoomDTO;
import com.ntconsulting.hotel.hotelservices.repository.HotelRepository;
import com.ntconsulting.hotel.hotelservices.repository.RoomRepository;
import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;
import com.ntconsulting.hotel.hotelservices.repository.entity.RoomEntity;
import com.ntconsulting.hotel.hotelservices.service.HotelService;
import com.ntconsulting.hotel.hotelservices.service.RoomService;
import com.ntconsulting.hotel.hotelservices.web.rest.util.error.ApiError;
import com.ntconsulting.hotel.hotelservices.web.rest.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class RoomServiceImpl implements RoomService {

    private final static Logger LOG = LoggerFactory.getLogger(HotelService.class);

    private final HotelService hotelService;

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(HotelService hotelService, RoomRepository roomRepository){
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDTO createRoom(RoomDTO room) {
        LOG.info("M=RoomService.createRoom status=start method=createRoom");
        Optional<HotelEntity> hotel = hotelService.findById(room.hotelId());
        if(hotel.isPresent()){
            return new RoomDTO(roomRepository.save(new RoomEntity(room, hotel.get(), null)));
        }else{
            LOG.error("M=RoomService.createRoom status=error method=createRoom room={} hotel={}," +
                    " notFound", room.name(), room.hotelId());
            throw  new NotFoundException(new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND, 4004, "Error hotel nao encontrado"));        }
    }

    @Override
    public List<RoomDTO> createRooms (List<RoomDTO> rooms) {
        LOG.info("M=RoomService.createRooms status=start method=createRoom");
        return rooms.parallelStream().map(r -> {
            Optional<HotelEntity> hotel = hotelService.findById(r.hotelId());
            if(hotel.isPresent()){
                return new RoomDTO(roomRepository.save(new RoomEntity(r, hotel.get(), null)));
            }else{
                LOG.error("M=RoomService.createRooms status=error method=createRoom room={} hotel={}," +
                        " notFound", r.name(), r.hotelId());
                throw  new NotFoundException(new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND, 4004, "Error hotel nao encontrado"));            }
        }).toList();
    }

    @Override
    public List<RoomDTO> findAll() {
        return roomRepository.findAll().parallelStream().map(RoomDTO::new).collect(toList());

    }
}
