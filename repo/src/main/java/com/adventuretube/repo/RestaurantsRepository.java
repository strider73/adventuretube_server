package com.adventuretube.repo;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.adventuretube.model.Restaurants;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface RestaurantsRepository extends MongoRepository <Restaurants,Long> {
    Page<Restaurants> findAll(Pageable pageable);

    @Query("{'location' : { $near : { $geometry : { type : 'Point', coordinates : ?0 }, $maxDistance : ?1 } } }")
    List<Restaurants>  findNearByLocation(Point location, double maxDistance );

}