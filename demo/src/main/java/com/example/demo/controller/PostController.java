package com.example.demo.controller;


import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.PostService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.PostVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    FileStorageService storageService;

    ObjectMapper objectMapper = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    public ResponseEntity<?> addPost(@RequestParam(required=true, value="file") MultipartFile[] file,@RequestParam(required=true, value="jsondata") String jsonData){
        try {
            PostRequest postRequest = new PostRequest();
            postRequest = objectMapper.readValue(jsonData, PostRequest.class);
            Optional<String> optional = postService.addPost(postRequest);
            try {
                storageService.save(file,optional.get(),postRequest.getPost().getUid());
            }catch (Exception e){
                System.out.println("file upload error:"+e);
            }
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/getPostCover", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostCoverByUid(@RequestParam String uid){
        try{
            List<PostResponse> post = postService.getPostCoverByUid(uid);
            return JsonResponse.generateResponse("獲取用戶貼文成功", HttpStatus.OK, post);
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/getPost", method = RequestMethod.GET)
    public ResponseEntity<Object> getPostByUid(@RequestParam String uid){
        try{
            List<Post> post = postService.getPostByUid(uid);
            return JsonResponse.generateResponse("獲取用戶貼文成功", HttpStatus.OK, post);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/updateLikes", method = RequestMethod.POST)
    public ResponseEntity<?> updateLikes(@RequestParam Integer pid, @RequestParam String uid){
        try {
            Optional<String> optional = postService.updatePostLikes(pid,uid);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK,"");
        }catch (Exception e){
            System.out.println(e);
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
