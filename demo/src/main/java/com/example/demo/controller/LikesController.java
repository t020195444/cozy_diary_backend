package com.example.demo.controller;


import com.example.demo.entity.Likes;
import com.example.demo.service.LikesService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.LikesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class LikesController {
    @Autowired
    private LikesService likesService;

    @RequestMapping(value = "/addLikes", method = RequestMethod.POST)
    public ResponseEntity<?> addLikes(@RequestBody LikesVO likesVO){
        try{
            Optional<String> optional = likesService.addLikes(likesVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @RequestMapping(value = "/likesList", method = RequestMethod.GET)
    public ResponseEntity<Object> getLikesList(@RequestParam Integer pid){
        try {
            List<Likes> likesList = likesService.getLikesListByPid(pid);
            return JsonResponse.generateResponse("查詢貼文按讚列表成功",HttpStatus.OK,likesList);
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @RequestMapping(value = "/deleteLikes", method = RequestMethod.POST)
    public  ResponseEntity<?> deleteLikes(@RequestBody LikesVO likesVO){
        try {
            Optional<String> optional = likesService.deleteLikes(likesVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
