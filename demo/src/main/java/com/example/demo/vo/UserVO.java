package com.example.demo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
    private String google_id;
    private String name;
    private Integer age;
    private Integer sex;
    private String introduction;
    private String pic;
    private String birth;
    private String create_time;
    private String email;
}
