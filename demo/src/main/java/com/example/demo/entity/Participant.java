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
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer partId;

    @Column(name = "participant")
    private String participant;

    @Column(name = "aid")
    private Integer aid;

    @Column(name = "qualified")
    private Integer qualified;

    @Column(name = "reason")
    private String reason;

    @Column(name = "release_time")
    private LocalDateTime releaseTime;
}
