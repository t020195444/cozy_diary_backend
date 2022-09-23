package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;

    @Column(name = "place_lng")
    private Double placeLng;

    @Column(name = "place_lat")
    private Double placeLat;

    @Column(name = "holder")
    private String holder;

    @Column(name = "cover")
    private String cover;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_time")
    private LocalDateTime activityTime;

    @Column(name = "audit_time")
    private LocalDateTime auditTime;

    @Column(name = "content")
    private String content;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "publish_time")
    private LocalDateTime publishTime;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "act_id")
    private Integer actId;

    @OneToMany(targetEntity = ActivityFile.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "aid" , referencedColumnName = "aid")
    private List<ActivityFile> activityFiles;

    @OneToMany(targetEntity = Participant.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "aid", referencedColumnName = "aid")
    private List<Participant> participants;
}
