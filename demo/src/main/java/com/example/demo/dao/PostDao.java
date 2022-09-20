package com.example.demo.dao;

import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Follower;
import com.example.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

    public List<Post> findPostByUid(String uid);

    public Post findPostByPid(Integer pid);

    @Query(value = "SELECT new com.example.demo.dto.PostResponse(p.pid as pid , p.cover as cover , p.likes as likes , p.title as title , us.pic as pic , us.name as username) FROM UserCategory u  INNER JOIN Post p on u.cid = p.cid INNER JOIN User us on p.uid=us.googleId WHERE u.uid = :uid  ")
    public List<PostResponse> findPostCoverByUid(String uid);

    @Query(value = "SELECT new com.example.demo.dto.PostResponse(p.pid as pid ,  p.cover as cover , p.likes as likes , p.title as title , us.pic as pic , us.name as username) FROM Post p INNER JOIN User us on p.uid=us.googleId WHERE p.uid = :uid  ")
    public List<PostResponse> findPostCoverForPersonalPageByUid(String uid);

    @Query(value = "SELECT MAX(pid) as max_id FROM post",nativeQuery = true)
    public Integer findNewPid();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE post AUTO_INCREMENT = 1" , nativeQuery = true)
    public void resetPostId();
}
