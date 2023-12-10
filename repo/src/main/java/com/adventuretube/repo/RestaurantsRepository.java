package com.adventuretube.repo;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.adventuretube.model.Restaurant;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface RestaurantsRepository extends MongoRepository <Restaurant,Long> {
    Page<Restaurant> findAll(Pageable pageable);

    @Query("{'location' : { $near : { $geometry : { type : 'Point', coordinates : ?0 }, $maxDistance : ?1 } } }")
    List<Restaurant>  findNearByLocation(Point location, double maxDistance );

//    @Query("{'location': { $geoWithin: { $box: [ ?0, ?1 ] } } }")
//    List<Restaurant> findByLocationWithinBox(double[] lowerLeft, double[] upperRight);
}

