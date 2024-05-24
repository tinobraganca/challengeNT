package com.ntconsulting.hotel.hotelservices.service;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.dto.RoomDTO;
import com.ntconsulting.hotel.hotelservices.repository.HotelRepository;
import com.ntconsulting.hotel.hotelservices.repository.RoomRepository;
import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;
import com.ntconsulting.hotel.hotelservices.repository.entity.RoomEntity;
import com.ntconsulting.hotel.hotelservices.service.impl.HotelServiceImpl;
import com.ntconsulting.hotel.hotelservices.service.impl.RoomServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class RoomServiceImplTest {

    @InjectMocks
    RoomServiceImpl roomService;
    @Mock
    HotelServiceImpl hotelService;

    @Mock
    RoomRepository roomRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        roomService = new RoomServiceImpl(hotelService, roomRepository);
    }

    @Test
    public void findAll(){
        RoomEntity mockFoo = mock(RoomEntity.class);
        when(roomRepository.findAll()).thenReturn(Arrays.asList(
                mockFoo
        ));

        var response = hotelService.findAll();

        assertNotNull(response);
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void save(){
        HotelDTO hotelDTO = new HotelDTO(1L, "Pucllana", "Peru", "Lima",
                "Miraflores", 9, 112L, null);
        RoomDTO room = new RoomDTO(
                1L, "Suite Premium", 2, new BigDecimal(50.10), 1L,
                null, null, null, null
        );

        when(hotelService.findById(anyLong())).thenReturn(Optional.of(new HotelEntity(hotelDTO)));
        when(roomRepository.save(any(RoomEntity.class))).thenReturn(new RoomEntity(room, new HotelEntity(hotelDTO), null));

        List<RoomDTO> response = roomService.createRooms(Arrays.asList(room));
        assertNotNull(response);
        assertEquals(room.name(), response.getFirst().name());
        verify(hotelService, times(1)).findById(any());
        verify(roomRepository, times(1)).save(any());

    }

}
