package ru.flint.interview.web.mapper;

import org.springframework.stereotype.Component;
import ru.flint.interview.entity.user.User;
import ru.flint.interview.web.dto.UserProfileDTO;

@Component
public class UserProfileMapper implements Mapper<User, UserProfileDTO> {

    @Override
    public User toEntity(UserProfileDTO dto) {
        return null;
    }

    @Override
    public UserProfileDTO toDTO(User entity) {
        return new UserProfileDTO(
                entity.getId(),
                entity.getFirstname(),
                entity.getLastname());
    }
}
