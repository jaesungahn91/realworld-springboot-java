package io.github.ahnjs.realworld.infrastructure.jwt;

import io.github.ahnjs.realworld.domain.jwt.JWTPayload;
import io.github.ahnjs.realworld.domain.user.User;

import java.time.Instant;

import static java.lang.String.format;

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
        return sub;
    }

    @Override
    public boolean isExpired() {
        return iat < Instant.now().getEpochSecond();
    }

    @Override
    public String toString() {
        return format("{\"sub\":%d,\"name\":\"%s\",\"iat\":%d}", sub, name, iat);
    }
}
