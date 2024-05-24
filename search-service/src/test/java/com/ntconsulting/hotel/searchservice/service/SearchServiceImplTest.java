package com.ntconsulting.hotel.searchservice.service;


import com.ntconsulting.hotel.searchservice.dto.SearchRequestDTO;
import com.ntconsulting.hotel.searchservice.repository.RoomRepository;
import com.ntconsulting.hotel.searchservice.repository.entity.RoomEntity;
import com.ntconsulting.hotel.searchservice.service.impl.SearchServiceImpl;
import com.ntconsulting.hotel.searchservice.specification.RoomSpecification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SearchServiceImplTest {

    @InjectMocks
    SearchServiceImpl searchService;

    @Mock
    RoomRepository roomRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        searchService = new SearchServiceImpl(roomRepository);
    }

    @Test
    public void findAll(){
        Pageable page = PageRequest.of(0, 3, Sort.by("price"));
        Page<RoomEntity> mockRoom = (Page<RoomEntity>) mock(RoomEntity.class);

        when(roomRepository.findAll(page)).thenReturn(mockRoom);


        var response = searchService.findAllSortingByPrice(page);

        assertNotNull(response);
        verify(roomRepository, times(1)).findAll(page);
    }

    @Test
    public void findAllFilteringByPrice(){
        Pageable page = PageRequest.of(0, 3, Sort.by("price"));
        SearchRequestDTO request = new SearchRequestDTO( "Pucllana", "Peru", "Lima",
                4,new BigDecimal(10),new BigDecimal(100),3,
                "10-05-2024 00:00", "15-05-2024 00:00");
        Page<RoomEntity> mockRoom = (Page<RoomEntity>) mock(RoomEntity.class);
        Specification<RoomEntity> specification =
                RoomSpecification.filter(request.country(), request.city(), request.quantityPeople(), request.priceStart(),
                        request.priceEnd(), request.rating(), null, null);
        when(roomRepository.findAll(specification, page)).thenReturn(mockRoom);


        var response = searchService.findAllFilteringByPrice(request, page);

        assertNotNull(response);
        verify(roomRepository, times(1)).findAll(page);
    }

    @Test
    public void compareRooms(){

        RoomEntity roomEntity = new RoomEntity(1L, "Suite Premium", 2, new BigDecimal(50.10), null, null);
        RoomEntity roomEntity2 = new RoomEntity(1L, "Suite Premium", 2, new BigDecimal(50.10), null, null);

        when(roomRepository.findById(1L)).thenReturn(roomEntity);
        when(roomRepository.findById(2L)).thenReturn(roomEntity2);

        var response = searchService.compareRooms(1L, 2L);

        assertNotNull(response);
        assertEquals(roomEntity.getId(), response.room1().id());
        assertEquals(roomEntity2.getId(), response.room2().id());

        verify(roomRepository, times(2)).findById(anyLong());
    }

}
