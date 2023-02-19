package com.adventuretube.repo;

import com.adventuretube.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place,String> {
}
