package com.ntconsulting.hotel.hotelservices.service.impl;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.repository.HotelRepository;
import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;
import com.ntconsulting.hotel.hotelservices.service.HotelService;
import com.ntconsulting.hotel.hotelservices.web.rest.HotelRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class HotelServiceImpl implements HotelService {

    private final static Logger LOG = LoggerFactory.getLogger(HotelService.class);

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    @Override
    public List<HotelDTO> createHotel(List<HotelDTO> hotels) {
        LOG.info("M=HotelService.createHotel status=start method=createHotel");
        return hotels.parallelStream().map(h -> {
            return new HotelDTO(hotelRepository.save(new HotelEntity(h)));
        }).toList();
    }

    @Override
    public List<HotelDTO> findAll() {
        LOG.info("M=HotelService.findAll status=start method=findAll");
        return hotelRepository.findAll().stream().filter(Objects::nonNull).map(HotelDTO::new).collect(toList());
    }

    @Override
    public Optional<HotelEntity> findById(Long id) {
        return hotelRepository.findById(id);
    }
}
