package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    private UserService userService;

    @Override
    public User findUserByGoogleId(String gid) {
        return userDao.findUserByGoogleId(gid);
    }

    @Override
    public Optional<String> register(UserVO userVO){
        try {
            User data = userDao.findUserByGoogleId(userVO.getGoogle_id());

            if(data != null) return Optional.of("該帳號已註冊，進行登入");

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
            System.out.println("uid"+userVO.getGoogle_id());

            return Optional.of("註冊成功");
        }catch (Exception e){
            System.out.println(e);
            return Optional.of(e.toString());
        }

    }

    @Override
    public Optional<String> updateUser(UserVO userVO) {
        try {
            User user = userDao.findUserByGoogleId(userVO.getGoogle_id());
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
        }catch (Exception e){
            return Optional.of(e.toString());
        }
    }


}
