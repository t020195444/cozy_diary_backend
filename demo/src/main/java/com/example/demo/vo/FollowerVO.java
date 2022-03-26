package com.example.demo.vo;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FollowerVO {
    public Integer fid;
    public String  follower1;
    public String follower2;
    public String follow_time;
}
