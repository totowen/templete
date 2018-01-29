package com.tos.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by qq136 on 2017/6/22.
 */
@Entity
@Data
public class UserP implements Serializable{


    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String email;
    @Column
    private Date lastPasswordResetDate;

    @Transient
    private List<String> roles;


}
