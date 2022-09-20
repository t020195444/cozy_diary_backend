package com.example.demo.dao;

import com.example.demo.entity.RepliesComments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepliesCommentDao extends JpaRepository<RepliesComments,Integer> {
}
