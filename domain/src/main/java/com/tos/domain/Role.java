package com.tos.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by qq136 on 2017/6/22.
 */
@Entity
@Data
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String role;

}
