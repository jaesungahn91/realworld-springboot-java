package io.github.ahnjs.realworld.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.github.ahnjs.realworld.domain.user.Email;
import io.github.ahnjs.realworld.domain.user.UserName;
import io.github.ahnjs.realworld.domain.user.UserSignUpRequest;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@JsonTypeName("user")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Value
public class UserPostRequestDTO {
    @javax.validation.constraints.Email
    String email;
    @NotBlank
    String username;
    @NotBlank
    String password;

    UserSignUpRequest toSignUpRequest() {
        return new UserSignUpRequest(
                new Email(email),
                new UserName(username),
                password
        );
    }
}
