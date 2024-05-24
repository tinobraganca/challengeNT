package com.ntconsulting.hotel.searchservice.web.rest;

import com.ntconsulting.hotel.searchservice.dto.CompareResponseDTO;
import com.ntconsulting.hotel.searchservice.dto.RoomDTO;
import com.ntconsulting.hotel.searchservice.dto.SearchRequestDTO;
import com.ntconsulting.hotel.searchservice.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search/")
public class SearchRest {

    private final static Logger LOG = LoggerFactory.getLogger(SearchRest.class);

    private final SearchService searchService;

    @Autowired
    public SearchRest(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping(value = "find-all-first-page")
    public Page<RoomDTO> searchRoom(@RequestHeader("Authorization") String authorization, Pageable p){
        LOG.info("M=SearchRest.searchRoom status=start method=searchRoom");
        return searchService.findAllSortingByPrice(p);
    }
    @GetMapping(value = "find-all")
    public Page<RoomDTO> searchFilteringRoom(@RequestHeader("Authorization") String authorization, @RequestBody SearchRequestDTO search, Pageable p){
        LOG.info("M=SearchRest.searchFilteringRoom status=start method=searchFilteringRoom");
        return searchService.findAllFilteringByPrice(search, p);
    }

    @GetMapping(value = "compare-room")
    public ResponseEntity<CompareResponseDTO> compareRooms(@RequestHeader("Authorization") String authorization, @RequestParam Long room1, @RequestParam Long room2){
        LOG.info("M=SearchRest.compareRooms status=start method=compareRooms");
        return ResponseEntity.status(HttpStatus.OK).body(searchService.compareRooms(room1, room2));
    }


}
