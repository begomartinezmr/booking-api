package com.expedia.bookingsapi.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a review object.
 */
@Getter
@Setter
@AllArgsConstructor
public class Review {

    private String id;
    private int rating;
    private String comment;
}
