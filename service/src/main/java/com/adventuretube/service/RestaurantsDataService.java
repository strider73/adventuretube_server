package com.adventuretube.service;


import com.adventuretube.model.Restaurants;
import com.adventuretube.repo.RestaurantsRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.Filters;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantsDataService {
    private final RestaurantsRepository restaurantsRepository;
    private final MongoTemplate mongoTemplate ;

//    @Autowired
//    public RestaurantsDataService(RestaurantsRepository restaurantsRepository){
//        this.restaurantsRepository = restaurantsRepository;
//    }

    ///getRestaurants?page=0&size=10
    public Page<Restaurants> getRestaurants(Pageable pageable){
        return restaurantsRepository.findAll(pageable);
    }


//    public List<Restaurants> findRestaurantsNearLocation(Point location,double maxDistance){
//        return restaurantsRepository.findNearByLocation(location,maxDistance);
//    }

    public List<Restaurants> findRestaurantsNearLocation(Point location, double maxDistance){
        MongoCollection<Document> collection = mongoTemplate.getCollection("restaurants");
        FindIterable<Document> result = collection.find(Filters.near("location",location,maxDistance, 10.0));
        List<Restaurants> restaurantsList = new ArrayList<>();

        for (Document document : result) {
            Restaurants restaurant = mongoTemplate.getConverter().read(Restaurants.class, document);
            restaurantsList.add(restaurant);
        }


        return restaurantsList;
    }
}
