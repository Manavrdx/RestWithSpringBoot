package com.test.station.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "users")
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private String password;

    private String email;
}
