package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentsVO {
    public Integer commentId;
    public String text;
    public Integer pid;
    public String uid;
    public String commentTime;
    public Integer likes;
}
