package com.example.demo.dao;

import com.example.demo.entity.Category;
import com.example.demo.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryDao extends JpaRepository<UserCategory,Integer> {
    public List<UserCategory> findAllByUid(String uid);
}
