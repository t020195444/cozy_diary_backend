package com.example.demo.controller;

import com.example.demo.dao.FollowerDao;
import com.example.demo.entity.Follower;
import com.example.demo.service.FollowerService;
import com.example.demo.util.JsonResponse;
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

    @RequestMapping(value = "/addFolower", method = RequestMethod.POST)
    public ResponseEntity<?> addFollower(@RequestBody FollowerVO followerVO){
        try{
            Optional<String> optional = followerService.addFollower(followerVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @RequestMapping(value = "/followList", method = RequestMethod.GET)
    public ResponseEntity<Object> getFollowList(@RequestParam String follower1){
        try {
            List<Follower> followerList = followerService.findAllByFollwer1(follower1);
            return JsonResponse.generateResponse("查詢追蹤名單成功",HttpStatus.OK,followerList);
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
