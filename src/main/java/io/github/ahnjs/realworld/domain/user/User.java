package io.github.ahnjs.realworld.domain.user;

import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Table(name = "users")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Profile profile;

    @Embedded
    private Password password;

    static User of(Email email, UserName userName, Password password) {
        return new User(email, new Profile(userName), password);
    }

    private User(Email email, Profile profile, Password password) {
        this.email = email;
        this.profile = profile;
        this.password = password;
    }

    protected User() {
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getName() {
        return profile.getUserName();
    }

    public String getBio() {
        return profile.getBio();
    }
    public Image getImage() {
        return profile.getImage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
