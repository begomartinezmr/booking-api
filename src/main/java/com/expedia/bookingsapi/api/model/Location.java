package com.expedia.bookingsapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class representing a location object.
 */
@Getter
@AllArgsConstructor
public class Location {

    private String id;
    private String name;
}
