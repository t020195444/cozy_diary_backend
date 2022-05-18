package com.example.demo.controller;



import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public ResponseEntity<?> doRegister(@RequestBody UserVO userVO) {
        try {
            Optional<String> optional = userService.register(userVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        } catch (Exception e) {
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<Object> getUserByGid(@RequestParam String gid) {
        try {
            User user = userService.findUserByGoogleId(gid);
            return JsonResponse.generateResponse("獲取用戶基本資料成功", HttpStatus.OK, user);
        } catch (Exception e) {
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserVO userVO) {
        try {
            Optional<String> optional = userService.updateUser(userVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        } catch (Exception e) {
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
