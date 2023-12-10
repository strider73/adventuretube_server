package com.adventuretube;


import com.adventuretube.model.Restaurant;
import com.adventuretube.service.RestaurantsDataService;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurants")
@AllArgsConstructor
public class RestaurantsDataController {
    private final RestaurantsDataService restaurantsDataService;





    @GetMapping
    public ResponseEntity<List<Restaurant>>  getRestaurants(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){

//        int page = 1; // Page number, 0-based index (first page is 0)
//        int size = 10; // Number of items per page
        Sort sort = Sort.by(Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(page,size);
        Page<Restaurant> restaurantPage =   restaurantsDataService.getRestaurants(pageable);
        for (Restaurant restaurant : restaurantPage.getContent()) {
            System.out.println("==============BellaMun==========");
            System.out.println(restaurant.getName());
            System.out.println(restaurant.getLocation());
            System.out.println(restaurant.getLocation().getCoordinates());
        }
        return new ResponseEntity<>(restaurantPage.getContent(), HttpStatus.OK);
    }



    @GetMapping("/near")
    public  ResponseEntity<List<Restaurant>> getRestaurntsByNear(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("maxDistance")  double maxDistance
    ){
        double meterPerMile = 1609.34;
        System.out.println("longitude : "+longitude);
        System.out.println("latitude  : "+latitude);

        Point current = new Point(new Position(longitude, latitude));
//      Point current = new Point(new Position(-73.93414657, 40.82302903));
        List<Restaurant> nearbyRestaurants = restaurantsDataService.findRestaurantsNearLocation(current, maxDistance * meterPerMile);
        for (Restaurant restaurant : nearbyRestaurants) {
            // Print restaurant information (you can customize the printing as needed)
            System.out.println("Restaurant Name: " + restaurant.getName());
            System.out.println("Restaurant Address: " + restaurant.toString());
            // Add more details as necessary
        }
        return new ResponseEntity<>(nearbyRestaurants, HttpStatus.OK);

    }

    //https://www.codecentric.de/wissens-hub/blog/spring-data-mongodb-geospatial-queries
    /*
       db.location.find( {position: { $near: [0,0], $maxDistance: 0.75  } } ) ==> Circle

      db.location.find( {position: { $within: { $box: [ [0.25, 0.25], [1.0,1.0] ] }  } } ) ==> Box

     */
    @GetMapping("/locations-in-bounding-box")
    public  ResponseEntity<List<Restaurant>> getLocationsInBoundingBox(
            @RequestParam double swLon,
            @RequestParam double swLat,
            @RequestParam double neLon,
            @RequestParam double neLat) {

         double[] lowerLeft  = {swLon,swLat};
         double[] upperRight = {neLon,neLat};
        List<Restaurant> restaurants = restaurantsDataService.findRestaurantsWithinBox(lowerLeft,upperRight);
        for (Restaurant restaurant : restaurants) {
            // Print restaurant information (you can customize the printing as needed)
            System.out.println("Restaurant Name: " + restaurant.getName());
            System.out.println("Restaurant Address: " + restaurant.toString());
            // Add more details as necessary
        }
        return new ResponseEntity<>(restaurants, HttpStatus.OK);

    }

}
