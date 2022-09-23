package com.example.demo.service;

import com.example.demo.dto.ActivityCoverResponse;
import com.example.demo.dto.ActivityRequest;
import com.example.demo.entity.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {

    public List<Activity> getActivityByUid(String uid);

    public Activity getActivityByAid(Integer aid);

    public List<ActivityCoverResponse> getActivityCoverByCategory(Integer actId);

    public Optional<String> addActivity(ActivityRequest activityRequest);

//    public Optional<String> updateActivityLikes(Integer aid, String uid);
}
