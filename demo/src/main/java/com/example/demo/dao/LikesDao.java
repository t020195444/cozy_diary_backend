package com.example.demo.dao;


import com.example.demo.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesDao extends JpaRepository<Likes,Integer> {
    public List<Likes> getLikesListByPid(Integer pid);

    public Likes getLikesByPidAndUid(Integer pid, String uid);
}
