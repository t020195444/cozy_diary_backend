package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateCreateException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private UserService userService;

    @Override
    public User findUserByGoogleId(String gid) {
        User user = userDao.findUserByGoogleId(gid);
        if(user != null){
            return userDao.findUserByGoogleId(gid);
        } else {
            throw new NotFoundException("查無該用戶");
        }
    }

    @Override
    public Optional<String> register(UserVO userVO) {
        User data = userDao.findUserByGoogleId(userVO.getGoogle_id());
        String basic = "/Users/yangzhelun/Desktop/development/uploadFile";

        if (data != null) {
            return Optional.of("該帳號已註冊");
        }

        User user = new User();
        user.setGoogleId(userVO.getGoogle_id());
        user.setAge(userVO.getAge());
        user.setName(userVO.getName());
        user.setSex(userVO.getSex());
        user.setEmail(userVO.getEmail());
        user.setBirth(LocalDate.parse(userVO.getBirth()));
        user.setCreate_time(LocalDateTime.now());
        user.setIntroduction(userVO.getIntroduction());
        user.setPic(userVO.getPic());
        userDao.save(user);
        File userFile = new File(basic + "/" + userVO.getGoogle_id());
        if ( !userFile.exists()){
            userFile.mkdir();
        }
        System.out.println("uid" + userVO.getGoogle_id());


        return Optional.of("註冊成功");

    }

    @Override
    public Optional<String> updateUser(UserVO userVO) {
        User user = userDao.findUserByGoogleId(userVO.getGoogle_id());
        if (user != null) {
            user.setAge(userVO.getAge());
            user.setName(userVO.getName());
            user.setSex(userVO.getSex());
            user.setEmail(userVO.getEmail());
            user.setBirth(LocalDate.parse(userVO.getBirth()));
            user.setCreate_time(LocalDateTime.now());
            user.setIntroduction(userVO.getIntroduction());
            user.setPic(userVO.getPic());
            userDao.save(user);
            return Optional.of("更新成功");
        } else {
            throw new DuplicateCreateException("找不到該用戶");
        }
    }


}
