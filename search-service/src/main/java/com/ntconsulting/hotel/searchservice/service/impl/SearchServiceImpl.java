package com.ntconsulting.hotel.searchservice.service.impl;

import com.ntconsulting.hotel.searchservice.dto.CompareResponseDTO;
import com.ntconsulting.hotel.searchservice.dto.RoomDTO;
import com.ntconsulting.hotel.searchservice.dto.SearchRequestDTO;
import com.ntconsulting.hotel.searchservice.repository.RoomRepository;
import com.ntconsulting.hotel.searchservice.repository.entity.RoomEntity;
import com.ntconsulting.hotel.searchservice.service.SearchService;
import com.ntconsulting.hotel.searchservice.specification.RoomSpecification;
import com.ntconsulting.hotel.searchservice.web.rest.SearchRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final static Logger LOG = LoggerFactory.getLogger(SearchService.class);

    private final RoomRepository roomRepository;

    @Autowired
    public SearchServiceImpl(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    @Override
    public Page<RoomDTO> findAllSortingByPrice(Pageable page){
        LOG.info("M=SearchService.findAllSortingByPrice status=start method=findAllSortingByPrice");
        page = PageRequest.of(0, 3, Sort.by("price"));
        List<RoomDTO> rooms =  roomRepository.findAll(page).stream().map(RoomDTO::new).toList();
        LOG.info("M=SearchService.findAllSortingByPrice status=end method=findAllSortingByPrice");
        return new PageImpl<>(rooms);
    }

    @Override
    public Page<RoomDTO> findAllFilteringByPrice(SearchRequestDTO request, Pageable page){
        page = PageRequest.of(0, 3, Sort.by("price"));
        Specification<RoomEntity> specification =
                RoomSpecification.filter(request.country(), request.city(), request.quantityPeople(), request.priceStart(),
                        request.priceEnd(), request.rating(), null, null);
        LOG.info("M=SearchService.findAllFilteringByPrice status=start method=findAllFilteringByPrice request={}", request.toString());
        List<RoomDTO> rooms = roomRepository.findAll(specification, page).stream().map(RoomDTO::new).toList();
        LOG.info("M=SearchService.findAllFilteringByPrice status=end method=findAllFilteringByPrice");
        return new PageImpl<>(rooms);
    }

    @Override
    public CompareResponseDTO compareRooms(Long room1, Long room2) {
        LOG.info("M=SearchService.findAllFilteringByPrice status=start method=findAllFilteringByPrice room1={} room2={}", room1, room2);
        return new CompareResponseDTO(new RoomDTO(roomRepository.findById(room1)), new RoomDTO(roomRepository.findById(room2)));
    }

}
