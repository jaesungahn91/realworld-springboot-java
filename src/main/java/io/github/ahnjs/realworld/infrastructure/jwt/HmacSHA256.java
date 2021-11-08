package io.github.ahnjs.realworld.infrastructure.jwt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class HmacSHA256 {

    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    public HmacSHA256() {
    }

    public static byte[] sign(byte[] secret, String message) {
        try {
            final var hmacSHA256 = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            hmacSHA256.init(new SecretKeySpec(secret, HMAC_SHA256_ALGORITHM));
            return hmacSHA256.doFinal(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new HmacSHA256SignFailedException(e);
        }
    }

    private static class HmacSHA256SignFailedException extends RuntimeException {
        public HmacSHA256SignFailedException(Throwable cause) {
            super(cause);
        }
    }
}
