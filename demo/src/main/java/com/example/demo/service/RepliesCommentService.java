package com.example.demo.service;

import com.example.demo.vo.RepliesCommentVO;

import java.util.Optional;

public interface RepliesCommentService {

    public Optional<String> addRepliesComment(RepliesCommentVO repliesCommentVO);

    public Optional<String> deleteRepliesComment(Integer rid);

    public Optional<String> updateRepliesComment(Integer rid , String text);


}
