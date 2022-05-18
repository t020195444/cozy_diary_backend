package com.example.demo.service;

import com.example.demo.entity.Follower;
import com.example.demo.vo.FollowerVO;

import java.util.List;
import java.util.Optional;

public interface FollowerService {

    public List<Follower> findAllByFollwer1(String follower1);

    public Optional<String> addFollower(FollowerVO followerVO);

    public Optional<String> deleteFollower(String follower1 , String follower2);

}
