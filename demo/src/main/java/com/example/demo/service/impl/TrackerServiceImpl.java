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
            trackerDao.save(tracker);

            return Optional.of("新增追蹤成功");
        }catch (Exception e){
            return Optional.of("新增追蹤發生以下錯誤："+e);
        }
    }

    @Override
    public Optional<String> deleteTrack(String tracker1, String tracker2) {
        try{
            trackerDao.deleteTrackById(tracker1,tracker2);
            return Optional.of("刪除追蹤成功");
        }catch (Exception e){
            return Optional.of("刪除追蹤發生以下錯誤："+e);
        }
    }
}
