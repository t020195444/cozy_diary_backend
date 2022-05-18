package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PostResponse {
    private String uid;
    private Integer likes;
    private String cover;
    private String title;
    private Integer cid;

    public PostResponse(String uid, String cover, Integer likes , String title , Integer cid) {
        this.uid = uid;
        this.likes = likes;
        this.cover = cover;
        this.title = title;
        this.cid = cid;
    }
}
