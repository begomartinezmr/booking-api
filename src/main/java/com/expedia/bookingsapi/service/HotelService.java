package com.expedia.bookingsapi.service;

import static com.expedia.bookingsapi.api.utils.BookingConstants.HOTELS_FILE;

import com.expedia.bookingsapi.BookingsApiApplication;
import com.expedia.bookingsapi.api.model.Hotel;
import com.expedia.bookingsapi.api.model.Review;
import com.expedia.bookingsapi.api.utils.BookingUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class that handles hotel related operations.
 */
@Service
public class HotelService {
    private static final Map<String, Hotel> HOTELS;

    static {
        try (var reader = new JsonReader(new FileReader(new File(
          Objects.requireNonNull(
            BookingsApiApplication.class.getClassLoader().getResource(HOTELS_FILE)
          ).toURI()
        )))) {
            Hotel[] hotels = new Gson().fromJson(reader, Hotel[].class);
            HOTELS = Arrays.stream(hotels).collect(Collectors.toMap(Hotel::getId, hotel -> hotel));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Searches hotels based on the given parameters.
     *
     * @param location     The location of the hotel.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @param priceRange   The price range for filtering hotels.
     * @return List of filtered hotels.
     */
    public List<Hotel> searchHotels(
      final String location,
      final Date checkInDate,
      final Date checkOutDate,
      final List<Integer> priceRange) {

        final List<Hotel> filteredHotels = filterHotels(HOTELS, location, priceRange);

        for (final Hotel hotel : filteredHotels) {
            hotel.setTotalPrice(calculateTotalPrice(hotel.getPrice(), checkInDate, checkOutDate));
            hotel.setAverageReviewScore(BookingUtils.calculateAverage(getRatings(hotel)));
        }

        filteredHotels.sort(Comparator.comparing(Hotel::getAverageReviewScore).reversed());

        return filteredHotels;
    }

    /**
     * Filters hotels based on the given parameters.
     *
     * @param hotels     The map of hotels to be filtered.
     * @param location   The location to filter by.
     * @param priceRange The price range to filter by.
     * @return List of filtered hotels.
     */
    private List<Hotel> filterHotels(final Map<String, Hotel> hotels, final String location,
      final List<Integer> priceRange) {
        return hotels.values().stream()
          .filter(hotel ->
            Objects.equals(hotel.getLocation().getName(), location)
              && hotel.getPrice() > priceRange.get(0) && hotel.getPrice() < priceRange.get(1))
          .collect(Collectors.toList());
    }

    /**
     * Calculates the total price for a hotel stay.
     *
     * @param price        The price per night.
     * @param checkInDate  The check-in date.
     * @param checkOutDate The check-out date.
     * @return The total price for the hotel stay.
     */
    private int calculateTotalPrice(final int price, final Date checkInDate,
      final Date checkOutDate) {
        return BookingUtils.getNumberOfDays(checkInDate, checkOutDate) * price;
    }

    /**
     * Returns a list of integer ratings from the reviews of a given hotel.
     *
     * @param hotel the Hotel object to extract the ratings from
     * @return a list of integer ratings
     */
    private List<Integer> getRatings(final Hotel hotel) {
        return hotel.getReviews().stream()
          .map(Review::getRating)
          .toList();
    }
}
