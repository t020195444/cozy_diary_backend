package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.entity.Follower;
import com.example.demo.service.CategoryService;
import com.example.demo.util.JsonResponse;
import com.example.demo.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = "http://localhost:10001")
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@RequestBody CategoryVO categoryVO){
        try {
            Optional<String> optional = categoryService.addCategory(categoryVO);
            return JsonResponse.generateResponse(optional.get(), HttpStatus.OK, "");
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }

    @RequestMapping(value = "/categoryList", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategoryList(@RequestParam Integer cid){
        try {
            List<Category> categoryList = categoryService.getCategoryById(cid);
            return JsonResponse.generateResponse("查詢貼文類別成功",HttpStatus.OK,categoryList);
        }catch (Exception e){
            return JsonResponse.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
    }
}
