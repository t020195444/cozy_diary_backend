package com.example.demo.service.impl;


import com.example.demo.dao.FollowerDao;
import com.example.demo.entity.Follower;
import com.example.demo.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {
    @Autowired
    private FollowerDao followerDao;

    @Override
    public List<Follower> findAllByFollwer1(String follower1) {
        return followerDao.findAllByFollower1(follower1);
    }
}
