package com.adventuretube.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.adventuretube.model.Restaurants;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;

public interface RestaurantsRepository extends MongoRepository <Restaurants,Long> {
    Page<Restaurants> findAll(Pageable pageable);
}