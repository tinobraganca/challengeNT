package com.ntconsulting.hotel.hotelservices;

import org.junit.Before;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseMockTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this).close();
    }
}
