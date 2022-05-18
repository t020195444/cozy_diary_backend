package com.example.demo.service.impl;


import com.example.demo.dao.FollowerDao;
import com.example.demo.entity.Follower;
import com.example.demo.service.FollowerService;
import com.example.demo.vo.FollowerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FollowerServiceImpl implements FollowerService {
    @Autowired
    private FollowerDao followerDao;

    @Override
    public List<Follower> findAllByFollwer1(String follower1) {
        return followerDao.findAllByFollower1(follower1);
    }

    @Override
    public Optional<String> addFollower(FollowerVO followerVO) {
        try {
            Follower follower = new Follower();
            follower.setFollower1(followerVO.getFollower1());
            follower.setFollower2(followerVO.getFollower2());
            follower.setFollow_time(LocalDateTime.now());
            followerDao.save(follower);

            return Optional.of("新增粉絲成功");
        }catch (Exception e){
            return Optional.of("新增粉絲發生以下錯誤："+e);
        }
    }

    @Override
    public Optional<String> deleteFollower(String follower1, String follower2) {
        try {
            followerDao.deleteFollowerById(follower1,follower2);
            return Optional.of("刪除粉絲成功");
        }catch (Exception e){
            return Optional.of("刪除粉絲發生以下錯誤："+e);
        }
    }
}
