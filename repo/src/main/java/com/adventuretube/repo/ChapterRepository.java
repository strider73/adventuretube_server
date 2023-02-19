package com.adventuretube.repo;

import com.adventuretube.model.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChapterRepository  extends MongoRepository<Chapter,String> {
}
