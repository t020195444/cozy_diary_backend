package com.example.demo.dao;

import com.example.demo.entity.Follower;
import com.example.demo.entity.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TrackerDao extends JpaRepository<Tracker,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Tracker t where t.tracker1=:tracker1 and t.tracker2=:tracker2")
    public void deleteTrackById(@Param("tracker1") String tracker1, @Param("tracker2") String tracker2);
}
