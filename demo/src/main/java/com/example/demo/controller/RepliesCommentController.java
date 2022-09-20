package com.example.demo.controller;

import com.example.demo.service.RepliesCommentService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.CommentsVO;
import com.example.demo.vo.RepliesCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class RepliesCommentController {
    @Autowired
    private RepliesCommentService repliesCommentService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addRepliesComment", method = RequestMethod.POST)
    public ResponseEntity<?> addRepliesComment(@RequestBody RepliesCommentVO repliesCommentVO) {
        try {
            Optional<String> optional = repliesCommentService.addRepliesComment(repliesCommentVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }


}
