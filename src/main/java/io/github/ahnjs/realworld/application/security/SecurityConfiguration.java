package io.github.ahnjs.realworld.application.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableConfigurationProperties(SecurityConfigurationProperties.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    private final SecurityConfigurationProperties properties;

    public SecurityConfiguration(SecurityConfigurationProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors();
        http.formLogin().disable();
        http.logout().disable();

        http.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .anyRequest().authenticated();
    }


}

@ConstructorBinding
@ConfigurationProperties("security")
class SecurityConfigurationProperties {
    private final List<String> allowedOrigins;

    public SecurityConfigurationProperties(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }
}