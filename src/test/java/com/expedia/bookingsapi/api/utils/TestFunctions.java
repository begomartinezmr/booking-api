package com.expedia.bookingsapi.api.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Provides utility methods for testing functionality.
 */
public final class TestFunctions {

    /**
     * Returns a new date instance representing the specified year, month, and day of the month.
     *
     * @param year       the year
     * @param month      the month
     * @param dayOfMonth the day of the month
     * @return a new date instance
     */
    public static Date getDate(final int year, final int month, final int dayOfMonth) {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, dayOfMonth);
        return calendar.getTime();
    }
}
