package com.adventuretube;


import com.adventuretube.service.RestaurantsDataService;
import lombok.AllArgsConstructor;
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

    int page = 1; // Page number, 0-based index (first page is 0)
    int size = 10; // Number of items per page
    Sort sort = Sort.by(Sort.Order.asc("fieldName")); // Replace "fieldName" with the field you want to sort by
    Pageable pageable = PageRequest.of(page, size, sort);



    @GetMapping
    public void getRestaurants(){
        Sort sort = Sort.by(Sort.Order.asc("name"));
        Pageable pageable = PageRequest.of(page,size);
        restaurantsDataService.getRestaurants(pageable);
    }
}
