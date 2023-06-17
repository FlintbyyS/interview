package ru.flint.interview.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flint.interview.entity.user.User;
import ru.flint.interview.repository.UserRepository;
import ru.flint.interview.web.dto.UserProfileDTO;


import static ru.flint.interview.util.validation.ValidationUtil.checkFound;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void delete(long id) {
        log.info("Delete user with id = {}", id);
        repository.deleteById(id);
    }

    public User getById(long id) {
        log.info("Find user with id = {}", id);
        return checkFound(repository.findById(id), id, User.class);
    }

    @Transactional
    public User update(long id, UserProfileDTO dto) {
        log.info("Update user with id = {}", id);
        User storedUser = checkFound(repository.findById(id), id, User.class);
        storedUser.setFirstname(dto.getFirstName());
        storedUser.setLastname(dto.getLastName());
        return repository.save(storedUser);
    }
}
