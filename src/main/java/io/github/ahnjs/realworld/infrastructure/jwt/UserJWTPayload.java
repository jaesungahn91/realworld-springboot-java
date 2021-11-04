package io.github.ahnjs.realworld.infrastructure.jwt;

import io.github.ahnjs.realworld.domain.jwt.JWTPayload;
import io.github.ahnjs.realworld.domain.user.User;

public class UserJWTPayload implements JWTPayload {

    private final long sub;
    private final String name;
    private final long iat;

    static UserJWTPayload of(User user, long epochSecondExpired) {
        return new UserJWTPayload(user.getId(), String.valueOf(user.getEmail()), epochSecondExpired);
    }

    UserJWTPayload(long sub, String name, long iat) {
        this.sub = sub;
        this.name = name;
        this.iat = iat;
    }

    @Override
    public long getUserId() {
        return 0;
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
