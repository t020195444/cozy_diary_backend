package com.example.demo.entity;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "pic")
    private String pic;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "create_time")
    private LocalDateTime create_time;

    @Column(name = "email")
    private String email;


    @OneToMany(targetEntity = Tracker.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="tracker1",referencedColumnName = "google_id")
    private List<Tracker> tracker;

    @OneToMany(targetEntity = Follower.class,cascade =  CascadeType.ALL)
    @JoinColumn(name = "follower1" , referencedColumnName = "google_id")
    private List<Follower> follower;

    @OneToMany(targetEntity = UserCategory.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "uid" , referencedColumnName = "google_id")
    private List<UserCategory> userCategoryList;


}
