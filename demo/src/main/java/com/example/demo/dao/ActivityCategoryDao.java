package com.example.demo.dao;

import com.example.demo.entity.ActivityCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityCategoryDao extends JpaRepository<ActivityCategory,Integer> {
    public List<ActivityCategory> findAllByActId(Integer aid);
}
