package com.adventuretube;


import com.adventuretube.model.Restaurant;
import com.adventuretube.service.RestaurantsDataService;
import com.adventuretube.web.AdventureTubeProps;
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
public class RestaurantsDataController {
    private final RestaurantsDataService restaurantsDataService;
    private AdventureTubeProps props;
    public RestaurantsDataController(RestaurantsDataService restaurantsDataService,
                                     AdventureTubeProps props){
        this.restaurantsDataService = restaurantsDataService;
        this.props = props;
    }

//    @GetMapping
//    public ResponseEntity<List<Restaurant>>  getRestaurants(){
//        List<Restaurant> restaurants =  restaurantsDataService.getRestaurants();
//        System.out.println("count : "+restaurants.size());
//        for (Restaurant restaurant : restaurants) {
//            // Print restaurant information (you can customize the printing as needed)
//            System.out.println("==============All restaurants==========");
//            System.out.println("Restaurant Name: " + restaurant.getName());
//            System.out.println("Restaurant Address: " + restaurant.toString());
//            // Add more details as necessary
//        }
//        return new ResponseEntity<>(restaurants, HttpStatus.OK);
//    }



    @GetMapping
    public ResponseEntity<List<Restaurant>>  getRestaurants(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        Sort sort = Sort.by(Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(page,size);
        Page<Restaurant> restaurantPage =   restaurantsDataService.getRestaurants(pageable);

        for (Restaurant restaurant : restaurantPage.getContent()) {
            // Print restaurant information (you can customize the printing as needed)
            System.out.println("==============print page: "+page+" ==========");
            System.out.println("Restaurant Name: " + restaurant.getName());
            System.out.println("Restaurant Address: " + restaurant.toString());
            // Add more details as necessary
        }
        return new ResponseEntity<>(restaurantPage.getContent(), HttpStatus.OK);
    }



    @GetMapping("/near")
    public  ResponseEntity<List<Restaurant>> getRestaurntsByNear(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam(value = "maxDistance", required = false) Double maxDistance // Use Double instead of double
    ){
        maxDistance = (maxDistance == null ||maxDistance == 0) ? props.getMaxDistance() : maxDistance;
        double meterPerK = 1000;
        maxDistance = maxDistance * meterPerK;
        System.out.println("maxDistance is "+maxDistance);


        System.out.println("longitude : "+longitude);
        System.out.println("latitude  : "+latitude);
        System.out.println("pageCount is "+props.getPageSize());


        double[] current  = {longitude,latitude};
        List<Restaurant> nearbyRestaurants = restaurantsDataService.findRestaurantsNearLocation(current, maxDistance);
        for (Restaurant restaurant : nearbyRestaurants) {
            // Print restaurant information (you can customize the printing as needed)
            System.out.println("Test Restaurant Name: " + restaurant.getName());
            System.out.println("Test Restaurant Address: " + restaurant.toString());
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
