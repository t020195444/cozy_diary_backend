package com.example.demo.controller;

import com.example.demo.service.TrackerService;
import com.example.demo.vo.TrackerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class TrackerController {

    @Autowired
    private TrackerService trackerService;

    @RequestMapping(value = "/addTracker", method = RequestMethod.POST)
    public ResponseEntity<?> addTracker(@RequestBody TrackerVO trackerVO){
        try{
            System.out.println("in controller");
            Optional<String> optional = trackerService.addTracker(trackerVO);
            return ResponseEntity.ok().body("regist successful");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }    }
}
