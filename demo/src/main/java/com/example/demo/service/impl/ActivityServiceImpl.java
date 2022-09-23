package com.example.demo.service.impl;

import com.example.demo.dao.ActivityDao;
import com.example.demo.dto.ActivityCoverResponse;
import com.example.demo.dto.ActivityRequest;
import com.example.demo.entity.Activity;
import com.example.demo.entity.ActivityFile;
import com.example.demo.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Override
    public List<Activity> getActivityByUid(String uid) {
        return activityDao.findActivityByHolder(uid);
    }

    @Override
    public Activity getActivityByAid(Integer aid) {
        return activityDao.findActivityByAid(aid);
    }

    @Override
    public List<ActivityCoverResponse> getActivityCoverByCategory(Integer actId) {
        return activityDao.findActivityCoverByActId(actId);
    }

    @Override
    public Optional<String> addActivity(ActivityRequest activityRequest) {
//        String basicUrl = "http://172.20.10.10:8080/staticFile/";
        String basicUrl = "http://140.131.114.166:8080/staticFile/";

        try{
            Activity activity = new Activity();
            Integer nextAid = 1;
            if(activityDao.findNewAid() == null){
                activityDao.resetActivityId();
            }else {
                nextAid = activityDao.findNewAid() +1;
            }
            System.out.println(nextAid);
            List<ActivityFile> activityFiles = activityRequest.getActivity().getActivityFiles();
            for(int i=0; i < activityFiles.size();i++){
                String originalFileName = activityFiles.get(i).getActivityUrl();
                activityFiles.get(i).setAid(nextAid);
                activityFiles.get(i).setActivityUrl(basicUrl+activityRequest.getActivity().getHolder()+"/activityFile/"+nextAid+"/"+originalFileName);
            }
            activity.setActivityName(activityRequest.getActivity().getActivityName());
            activity.setActivityTime(activityRequest.getActivity().getActivityTime());
            activity.setAuditTime(activityRequest.getActivity().getAuditTime());
            activity.setHolder(activityRequest.getActivity().getHolder());
            activity.setPlaceLat(activityRequest.getActivity().getPlaceLat());
            activity.setPlaceLng(activityRequest.getActivity().getPlaceLng());
            activity.setCover(basicUrl+activityRequest.getActivity().getHolder()+"/activityFile/"+nextAid+"/"+activityRequest.getActivity().getCover());
            activity.setContent(activityRequest.getActivity().getContent());
            activity.setPayment(activityRequest.getActivity().getPayment());
            activity.setBudget(activityRequest.getActivity().getBudget());
            activity.setPublishTime(LocalDateTime.now());
            activity.setLikes(activityRequest.getActivity().getLikes());
            activity.setActId(activityRequest.getActivity().getActId());
            activity.setActivityFiles(activityFiles);
            activity.setParticipants(activityRequest.getActivity().getParticipants());
            activityDao.save(activity);
            return Optional.of(activity.getAid().toString());
        }catch (Exception e){
            return Optional.of("新增活動時發生以下錯誤："+e);
        }
    }

//    @Override
//    public Optional<String> updateActivityLikes(Integer aid, String uid) {
//        try {
//
//        }catch (Exception e){
//
//        }
//    }
}
