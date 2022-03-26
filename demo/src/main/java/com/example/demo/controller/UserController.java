package com.example.demo.controller;


import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.entity.Follower;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ResponseEntity<?> doRegister(@RequestBody UserVO userVO){
        try{
            System.out.println(userVO.getGoogle_id());
            Optional<String> optional = userService.register(userVO);
            return ResponseEntity.ok().body(optional);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByGid(@RequestParam String gid){
        User user = userService.findUserByGoogleId(gid);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserVO userVO){
        try{
            Optional<String> optional = userService.updateUser(userVO);
            return ResponseEntity.ok().body(optional);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @RequestMapping(value = "/user/{id}/tracker", method = RequestMethod.GET)
//    public ResponseEntity getTrackByUid(@PathVariable String id){
//        List<TrackerResponse> tracker = userService.getTrackListByUid(id);
//        return ResponseEntity.status(HttpStatus.OK).body(tracker);
//    }
//
//    @RequestMapping(value = "/user/{id}/follower", method = RequestMethod.GET)
//    public ResponseEntity getFollowByUid(@PathVariable String id){
//        List<FollowerResponse> tracker = userService.getFollowListByUid(id);
//        return ResponseEntity.status(HttpStatus.OK).body(tracker);
//    }

}
