package ru.flint.interview.web.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.flint.interview.entity.Role;
import ru.flint.interview.entity.User;
import ru.flint.interview.web.dto.UserDTO;
import ru.flint.interview.web.dto.UserSecurityDTO;

import java.util.Set;

@Component
public class UserMapper implements Mapper<User, UserDTO>{
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User toEntity(UserDTO dto) {
        User user = new User(
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getEnabled(),
                dto.getRoles()
        );
        user.setId(dto.getId());
        return user;
    }

    @Override
    public UserDTO toDTO(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                "***", // do not expose password to frontend
                entity.isEnabled(),
                entity.getRoles()
        );
    }

    public User toEntity(UserSecurityDTO dto){
        User user = new User(
                dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                passwordEncoder.encode(dto.getPassword()),
                true,
                Set.of(Role.USER)
        );
        user.setId(dto.getId());
        return user;
    }
}
