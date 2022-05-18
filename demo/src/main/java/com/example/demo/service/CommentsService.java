package com.example.demo.service;

import com.example.demo.entity.Comments;
import com.example.demo.vo.CommentsVO;

import java.util.Optional;

public interface CommentsService {

    public Optional<String> addComments(CommentsVO commentsVO);

    public Optional<String> deleteComment(Integer cid);

    public Optional<String> updateComment(Integer cid, String text);
}
