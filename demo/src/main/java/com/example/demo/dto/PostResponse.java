package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PostResponse {
    private Integer pid;
    private Integer likes;
    private String cover;
    private String title;
    private String pic;
    private String username;

    public PostResponse(Integer pid , String cover, Integer likes , String title , String pic, String username) {
        this.pid = pid;
        this.likes = likes;
        this.cover = cover;
        this.title = title;
        this.pic = pic;
        this.username = username;
    }
}
