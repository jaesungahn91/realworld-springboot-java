package io.github.ahnjs.realworld.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "users")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String email;

    private String password;

}
