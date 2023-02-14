package com.adventuretube.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AdventureTubeDataRepository extends MongoRepository<AdventureTubeData,String > {
    Optional<AdventureTubeData>  findAdventureTubeDataByYoutubeContentID(String youtubeContentId);
}
