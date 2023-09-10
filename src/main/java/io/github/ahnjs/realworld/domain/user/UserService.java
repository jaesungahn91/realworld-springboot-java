package io.github.ahnjs.realworld.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User signUp(UserSignUpRequest request) {
        final var encodedPassword = Password.of(request.getRawPassword(), passwordEncoder);
        return userRepository.save(User.of(request.getEmail(),
                request.getUserName(),
                encodedPassword
        ));
    }
}
