package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.vo.CategoryVO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public List<Category> getCategoryById(Integer cid);

    public Optional<String> addCategory(CategoryVO categoryVO);

}
