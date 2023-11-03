package com.adventuretube.service;


import com.adventuretube.model.Restaurants;
import com.adventuretube.repo.RestaurantsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class RestaurantsDataService {
    private final RestaurantsRepository restaurantsRepository;
    @Autowired
    public RestaurantsDataService(RestaurantsRepository restaurantsRepository){
        this.restaurantsRepository = restaurantsRepository;
    }

    ///getRestaurants?page=0&size=10
    public Page<Restaurants> getRestaurants(Pageable pageable){
        return restaurantsRepository.findAll(pageable);
    }
}
