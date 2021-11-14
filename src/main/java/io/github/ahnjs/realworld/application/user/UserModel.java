package io.github.ahnjs.realworld.application.user;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.github.ahnjs.realworld.domain.user.User;
import lombok.Value;

@JsonTypeName("name")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@Value
public class UserModel {

    String email;
    String username;
    String token;
    String bio;
    String image;

    static UserModel fromUserAndToken(User user, String token) {
        return new UserModel(
                String.valueOf(user.getEmail()),
                String.valueOf(user.getName()),
                token,
                "",
                ""
        );
    }

}
