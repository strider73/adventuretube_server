package com.adventuretube.repo;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.adventuretube.model.Restaurant;
import org.springframework.data.domain.Page;

import  org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface RestaurantsRepository extends MongoRepository <Restaurant,String> {

//    List<Restaurant> findAll();

    //@Query("{'location' : { $near : { $geometry : { type : 'Point', coordinates : [ ?0, ?1 ]}, $maxDistance : ?1 } } }")
    @Query( """
            {location:
                  {$near :
                     {$geometry: { type:  "Point",  coordinates: ?0 }, $minDistance: 1000,$maxDistance: ?1}
                   }
            }
            """)
    List<Restaurant> findNearByLocation(double[] point , double maxDistance);


    //@Query("{'location': { $geoWithin: { $box: [ ?0, ?1 ] } } }")
    @Query("""
            {location:
               {$geoWithin:
                     { $box:[?0,?1]}
                }
            }
            """)
    List<Restaurant> findByLocationWithinBox(double[] lowerLeft, double[] upperRight);
}

