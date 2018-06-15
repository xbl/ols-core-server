package com.thoughtworks.nho.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
@Entity
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String name;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
}
