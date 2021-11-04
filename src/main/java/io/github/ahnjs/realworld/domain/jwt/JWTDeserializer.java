package io.github.ahnjs.realworld.domain.jwt;

public interface JWTDeserializer {

    JWTPayload jwtPayloadFromJWT(String jwtToken);

}
