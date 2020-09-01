package com.virtusa.epasscovid19.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_front")
@Getter
@Setter
@DynamicUpdate(value = true)
public class UserFrontVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_front_id", length = 10)
    private long userFrontId;

    @ManyToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id")
    private UserRoleVo userRoleVo;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "email", length = 80)
    private String email;

    @Column(name = "contact_no", length = 30)
    private String contactNo;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "user_type", length = 50)
    private String userType;

    @Column(name = "is_deleted", length = 1, columnDefinition = "int default 0")
    private int isDeleted;

    @CreationTimestamp
    @Column(name = "created_on", length = 50, updatable = false)
    private Date createdOn;

    @UpdateTimestamp
    @Column(name = "modified_on", length = 50)
    private Date modifiedOn;

}
