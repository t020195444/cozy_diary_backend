package com.example.demo.dao;

import com.example.demo.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FollowerDao extends JpaRepository<Follower,Integer> {

    public List<Follower> findAllByFollower1(String follower1);

    @Transactional
    @Modifying
    @Query("delete from Follower f where f.follower1=:follower1 and f.follower2=:follower2")
    public void deleteFollowerById(@Param("follower1") String follower1, @Param("follower2") String follower2);
}
