package com.ntconsulting.hotel.searchservice.service;

import com.ntconsulting.hotel.searchservice.dto.CompareResponseDTO;
import com.ntconsulting.hotel.searchservice.dto.RoomDTO;
import com.ntconsulting.hotel.searchservice.dto.SearchRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    Page<RoomDTO> findAllSortingByPrice(Pageable page);

    Page<RoomDTO> findAllFilteringByPrice(SearchRequestDTO request, Pageable page);

    CompareResponseDTO compareRooms(Long room1, Long room2);
}
