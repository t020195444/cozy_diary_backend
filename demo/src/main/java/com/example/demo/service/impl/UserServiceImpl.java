package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.dto.UserRegisterRequest;
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
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    public Optional<String> register(UserRegisterRequest userRegisterRequest) {
        User data = userDao.findUserByGoogleId(userRegisterRequest.getUser().getGoogleId());
        String basic = "/Users/yangzhelun/Desktop/development/uploadFile";
        String dbFile = "http://172.20.10.10:8080/staticFile/";

//        String basic = "/root/uploadFile";
//        String dbFile = "https://140.131.114.166:80/staticFile/";

        if (data != null) {
            return Optional.of("該帳號已註冊");
        }
        try {
            dbFile = dbFile + userRegisterRequest.getUser().getGoogleId() + "/userProfile";
            User user = new User();
            LocalDate now = LocalDate.now();
            LocalDate birth = LocalDate.parse(userRegisterRequest.getUser().getBirth().toString());
            Long diff = birth.until(now, ChronoUnit.YEARS);
            System.out.println(diff);
            user.setGoogleId(userRegisterRequest.getUser().getGoogleId());
            user.setName(userRegisterRequest.getUser().getName());
            user.setSex(userRegisterRequest.getUser().getSex());
            user.setEmail(userRegisterRequest.getUser().getEmail());
            user.setAge(Math.toIntExact(diff));
            user.setBirth(LocalDate.parse(userRegisterRequest.getUser().getBirth().toString()));
            user.setCreate_time(LocalDateTime.now());
            user.setIntroduction(userRegisterRequest.getUser().getIntroduction());
            File userFile = new File(basic + "/" + userRegisterRequest.getUser().getGoogleId());
            File userProfile = new File(userFile.getPath()+ "/userProfile");
            File postFile = new File(userFile.getPath()+ "/postFile");
            File activityFile = new File(userFile.getPath()+ "/activityFile");
            user.setPic(dbFile+"/"+userRegisterRequest.getUser().getPic());
            userDao.save(user);
            if ( !userFile.exists()){
                userFile.mkdir();
                userProfile.mkdir();
                postFile.mkdir();
                activityFile.mkdir();
            }
            return Optional.of("註冊成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return Optional.of(e.toString());
        }

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
