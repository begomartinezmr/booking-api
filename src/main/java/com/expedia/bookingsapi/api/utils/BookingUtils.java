package com.expedia.bookingsapi.api.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public final class BookingUtils {
    private BookingUtils() {
        throw new AssertionError("This class should not be instantiated.");
    }

    /**
     * Calculates the average of a list of integers.
     * @param ratings The list of integers to calculate the average of.
     * @return The average of the list of integers.
     */
    public static double calculateAverage(final List<Integer> ratings) {
        return ratings.stream()
          .mapToInt(Integer::intValue)
          .average()
          .orElse(0);

    }

    /**
     * Gets the number of days between two dates.
     * @param checkInDate The check-in date.
     * @param checkOutDate The check-out date.
     * @return The number of days between the check-in and check-out dates.
     */
    public static int getNumberOfDays(final Date checkInDate, final Date checkOutDate) {
        final LocalDate localCheckInDate = checkInDate.toInstant().atZone(ZoneId.systemDefault())
          .toLocalDate();
        final LocalDate localCheckOutDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault())
          .toLocalDate();
        return (int) ChronoUnit.DAYS.between(localCheckInDate, localCheckOutDate);
    }
}
