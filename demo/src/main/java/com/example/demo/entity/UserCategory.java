package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@IdClass(UserCategoryPK.class)
@Table(name = "userCategory")
public class UserCategory implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "uid")
    private String uid;

    @Id
    @Column(name = "cid")
    private Integer cid;
}
