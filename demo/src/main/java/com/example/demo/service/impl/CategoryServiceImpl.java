package com.example.demo.service.impl;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryById(Integer cid) {
        return categoryDao.findAllByCid(cid);
    }

    @Override
    public Optional<String> addCategory(CategoryVO categoryVO) {
        try {
            Category category = new Category();
            category.setCategory(categoryVO.getCategory());
            categoryDao.save(category);
            return Optional.of("新增貼文類別成功");
        }catch (Exception e){
            return Optional.of("新增貼文類別時發生以下錯誤："+e.toString());
        }
    }
}
