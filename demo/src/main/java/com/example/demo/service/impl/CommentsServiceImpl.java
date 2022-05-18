package com.example.demo.service.impl;

import com.example.demo.dao.CommentsDao;
import com.example.demo.entity.Comments;
import com.example.demo.service.CommentsService;
import com.example.demo.vo.CommentsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsDao commentsDao;

    @Override
    public Optional<String> addComments(CommentsVO commentsVO) {
        try {
            Comments comments = new Comments();
            comments.setText(commentsVO.getText());
            comments.setCommentTime(LocalDateTime.now().toString());
            comments.setUid(commentsVO.getUid());
            comments.setLikes(0);
            comments.setPid(commentsVO.getPid());
            commentsDao.save(comments);
            return Optional.of("新增留言成功");
        }catch (Exception e){
            return Optional.of("新增留言時發生以下錯誤："+e.toString());
        }
    }

    @Override
    public Optional<String> deleteComment(Integer cid) {
        Comments comments = commentsDao.getById(cid);
        try {
            commentsDao.delete(comments);
            return Optional.of("留言刪除成功");
        }catch (Exception e){
            return Optional.of("刪除留言時發生以下錯誤："+e.toString());
        }
    }

    @Override
    public Optional<String> updateComment(Integer cid , String text) {
        Comments comments = commentsDao.getById(cid);
        try {
            comments.setText(text);
            commentsDao.save(comments);
            return Optional.of("留言更新成功");
        }catch (Exception e){
            return Optional.of("更新留言時發生以下錯誤："+e.toString());
        }
    }
}
