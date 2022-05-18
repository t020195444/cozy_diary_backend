package com.example.demo.service.impl;


import com.example.demo.dao.LikesDao;
import com.example.demo.entity.Likes;
import com.example.demo.service.LikesService;
import com.example.demo.vo.LikesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LikesServiceImpl  implements LikesService {
    @Autowired
    private LikesDao likesDao;

    @Override
    public List<Likes> getLikesListByPid(Integer pid) {
        return likesDao.getLikesListByPid(pid);
    }

    @Override
    public Optional<String> addLikes(LikesVO likesVO) {
        try {
            Likes likes = new Likes();
            likes.setPid(likesVO.getPid());
            likes.setUid(likesVO.getUid());
            likes.setLikeTime(LocalDateTime.now());
            likesDao.save(likes);
            return Optional.of("新增按讚資料成功");
        }catch (Exception e){
            return Optional.of("新增按讚資料時發生以下錯誤："+e.toString());
        }
    }

    @Override
    public Optional<String> deleteLikes(LikesVO likesVO) {
        try {
            Likes likes = new Likes();
            likes.setPid(likesVO.getPid());
            likes.setUid(likesVO.getUid());
            likes.setLikeTime(LocalDateTime.now());
            likesDao.delete(likes);
            return Optional.of("刪除按讚資料成功");
        }catch (Exception e){
            return Optional.of("刪除按讚資料時發生以下錯誤："+e.toString());
        }
    }
}
