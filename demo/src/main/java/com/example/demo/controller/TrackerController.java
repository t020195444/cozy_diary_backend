package com.example.demo.controller;

import com.example.demo.service.TrackerService;
import com.example.demo.vo.TrackerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addTracker", method = RequestMethod.POST)
    public ResponseEntity<?> addTracker(@RequestBody TrackerVO trackerVO){
        try{
            Optional<String> optional = trackerService.addTracker(trackerVO);
            return ResponseEntity.ok().body(optional);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/deleteTracker", method = RequestMethod.POST)
    public ResponseEntity<?> deleteTracker(@RequestParam String tracker1,String tracker2){
        try{
            Optional<String> optional = trackerService.deleteTrack(tracker1,tracker2);
            return ResponseEntity.ok().body(optional);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }    }
}
