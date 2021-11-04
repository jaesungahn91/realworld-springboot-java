package io.github.ahnjs.realworld.domain.jwt;

import java.io.Serializable;

public interface JWTPayload extends Serializable {

    long getUserId();
    boolean isExpired();

}
