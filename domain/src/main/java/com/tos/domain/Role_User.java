package com.tos.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by qq136 on 2017/6/22.
 */
@Entity
@Data
public class Role_User {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="user_id")
    private User user;

}
