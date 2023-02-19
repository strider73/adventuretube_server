package com.adventuretube;

import com.adventuretube.model.AdventureTubeData;
import com.adventuretube.service.AdventureTubeDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/adventuretubedata")
@AllArgsConstructor
public class AdventureTubeDataController {

    private final AdventureTubeDataService adventureTubeDataService;

    @GetMapping
//    public  List<AdventureTubeData> fetchAllAdventureTubeDatas(){
//       return  adventureTubeDataService.getAdventuretubeDatasForArea();
//    }

    public void fetchAllAdventureTubeDatas() {
        adventureTubeDataService.getAdventuretubeDatasForArea();
    }

//    @GetMapping
//    public void fetchAdventureTubeDatas(){
//         adventureTubeDataService.getAdventuretubeDatasForArea();
//    }

    @PostMapping
    public ResponseEntity<Object> addAdventureTubeData(@RequestBody AdventureTubeData adventureTubeData) {
        adventureTubeDataService.addNewAdventureTubeData(adventureTubeData);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("message", "youtube upload successfully");
        body.put("contentId", adventureTubeData.getCoreDataID());
        body.put("contentTitle", adventureTubeData.getYoutubeTitle());
        return new ResponseEntity<>(body, HttpStatus.ACCEPTED);
    }
}
