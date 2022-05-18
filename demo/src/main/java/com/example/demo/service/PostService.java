package com.example.demo.service;


import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Post;
import com.example.demo.vo.PostVO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public List<Post> getPostByUid(String uid);

    public Optional<String> addPost(PostRequest postRequest);

    public Optional<String> updatePostLikes(Integer pid,String uid);

    public List<PostResponse> getPostCoverByUid(String uid);
}
