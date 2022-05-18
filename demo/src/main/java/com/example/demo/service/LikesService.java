package com.example.demo.service;

import com.example.demo.entity.Likes;
import com.example.demo.vo.LikesVO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface LikesService {
    public List<Likes> getLikesListByPid(Integer pid);

    public Optional<String> addLikes(LikesVO likesVO);

    public Optional<String> deleteLikes(LikesVO likesVO);
}
