package com.example.demo.service;

import com.example.demo.vo.TrackerVO;

import java.util.Optional;

public interface TrackerService  {

    public Optional<String> addTracker(TrackerVO trackerVO);
}