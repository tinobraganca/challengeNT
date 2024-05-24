package com.ntconsulting.hotel.hotelservices.service;

import com.ntconsulting.hotel.hotelservices.BaseMockTest;
import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.repository.HotelRepository;
import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;
import com.ntconsulting.hotel.hotelservices.service.impl.HotelServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest extends BaseMockTest {

    @InjectMocks
    HotelServiceImpl hotelService;

    @Mock
    HotelRepository hotelRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        hotelService = new HotelServiceImpl(hotelRepository);
    }

    @Test
    public void findAll(){
        HotelEntity mockFoo = mock(HotelEntity.class);
        when(hotelRepository.findAll()).thenReturn(Arrays.asList(
                mockFoo
                ));

        var response = hotelService.findAll();

        assertNotNull(response);
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    public void save(){
        HotelDTO hotelDTO = new HotelDTO(1L, "Pucllana", "Peru", "Lima",
                "Miraflores", 9, 112L, null);
        when(hotelRepository.save(any(HotelEntity.class))).thenReturn(new HotelEntity(hotelDTO));

        List<HotelDTO> response = hotelService.createHotel(Arrays.asList(hotelDTO));
        assertNotNull(response);
        assertEquals(hotelDTO.name(), response.getFirst().name());
        verify(hotelRepository, times(1)).save(any());
    }
}
