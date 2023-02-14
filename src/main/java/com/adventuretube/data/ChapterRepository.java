package com.adventuretube.data;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterRepository  extends MongoRepository<Chapter,String> {
}
