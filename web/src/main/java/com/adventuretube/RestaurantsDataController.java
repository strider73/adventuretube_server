package com.adventuretube;


import com.adventuretube.model.Restaurants;
import com.adventuretube.service.RestaurantsDataService;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.AllArgsConstructor;
import lombok.Synchronized;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

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
    public void getRestaurants(){

        int page = 1; // Page number, 0-based index (first page is 0)
        int size = 10; // Number of items per page
        Sort sort = Sort.by(Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(page,size);
        Page<Restaurants> restaurantPage =   restaurantsDataService.getRestaurants(pageable);
        for (Restaurants restaurant : restaurantPage.getContent()) {
            System.out.println("==============BellaMun==========");
            System.out.println(restaurant.getName());
            System.out.println(restaurant.getLocation());
            System.out.println(restaurant.getLocation().getCoordinates());
        }
    }



    @GetMapping("/near")
    public  void getRestaurntsByNear(
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude,
            @RequestParam("maxDistance")  double maxDistance
    ){
        double meterPerMile = 1609.34;
        System.out.println("longitude : "+longitude);
        System.out.println("latitude  : "+latitude);

        Point current = new Point(new Position(longitude, latitude));
//      Point current = new Point(new Position(-73.93414657, 40.82302903));
        List<Restaurants> nearbyRestaurants = restaurantsDataService.findRestaurantsNearLocation(current, maxDistance * meterPerMile);

        for (Restaurants restaurants : nearbyRestaurants) {
            // Print restaurant information (you can customize the printing as needed)
            System.out.println("Restaurant Name: " + restaurants.getName());
            System.out.println("Restaurant Address: " + restaurants.toString());
            // Add more details as necessary
        }
    }

}
