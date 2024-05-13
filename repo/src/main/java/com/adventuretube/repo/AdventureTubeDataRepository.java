package com.adventuretube.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.adventuretube.model.AdventureTubeData;

import java.util.List;
import java.util.Optional;

public interface AdventureTubeDataRepository extends MongoRepository<AdventureTubeData,String > {
    Optional<AdventureTubeData>  findAdventureTubeDataByYoutubeContentID(String youtubeContentId);
    List<AdventureTubeData> findAll();

}
