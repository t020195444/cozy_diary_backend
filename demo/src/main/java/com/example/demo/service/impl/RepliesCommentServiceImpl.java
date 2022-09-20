package com.example.demo.service.impl;


import com.example.demo.dao.RepliesCommentDao;
import com.example.demo.entity.RepliesComments;
import com.example.demo.service.RepliesCommentService;
import com.example.demo.vo.RepliesCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RepliesCommentServiceImpl implements RepliesCommentService {
    @Autowired
    private RepliesCommentDao repliesCommentDao;

    @Override
    public Optional<String> addRepliesComment(RepliesCommentVO repliesCommentVO) {
        try {
            RepliesComments repliesComments = new RepliesComments();
            repliesComments.setText(repliesCommentVO.getText());
            repliesComments.setRepliesTime(LocalDateTime.now().toString());
            repliesComments.setUid(repliesCommentVO.getUid());
            repliesComments.setLikes(0);
            repliesComments.setCommentId(repliesCommentVO.getCommentId());
            repliesCommentDao.save(repliesComments);
            return Optional.of("新增留言成功");
        }catch (Exception e){
            return Optional.of("新增留言時發生以下錯誤："+e.toString());
        }
    }

    @Override
    public Optional<String> deleteRepliesComment(Integer rid) {
        RepliesComments repliesComments = repliesCommentDao.getById(rid);
        try {
            repliesCommentDao.delete(repliesComments);
            return Optional.of("留言刪除成功");
        }catch (Exception e){
            return Optional.of("刪除留言時發生以下錯誤："+e.toString());
        }
    }

    @Override
    public Optional<String> updateRepliesComment(Integer rid,String text) {
        RepliesComments repliesComments = repliesCommentDao.getById(rid);
        try {
            repliesComments.setText(text);
            repliesCommentDao.save(repliesComments);
            return Optional.of("留言更新成功");
        }catch (Exception e){
            return Optional.of("更新留言時發生以下錯誤："+e.toString());
        }
    }
}
