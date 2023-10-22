package com.adventuretube.service;

import com.adventuretube.model.AdventureTubeData;
import com.adventuretube.repo.AdventureTubeDataRepository;
import com.adventuretube.service.error.YoutubeIdAlreadyExistException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import lombok.AllArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Service
public class AdventureTubeDataService {
    private final AdventureTubeDataRepository adventureTubeDataRepository;

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

    public void getAdventuretubeDatasForArea() {
        MongoClient mongoClient = MongoClients.create("mongodb://rootuser:rootpass@192.168.1.100/adventuretube?authSource=admin");
        MongoDatabase db = mongoClient.getDatabase("adventuretube");
        MongoCollection<Document> collection = db.getCollection("adventureTubeData");

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
