package ru.flint.interview.config.security;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import ru.flint.interview.entity.User;

@Getter
@ToString(of = "user")
public class AuthorizedUser extends org.springframework.security.core.userdetails.User{

    private final User user;

    public AuthorizedUser(@NotNull User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public long id() {
        return user.getId();
    }
}
