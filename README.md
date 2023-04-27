# Bookings API

## Installation

1. Clone the repository
2. Run `mvn clean install` in the project directory to build the project and generate the jar file
3. Run java -jar target/bookings-api-1.0-SNAPSHOT.jar to start the application

## Usage

The application will run on http://localhost:8080 by default.

To search for hotels, send a GraphQL query to the API. Here's an example query:
 ``` json
 query {
  searchHotels(
    location: "London",
     checkInDate: "05/01/2023"
     checkOutDate: "05/02/2023"
    priceRange: [50, 500]
  ) {
    id
    name
    location {
      id
      name
    }
    image
    reviews {
      id
      rating
      comment
    }
    totalPrice
  }
}

 ```
This query searches for hotels in London with check-in date May 1, 2023 and check-out date May 5, 2023, 
and with prices between $50 and $500 per night. The response includes the hotel ID, name, location,
image URL, reviews, and total price for the stay. The list is sorted by the average review (highest first)

### Possible Exceptions

`IllegalArgumentException` - if the check-in date is after the check-out date.
