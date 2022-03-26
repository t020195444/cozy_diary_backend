package com.example.demo.dao;

import com.example.demo.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerDao extends JpaRepository<Follower,Integer> {

    public List<Follower> findAllByFollower1(String follower1);


}
