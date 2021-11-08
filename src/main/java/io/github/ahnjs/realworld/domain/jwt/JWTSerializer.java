package io.github.ahnjs.realworld.domain.jwt;

import io.github.ahnjs.realworld.domain.user.User;

public interface JWTSerializer {

    String jwtFromUser(User user);
}
