package com.adventuretube;


import com.adventuretube.model.Restaurants;
import com.adventuretube.service.RestaurantsDataService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            System.out.println("==============YehwasnLeeeXXXXX==========");
            System.out.println(restaurant.getName());
            System.out.println(restaurant.getLocation());
            System.out.println(restaurant.getLocation().getCoordinates());
        }
    }
}
