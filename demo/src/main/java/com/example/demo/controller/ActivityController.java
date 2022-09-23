package com.example.demo.controller;

import com.example.demo.dto.ActivityCoverResponse;
import com.example.demo.dto.ActivityRequest;
import com.example.demo.service.ActivityService;
import com.example.demo.service.FileStorageService;
import com.example.demo.util.JsonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    FileStorageService storageService;

    ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addActivity", method = RequestMethod.POST)
    public ResponseEntity<?> addActivity(@RequestParam(required=true, value="file") MultipartFile[] file, @RequestParam(required=true, value="jsondata") String jsonData){
        objectMapper.findAndRegisterModules();
        try {
            ActivityRequest activityRequest = new ActivityRequest();
            activityRequest = objectMapper.readValue(jsonData, ActivityRequest.class);
            Optional<String> optional = activityService.addActivity(activityRequest);
            try {
                storageService.saveActivity(file,optional.get(),activityRequest.getActivity().getHolder());
            }catch (Exception e){
                System.out.println("file upload error:"+e);
            }
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @CrossOrigin(origins = "http://172.20.10.10:8080")
    @RequestMapping(value = "/getActivityCover", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostCoverByUid(@RequestParam Integer acid){
        try{
            List<ActivityCoverResponse> activityCoverResponses = activityService.getActivityCoverByCategory(acid);
            return JsonResponse.generateResponse("獲取用戶活動成功", HttpStatus.OK, activityCoverResponses);
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

}
