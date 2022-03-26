package com.example.demo.service;

import com.example.demo.entity.Follower;

import java.util.List;

public interface FollowerService {

    public List<Follower> findAllByFollwer1(String follower1);
}
