package com.expedia.bookingsapi.api.utils;

import com.expedia.bookingsapi.api.utils.BookingUtils;
import java.util.Calendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingUtilsTest {


    @ParameterizedTest
    @MethodSource("averageProvider")
    public void shouldReturnCalculateAverage(List<Integer> numbers, double expectedAverage) {
        double actualAverage = BookingUtils.calculateAverage(numbers);
        Assertions.assertEquals(expectedAverage, actualAverage);
    }

    static List<Object[]> averageProvider() {
        return Arrays.asList(new Object[][]{
          {Arrays.asList(1, 2, 3, 4, 5), 3.0},
          {Arrays.asList(0, 0, 0), 0.0},
          {Arrays.asList(-1, 1), 0.0}
        });
    }

    @ParameterizedTest
    @MethodSource("daysDataProvider")
    public void shouldReturnNumberOfDays(Date checkInDate, Date checkOutDate, int expectedDays) {
        int actualDays = BookingUtils.getNumberOfDays(checkInDate, checkOutDate);
        Assertions.assertEquals(expectedDays, actualDays);
    }

    static List<Object[]> daysDataProvider() {
        return Arrays.asList(new Object[][]{
          {TestFunctions.getDate(2023, Calendar.MAY, 5),
            TestFunctions.getDate(2023, Calendar.MAY, 6), 1},
          {TestFunctions.getDate(2023, Calendar.MAY, 5),
            TestFunctions.getDate(2023, Calendar.MAY, 5), 0},
          {null, null, 0},
          {TestFunctions.getDate(2023, Calendar.MAY, 5), null, 0}
        });
    }
}