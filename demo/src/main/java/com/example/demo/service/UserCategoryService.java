package com.example.demo.service;

import com.example.demo.entity.UserCategory;
import com.example.demo.vo.UserCategoryVO;

import java.util.List;
import java.util.Optional;

public interface UserCategoryService {

    public List<UserCategory> getUserCategoryListByUid(String uid);

    public Optional<String> addUserCategory(UserCategoryVO userCategoryVO);
}
