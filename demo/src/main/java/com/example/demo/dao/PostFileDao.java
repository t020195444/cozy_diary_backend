package com.example.demo.dao;

import com.example.demo.entity.Post;
import com.example.demo.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostFileDao extends JpaRepository<PostFile,Integer>{
}
