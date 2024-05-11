package com.adventuretube.service;


import com.adventuretube.model.Restaurant;
import com.adventuretube.repo.RestaurantsRepository;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.Filters;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantsDataService {

    private final RestaurantsRepository restaurantsRepository;
    private final MongoTemplate mongoTemplate ;


    ///getRestaurants?page=0&size=10
//    public Page<Restaurant> getRestaurants(Pageable pageable){
//        return restaurantsRepository.findAll(pageable);
//    }
    public Page<Restaurant> getRestaurants(Pageable pageable){
        return restaurantsRepository.findAll(pageable);
    }


    public List<Restaurant> getRestaurants(){
        System.out.println("restaurant count is :"+restaurantsRepository.count());
        return restaurantsRepository.findAll();
    }

    public List<Restaurant> findRestaurantsNearLocation(double[] location,double maxDistance){
        System.out.println("findRestaurantsNearLocation with MongoRepository");

        return restaurantsRepository.findNearByLocation(location,maxDistance);
    }

    public List<Restaurant> findRestaurantsWithinBox(double[] lowerLeft, double[] upperRight) {
        System.out.println("findRestaurantsWithinBox with MongoRepository");
        System.out.println("lowerLeft :["+lowerLeft[0]+","+lowerLeft[1]);
        System.out.println("upperRight:["+upperRight[0]+","+upperRight[1]);
        return restaurantsRepository.findByLocationWithinBox(lowerLeft, upperRight);
    }

//    public List<Restaurant> findRestaurantsNearLocation(Point location, double maxDistance){
//        MongoCollection<Document> collection = mongoTemplate.getCollection("restaurants");
//        FindIterable<Document> result = collection.find(Filters.near("location",location,maxDistance, 10.0));
//        List<Restaurant> restaurantList = new ArrayList<>();
//
//        for (Document document : result) {
//            Restaurant restaurant = mongoTemplate.getConverter().read(Restaurant.class, document);
//            restaurantList.add(restaurant);
//        }
//
//
//        return restaurantList;
//    }

//    public List<Restaurant> findRestaurantsWithinBox(double[] lowerLeft, double[] upperRight) {
//        List<Restaurant> restaurantList = new ArrayList<>();
//
//        MongoCollection<Document> collection = mongoTemplate.getCollection("restaurants");
//        FindIterable<Document> result = collection.find(Filters.geoWithinBox("location",lowerLeft[0],lowerLeft[1],upperRight[0],upperRight[1]));
//        for (Document document : result) {
//            Restaurant restaurant = mongoTemplate.getConverter().read(Restaurant.class, document);
//            restaurantList.add(restaurant);
//        }
//        return restaurantList;
//    }


//    public List<Restaurant> findRestaurantsInBoxLocation(double  swLon,double   swLat,double   neLon, double  neLat){
//        List<Restaurant> restaurantList = new ArrayList<>();
//        Box boundingBox = new Box(new GeoJsonPoint(swLon, swLat), new GeoJsonPoint(neLon, neLat));
//        System.out.println("the box is  :"+boundingBox.toString());
//        restaurantList = restaurantsRepository.findRestaurantsByLocationWithin(boundingBox);
//
//        return restaurantList;
//
//
//    }


//    public List<Restaurant> findRestaurantsInBoxLocation(double nwLat, double nwLon, double seLat, double seLon){
//        Box boundingBox = new Box(new GeoJsonPoint(nwLon, nwLat), new GeoJsonPoint(seLon, seLat));
//        List<Restaurant> restaurantList = mongoTemplate.find(
//                query(where("location").within(boundingBox)),
//                Restaurant.class
//        );
//        return restaurantList;
//
//
//    }
}
