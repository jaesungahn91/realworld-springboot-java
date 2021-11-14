package io.github.ahnjs.realworld.domain.user;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);
}
