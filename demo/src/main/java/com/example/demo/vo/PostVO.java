package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostVO {
    public Integer pid;
    public String uid;
    public String title;
    public String content;
    public Integer likes;
    public Integer collects;
    public String post_time;
    public String cover;
    public String modify_time;
    public Integer cid;
}
