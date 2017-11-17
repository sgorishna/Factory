package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Svetik on 02/11/2017.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "active", nullable = false)
    private byte active;




}
