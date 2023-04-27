package com.expedia.bookingsapi.api.service;

import static com.expedia.bookingsapi.api.utils.TestConstants.CHECK_IN_DATE_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.LOCATION_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.CHECK_OUT_DATE_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.PRICE_RANGE_DEFAULT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.expedia.bookingsapi.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.expedia.bookingsapi.api.model.Hotel;

public class HotelServiceTest {

    private HotelService hotelService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelService();
    }

    @Test
    void shouldReturnHotelsWithPricesAndReviews() {
        final List<Hotel> hotels = hotelService.searchHotels(LOCATION_DEFAULT,
          CHECK_IN_DATE_DEFAULT,
          CHECK_OUT_DATE_DEFAULT, PRICE_RANGE_DEFAULT);

        assertFalse(hotels.isEmpty());

        for (Hotel hotel : hotels) {
            assertTrue(hotel.getTotalPrice() > 0);
            assertTrue(hotel.getAverageReviewScore() >= 0 && hotel.getAverageReviewScore() <= 10);
        }
    }

    @Test
    void shouldReturnNoHotelsForInvalidLocation() {
        final List<Hotel> hotels = hotelService.searchHotels("Invalid Location",
          CHECK_IN_DATE_DEFAULT,
          CHECK_OUT_DATE_DEFAULT, PRICE_RANGE_DEFAULT);

        assertTrue(hotels.isEmpty());
    }


}
