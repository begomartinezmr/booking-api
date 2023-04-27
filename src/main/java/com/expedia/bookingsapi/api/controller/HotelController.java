package com.expedia.bookingsapi.api.controller;

import static com.expedia.bookingsapi.api.utils.BookingConstants.CHECKIN_PARAMETER;
import static com.expedia.bookingsapi.api.utils.BookingConstants.CHECKOUT_PARAMETER;
import static com.expedia.bookingsapi.api.utils.BookingConstants.DATE_FORMAT;
import static com.expedia.bookingsapi.api.utils.BookingConstants.LOCATION_PARAMETER;
import static com.expedia.bookingsapi.api.utils.BookingConstants.PRICE_RANGE_PARAMETER;

import com.expedia.bookingsapi.api.model.Hotel;
import com.expedia.bookingsapi.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;

/**
 * Controller class for handling hotel-related requests.
 */
@Controller
public class HotelController {
    private final HotelService hotelService;

    /**
     * Constructor for the HotelController class.
     *
     * @param hotelService The hotel service to be used.
     */
    @Autowired
    public HotelController(final HotelService hotelService) {
        this.hotelService = hotelService;
    }

    /**
     * Retrieves a list of hotels matching the specified search criteria.
     *
     * @param location     The location to search for.
     * @param checkInDate  The check-in date for the search.
     * @param checkOutDate The check-out date for the search.
     * @param priceRange   The price range for the search.
     * @return A list of hotels matching the specified search criteria.
     */
    @QueryMapping
    public List<Hotel> searchHotels(
      final @Argument(name = LOCATION_PARAMETER) String location,
      final @Argument(name = CHECKIN_PARAMETER) @DateTimeFormat(pattern = DATE_FORMAT) Date checkInDate,
      final @Argument(name = CHECKOUT_PARAMETER) @DateTimeFormat(pattern = DATE_FORMAT) Date checkOutDate,
      final @Argument(name = PRICE_RANGE_PARAMETER) List<Integer> priceRange) {
        if (checkInDate.after(checkOutDate)) {
            throw new IllegalArgumentException("Check-in date must be before check-out date");
        }

        return hotelService.searchHotels(location, checkInDate, checkOutDate, priceRange);
    }
}
