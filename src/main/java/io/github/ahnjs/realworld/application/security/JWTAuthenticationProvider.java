package io.github.ahnjs.realworld.application.security;

import io.github.ahnjs.realworld.domain.jwt.JWTDeserializer;
import io.github.ahnjs.realworld.domain.jwt.JWTPayload;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class JWTAuthenticationProvider implements AuthenticationProvider {

    private final JWTDeserializer jwtDeserializer;

    JWTAuthenticationProvider(JWTDeserializer jwtDeserializer) {
        this.jwtDeserializer = jwtDeserializer;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return Optional.of(authentication).map(JWTAuthenticationFilter.JWT.class::cast)
                .map(JWTAuthenticationFilter.JWT::getPrincipal)
                .map(Object::toString)
                .map(token -> new JWTAuthentication(jwtDeserializer.jwtPayloadFromJWT(token), token))
                .orElseThrow(IllegalStateException::new);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationFilter.JWT.class.isAssignableFrom(authentication);
    }

    @SuppressWarnings("java:S2160")
    private static class JWTAuthentication extends AbstractAuthenticationToken {

        private final JWTPayload jwtPayload;
        private final String token;

        public JWTAuthentication(JWTPayload jwtPayload, String token) {
            super(Collections.singleton(new SimpleGrantedAuthority("USER")));
            super.setAuthenticated(true);
            this.jwtPayload = jwtPayload;
            this.token = token;
        }

        @Override
        public Object getCredentials() {
            return token;
        }

        @Override
        public Object getPrincipal() {
            return jwtPayload;
        }
    }
}
