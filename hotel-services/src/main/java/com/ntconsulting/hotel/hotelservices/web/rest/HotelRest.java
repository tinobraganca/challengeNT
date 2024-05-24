package com.ntconsulting.hotel.hotelservices.web.rest;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel/")
public class HotelRest {

    private final static Logger LOG = LoggerFactory.getLogger(HotelRest.class);

    private final HotelService hotelService;

    @Autowired
    public HotelRest(HotelService hotelService){
    this.hotelService = hotelService;
    }

    @GetMapping(value = "find-all")
    public ResponseEntity<List<HotelDTO>> findAll(@RequestHeader("Authorization") String authorization){
        LOG.info("M=HotelRest.findAll status=start method=findAll");
        return  ResponseEntity.status(HttpStatus.CREATED).body(hotelService.findAll());
    }

    @PostMapping(value = "create-hotel")
    public ResponseEntity<List<HotelDTO>> createHotel(@RequestHeader("Authorization") String authorization, @RequestBody List<HotelDTO> hotels){
        LOG.info("M=HotelRest.createHotel status=start method=createHotel");
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotels));
    }

}
