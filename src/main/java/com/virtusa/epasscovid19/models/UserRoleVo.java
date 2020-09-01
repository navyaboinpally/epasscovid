package com.virtusa.epasscovid19.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_role")
@Data
public class UserRoleVo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", length = 10)
    private long userRoleId;

    @Column(name = "user_role_name", length = 50)
    private String userRoleName;
}
