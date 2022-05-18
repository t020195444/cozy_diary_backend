package com.example.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LikesPK implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer pid;

    private String uid;

}
