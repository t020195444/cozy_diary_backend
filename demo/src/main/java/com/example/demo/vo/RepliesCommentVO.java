package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RepliesCommentVO {
    public Integer rid;
    public String text;
    public Integer commentId;
    public String uid;
    public String repliesTime;
    public Integer likes;
}
