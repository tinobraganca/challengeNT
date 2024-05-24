package com.ntconsulting.hotel.hotelservices.web.rest;

import com.ntconsulting.hotel.hotelservices.dto.RoomDTO;
import com.ntconsulting.hotel.hotelservices.service.HotelService;
import com.ntconsulting.hotel.hotelservices.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room/")
public class RoomRest {

    private final static Logger LOG = LoggerFactory.getLogger(RoomRest.class);

    private final RoomService roomService;

    @Autowired
    public RoomRest(RoomService roomService){
        this.roomService = roomService;
    }

    @PostMapping(value = "create-room")
    public ResponseEntity<List<RoomDTO>> createRoom(@RequestHeader("Authorization") String authorization, @RequestBody List<RoomDTO> rooms){
        LOG.info("M=RoomRest.createRoom status=start method=createRoom");
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRooms(rooms));
    }

    @GetMapping(value = "find-all")
    public ResponseEntity<List<RoomDTO>> findAll(@RequestHeader("Authorization") String authorization){
        LOG.info("M=RoomRest.findAll status=start method=findAll");
        return  ResponseEntity.status(HttpStatus.CREATED).body(roomService.findAll());
    }
}
