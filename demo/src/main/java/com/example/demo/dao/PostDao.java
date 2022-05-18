package com.example.demo.dao;

import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Follower;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

    public List<Post> findPostByUid(String uid);

    @Query(value = "SELECT new com.example.demo.dto.PostResponse(p.uid as uid, p.cover as cover , p.likes as likes , p.title as title, u.cid as cid) FROM UserCategory u  INNER JOIN Post p on u.cid = p.cid WHERE u.uid = :uid ")
    public List<PostResponse> findPostCoverByUid(String uid);
}
