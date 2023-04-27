package com.expedia.bookingsapi.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class representing a hotel object.
 */
@Getter
@Setter
@Builder
public class Hotel {

    private String id;
    private String name;
    private Location location;
    private int price;
    private String image;
    private List<Review> reviews;
    private double averageReviewScore;
    private int totalPrice;

}
