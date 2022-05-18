package com.example.demo.service.impl;


import com.example.demo.dao.LikesDao;
import com.example.demo.dao.PostDao;
import com.example.demo.dto.PostRequest;
import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Likes;
import com.example.demo.entity.Post;
import com.example.demo.exception.ProjectException;
import com.example.demo.service.PostService;
import com.example.demo.vo.PostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl  implements PostService {
    @Autowired
    private PostDao postDao;

    @Autowired
    private LikesDao likesDao;

    @Override
    public List<Post> getPostByUid(String uid) {
        return postDao.findPostByUid(uid);
    }


    @Override
    public Optional<String> addPost(PostRequest postRequest) {
        try{
            Post post = new Post();
            post.setUid(postRequest.getPost().getUid());
            post.setTitle(postRequest.getPost().getTitle());
            post.setCid(postRequest.getPost().getCid());
            post.setContent(postRequest.getPost().getContent());
            post.setLikes(postRequest.getPost().getLikes());
            post.setCollects(postRequest.getPost().getCollects());
            post.setPost_time(LocalDateTime.now().toString());
            post.setCover(postRequest.getPost().getCover());
            post.setPostFiles(postRequest.getPost().getPostFiles());
            post.setComments(postRequest.getPost().getComments());
            postDao.save(post);
            return Optional.of(post.getPid().toString());
        }catch (Exception e){
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
}
