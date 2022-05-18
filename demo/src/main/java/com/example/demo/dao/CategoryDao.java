package com.example.demo.dao;

import com.example.demo.entity.Category;
import com.example.demo.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer>{
    public List<Category> findAllByCid(Integer cid);
}
