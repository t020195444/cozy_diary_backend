package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class LikesVO {
    public Integer pid;
    public String uid;
    public LocalDateTime likeTime;
}
