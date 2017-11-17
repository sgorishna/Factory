package com.spec.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Svetik on 02/11/2017.
 */
@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoles {

    @Id
    @Column(name="user_role_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "role", nullable = false, length = 45)
    private String role;



}
