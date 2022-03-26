package com.example.demo.service;

import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.vo.UserVO;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User findUserByGoogleId(String gid);

    public Optional<String> register(UserVO userVO);

    public Optional<String> updateUser(UserVO userVO);
}
