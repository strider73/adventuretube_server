package com.adventuretube.service;

import com.adventuretube.model.AdventureTubeData;
import com.adventuretube.repo.AdventureTubeDataRepository;
import com.adventuretube.service.error.YoutubeIdAlreadyExistException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.springframework.data.geo.Distance;

import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AdventureTubeDataService {
    private final AdventureTubeDataRepository adventureTubeDataRepository;
    private final MongoTemplate mongoTemplate ;
    public List<AdventureTubeData> getAllAdventureTubeDatas() {
        return adventureTubeDataRepository.findAll();
    }

    public void addNewAdventureTubeData(AdventureTubeData adventureTubeData) {
        Optional<AdventureTubeData> adventureTubeDataByYoutubId =
                adventureTubeDataRepository.findAdventureTubeDataByYoutubeContentID(adventureTubeData.getYoutubeContentID());
        if (adventureTubeDataByYoutubId.isPresent()) {
            throw new YoutubeIdAlreadyExistException(adventureTubeData.getYoutubeContentID(), adventureTubeData.getYoutubeTitle());
        }
        adventureTubeDataRepository.save(adventureTubeData);
        System.out.println(adventureTubeData);


    }

    //connection string to adventuretripvideo.com
    //mongodb://rootuser:rootpass@adventuretripvideo.com/adventuretube?authSource=admin
    public void getAdventuretubeDatasForArea() {

//        MongoClient mongoClient = MongoClients.create("mongodb://rootuser:rootpass@mongodb/adventuretube?authSource=admin");
//        MongoDatabase db = mongoClient.getDatabase("adventuretube");
//        MongoCollection<Document> collection = db.getCollection("adventureTubeData");
        System.out.println("using a mongo template");
        MongoCollection<Document> collection = mongoTemplate.getCollection("adventureTubeData");

        Point current = new Point(new Position(141.6041, -38.3));
        FindIterable<Document> result = collection.find(
                Filters.near("places.location", current, 100000.0, 10.0));

        Document doc = result.first();
        if (doc == null) {
            System.out.println("No results found.");
        } else {
            System.out.println(doc.toJson());
        }
    }


}
