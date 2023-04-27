package com.expedia.bookingsapi.api.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestConstants {
    public static final Date CHECK_IN_DATE_DEFAULT = TestFunctions.getDate(2023, Calendar.MAY, 5);
    public static final Date CHECK_OUT_DATE_DEFAULT = TestFunctions.getDate(2023, Calendar.MAY, 6);
    public static final List<Integer> PRICE_RANGE_DEFAULT = List.of(50, 100);
    public static final String LOCATION_DEFAULT = "London";
}
