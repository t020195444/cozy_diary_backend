package com.example.demo.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "activity_files")
public class ActivityFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer afId;

    @Column(name = "activity_url")
    private String activityUrl;

    @Column(name = "aid")
    private Integer aid;
}
