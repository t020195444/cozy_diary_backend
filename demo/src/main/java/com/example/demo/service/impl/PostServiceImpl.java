package com.example.demo.service.impl;


import com.example.demo.dao.LikesDao;
import com.example.demo.dao.PostDao;
import com.example.demo.dao.PostFileDao;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Likes;
import com.example.demo.entity.Post;
import com.example.demo.entity.PostFile;
import com.example.demo.exception.ProjectException;
import com.example.demo.service.PostService;
import com.example.demo.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl  implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private PostFileDao postFileDao;

    @Autowired
    private LikesDao likesDao;

    @Override
    public List<Post> getPostByUid(String uid) {
        return postDao.findPostByUid(uid);
    }

    @Override
    public Post getPostDetail(Integer pid) {
        return postDao.findPostByPid(pid);
    }


    @Override
    public Optional<String> addPost(MultipartFile[] files,PostRequest postRequest) {
        String basicUrl = "http://172.20.10.10:8080/staticFile/";
//        String basicUrl = "http://140.131.114.166:80/staticFile/";

        try{
            Post post = new Post();
            Integer nextPid = 1;
            if (postDao.findNewPid() == null){
                postDao.resetPostId();
            } else {
                nextPid = postDao.findNewPid() + 1;
            }
            List<PostFile> pFile = new ArrayList<>();
            for(int i=0;i<files.length;i++){
                PostFile postFile = new PostFile();
                postFile.setPostUrl(basicUrl+postRequest.getPost().getUid()+"/postFile/"+nextPid+"/"+files[i].getOriginalFilename());
                pFile.add(postFile);
                System.out.println("第"+i+"個"+pFile.get(i).getPostUrl());
            }


            post.setUid(postRequest.getPost().getUid());
            post.setTitle(postRequest.getPost().getTitle());
            post.setCid(postRequest.getPost().getCid());
            post.setContent(postRequest.getPost().getContent());
            post.setLikes(postRequest.getPost().getLikes());
            post.setCollects(postRequest.getPost().getCollects());
            post.setPost_time(LocalDateTime.now().toString());
            post.setCover(basicUrl+postRequest.getPost().getUid()+"/postFile/"+nextPid+"/"+postRequest.getPost().getCover());
            post.setPostFiles(pFile);
            post.setComments(postRequest.getPost().getComments());
            post.setPostLng(postRequest.getPost().getPostLng());
            post.setPostLat(postRequest.getPost().getPostLat());
            postDao.save(post);
            return Optional.of(post.getPid().toString());
        }catch (Exception e){
            System.out.println(e.toString());
            throw new ProjectException(e);
        }
    }

    @Override
    public Optional<String> updatePostLikes(Integer pid,String uid) {
        try {
            Likes likes = likesDao.getLikesByPidAndUid(pid,uid);
            Optional<Post> post = postDao.findById(pid);
            Integer newLikes = post.get().getLikes();
            if (likes == null){
                Likes addLikes = new Likes();
                addLikes.setPid(pid);
                addLikes.setUid(uid);
                addLikes.setLikeTime(LocalDateTime.now());
                likesDao.save(addLikes);
                newLikes +=1;
                post.get().setLikes(newLikes);
                postDao.save(post.get());
                return Optional.of("新增讚數成功");
            }else {
                newLikes -=1;
                post.get().setLikes(newLikes);
                postDao.save(post.get());
                likesDao.delete(likes);
                return Optional.of("新增讚數成功");
            }
        }catch (Exception e){
            throw new ProjectException(e);
        }
    }

    @Override
    public List<PostResponse> getPostCoverByUid(String uid) {
        return postDao.findPostCoverByUid(uid);
    }

    @Override
    public List<PostResponse> getPostCoverForPersonalPageByUid(String uid) {
        return postDao.findPostCoverForPersonalPageByUid(uid);
    }
}
