package com.example.demo.service.impl;

import com.example.demo.dao.TrackerDao;
import com.example.demo.entity.Tracker;
import com.example.demo.service.TrackerService;
import com.example.demo.vo.TrackerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TrackerServiceImpl implements TrackerService{

    @Autowired
    private TrackerDao trackerDao;

    @Override
    public Optional<String> addTracker(TrackerVO trackerVO){
        try{
            Tracker tracker = new Tracker();
            tracker.setTracker1(trackerVO.getTracker1());
            tracker.setTracker2(trackerVO.getTracker2());
            tracker.setTrack_time(LocalDateTime.now());
            Integer id = trackerDao.insert(tracker);
            if(id == 0){
                System.out.println("新增會員帳號時發生錯誤");
                return Optional.of("新增會員帳號時發生錯誤");
            }
            return Optional.empty();
        }catch (Exception e){
            System.out.println(e);
            return Optional.empty();
        }
    }
}
