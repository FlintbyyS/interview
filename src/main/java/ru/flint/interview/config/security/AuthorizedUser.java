package ru.flint.interview.config.security;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import ru.flint.interview.entity.user.User;

@Getter
@ToString(of = "user")
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public AuthorizedUser(@NotNull User user) {
        super(user.getUsername(), user.getPassword(), user.getAuthorities());
        this.user = user;
    }

    public long id() {
        return user.getId();
    }
}
