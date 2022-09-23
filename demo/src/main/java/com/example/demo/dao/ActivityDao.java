package com.example.demo.dao;

import com.example.demo.dto.ActivityCoverResponse;
import com.example.demo.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ActivityDao extends JpaRepository<Activity, Integer> {

    public List<Activity> findActivityByHolder(String holder);

    public Activity findActivityByAid(Integer aId);

    @Query(value = "SELECT new com.example.demo.dto.ActivityCoverResponse(a.cover as cover , a.likes as likes,  a.activityName as activityName , us.pic as pic , us.name as username , a.activityTime as activityTime , a.placeLng as placeLng , a.placeLat as placeLat) FROM ActivityCategory ac  INNER JOIN Activity a on ac.actId = a.actId INNER JOIN User us on a.holder=us.googleId WHERE ac.actId = :actId  ")
    public List<ActivityCoverResponse> findActivityCoverByActId(Integer actId);

    @Query(value = "SELECT MAX(aid) as max_id FROM activity",nativeQuery = true)
    public Integer findNewAid();

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE activity AUTO_INCREMENT = 1" , nativeQuery = true)
    public void resetActivityId();

}
