package com.expedia.bookingsapi.api.controller;

import com.expedia.bookingsapi.api.model.Hotel;
import com.expedia.bookingsapi.api.model.Location;
import com.expedia.bookingsapi.service.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static com.expedia.bookingsapi.api.utils.TestConstants.CHECK_IN_DATE_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.LOCATION_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.CHECK_OUT_DATE_DEFAULT;
import static com.expedia.bookingsapi.api.utils.TestConstants.PRICE_RANGE_DEFAULT;

@ExtendWith(MockitoExtension.class)
class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        hotelController = new HotelController(hotelService);
    }

    @Test
    void testSearchHotels() {

        final List<Hotel> expectedHotels = List.of(
          Hotel.builder()
            .id("1")
            .name("Hotel 1")
            .location(new Location("001", "London"))
            .price(150)
            .image("https://hotel1.com/image.jpg")
            .reviews(new ArrayList<>())
            .averageReviewScore(0.0)
            .totalPrice(300)
            .build(),
          Hotel.builder()
            .id("2")
            .name("Hotel 2")
            .location(new Location("001", "London"))
            .price(180)
            .image("https://hotel2.com/image.jpg")
            .reviews(new ArrayList<>())
            .averageReviewScore(0.0)
            .totalPrice(360)
            .build()
        );

        when(
          hotelService.searchHotels(LOCATION_DEFAULT, CHECK_IN_DATE_DEFAULT, CHECK_OUT_DATE_DEFAULT,
            PRICE_RANGE_DEFAULT)).thenReturn(
          expectedHotels);

        final List<Hotel> actualHotels = hotelController.searchHotels(LOCATION_DEFAULT,
          CHECK_IN_DATE_DEFAULT, CHECK_OUT_DATE_DEFAULT,
          PRICE_RANGE_DEFAULT);

        Assertions.assertEquals(expectedHotels.size(), actualHotels.size());
        Assertions.assertEquals(expectedHotels.get(0).getName(), actualHotels.get(0).getName());
        Assertions.assertEquals(expectedHotels.get(1).getName(), actualHotels.get(1).getName());
    }

    @Test
    void shouldThrowExceptionWhenCheckInDateAfterCheckOutDate() {
        assertThrows(IllegalArgumentException.class, () ->
          hotelController.searchHotels(LOCATION_DEFAULT, CHECK_OUT_DATE_DEFAULT,
            CHECK_IN_DATE_DEFAULT,
            PRICE_RANGE_DEFAULT));
    }
}
