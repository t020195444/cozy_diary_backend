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
public class FollowerResponse {
    private String follower1;
    private String follower2;
    private String name;
    private String email;
    private String followTime;

    public FollowerResponse(String follower1,String follower2,String name,String email,String followTime){
        this.follower1 = follower1;
        this.follower2 = follower2;
        this.name = name;
        this.email = email;
        this.followTime = followTime;
    }

}
