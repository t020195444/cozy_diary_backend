package com.example.demo.controller;

import com.example.demo.dao.FollowerDao;
import com.example.demo.entity.Follower;
import com.example.demo.service.FollowerService;
import com.example.demo.vo.FollowerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class FollowerController {

    @Autowired
    private FollowerService followerService;

//    @RequestMapping(value = "/addFolower", method = RequestMethod.POST)
//    public ResponseEntity<?> addFollower(@RequestBody Follower follower){
//        try{
//            System.out.println("in controller");
//            Follower optional = followerDao.save(follower);
//            return ResponseEntity.ok().body("regist successful");
//        }catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
//
//        }
//    }

    @RequestMapping(value = "/followList", method = RequestMethod.GET)
    public ResponseEntity<List<Follower>> getFollowList(@RequestParam String follower1){
       return new ResponseEntity<List<Follower>>(followerService.findAllByFollwer1(follower1), HttpStatus.OK);
    }
}
