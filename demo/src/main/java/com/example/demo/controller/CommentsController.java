package com.example.demo.controller;


import com.example.demo.dto.PostRequest;
import com.example.demo.service.CommentsService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.CommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public ResponseEntity<?> addComment(@RequestBody CommentsVO commentsVO){
        try {
            Optional<String> optional = commentsService.addComments(commentsVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    public ResponseEntity<?> deleteComment(@RequestParam Integer cid){
        try {
            Optional<String> optional = commentsService.deleteComment(cid);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public ResponseEntity<?> updateComment(@RequestParam Integer cid, @RequestParam String text){
        try {
            Optional<String> optional = commentsService.updateComment(cid, text);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
