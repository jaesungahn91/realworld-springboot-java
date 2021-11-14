package io.github.ahnjs.realworld.application.user;

import io.github.ahnjs.realworld.domain.jwt.JWTSerializer;
import io.github.ahnjs.realworld.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserRestController {

    private final UserService userService;
    private final JWTSerializer jwtSerializer;

    public UserRestController(UserService userService, JWTSerializer jwtSerializer) {
        this.userService = userService;
        this.jwtSerializer = jwtSerializer;
    }

    @PostMapping("/users")
    public UserModel postUser(@Valid @RequestBody UserPostRequestDTO dto) {
        final var userSaved = userService.signUp(dto.toSignUpRequest());
        return UserModel.fromUserAndToken(userSaved, jwtSerializer.jwtFromUser(userSaved));
    }
}
