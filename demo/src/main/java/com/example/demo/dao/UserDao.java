package com.example.demo.dao;

import com.example.demo.dto.FollowerResponse;
import com.example.demo.dto.TrackerResponse;
import com.example.demo.entity.Follower;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

      public User findUserByGoogleId(String gid);



//    @Query("SELECT * FROM user u INNER JOIN tracker t ON u.google_id = t.tracker1 WHERE u.google_id = :gid")
//    public List<TrackerResponse> findTrackListByGoogleId(String gid);
//
//    @Query("SELECT * FROM user u INNER JOIN follower f ON u.google_id = f.follower1 WHERE u.google_id = :fid")
//    public List<FollowerResponse> findFollowListByGoogleId(String fid);
}
