package com.example.demo.service.impl;


import com.example.demo.dao.UserCategoryDao;
import com.example.demo.entity.UserCategory;
import com.example.demo.service.UserCategoryService;
import com.example.demo.vo.UserCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {
    @Autowired
    private UserCategoryDao userCategoryDao;

    @Override
    public List<UserCategory> getUserCategoryListByUid(String uid) {
        return userCategoryDao.findAllByUid(uid);
    }

    @Override
    public Optional<String> addUserCategory(UserCategoryVO userCategoryVO) {
        try {
            UserCategory userCategory = new UserCategory();
            userCategory.setCid(userCategoryVO.getCid());
            userCategory.setUid(userCategoryVO.getUid());
            userCategoryDao.save(userCategory);
            return Optional.of("新增使用者喜好類別成功");
        } catch (Exception e){
            return Optional.of("新增使用者洗好類別時發生以下錯誤："+e.toString());
        }
    }
}
