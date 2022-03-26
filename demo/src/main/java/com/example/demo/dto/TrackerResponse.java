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
public class TrackerResponse {
    private String tracker1;
    private String tracker2;
    private String name;
    private String email;
    private String trackTime;

    public TrackerResponse(String tracker1,String tracker2,String name,String email,String trackTime){
        this.tracker1 = tracker1;
        this.tracker2 = tracker2;
        this.name = name;
        this.email = email;
        this.trackTime = trackTime;
    }

}
