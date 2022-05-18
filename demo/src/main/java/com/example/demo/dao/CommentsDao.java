package com.example.demo.dao;


import com.example.demo.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsDao extends JpaRepository<Comments,Integer> {
}
