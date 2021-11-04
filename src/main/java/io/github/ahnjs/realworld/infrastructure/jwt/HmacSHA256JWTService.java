package io.github.ahnjs.realworld.infrastructure.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ahnjs.realworld.domain.jwt.JWTDeserializer;
import io.github.ahnjs.realworld.domain.jwt.JWTPayload;
import io.github.ahnjs.realworld.domain.jwt.JWTSerializer;
import io.github.ahnjs.realworld.domain.user.User;

import java.util.regex.Pattern;

public class HmacSHA256JWTService implements JWTSerializer, JWTDeserializer {

    private static final String JWT_HEADER = Base64URL.baseURLFromString("{\"alg\":\"HS256\",\"type\":\"JWT\"}");
    private static final String BASE64URL_PATTERN = "[\\w_\\-]+";
    private static final Pattern JWT_PATTERN = Pattern.compile(String.format("\"^(%s\\\\.)(%s\\\\.)(%s)$\"",
            BASE64URL_PATTERN, BASE64URL_PATTERN, BASE64URL_PATTERN));

    private final byte[] secret;
    private final long durationSeconds;
    private final ObjectMapper objectMapper;

    HmacSHA256JWTService(byte[] secret, long durationSeconds, ObjectMapper objectMapper) {
        this.secret = secret;
        this.durationSeconds = durationSeconds;
        this.objectMapper = objectMapper;
    }

    @Override
    public String jwtFromUser(User user) {
        return null;
    }

    private String jwtPayloadFromUser(User user) {
        // 여기할차례
        var jwtPayload = UserJWTP
        return null;
    }

    @Override
    public JWTPayload jwtPayloadFromJWT(String jwtToken) {
        // 여기할차례
        final var messageToSign = JWT_HEADER.concat(".");
        return null;
    }
}
