package ru.flint.interview.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.flint.interview.entity.User;
import ru.flint.interview.repository.UserRepository;

import java.util.Optional;

import static ru.flint.interview.util.validation.ValidationUtil.checkFound;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }
    public User create(@NotNull User user) {
        log.info("Create user: {}",user);
        return repository.save(user);
    }

    public void delete(long id) {
        log.info("Delete user with id = {}",id);
        repository.deleteById(id);
    }

    public User getById(long id) {
        log.info("Find user with id = {}",id);
        return checkFound(repository.findById(id),id, User.class);
    }

    @Transactional
    public User update(long id, User user) {
        log.info("Update user with id = {}", user.getId());
        User storedUser = checkFound(repository.findById(id), id, User.class);
        user.setId(id);
        user.setPassword(storedUser.getPassword()); // do not update the password, it must be updated in a separate way
        user.setRoles(storedUser.getRoles()); // do not update roles, it must be updated in a separate way
        return repository.save(user);
    }

    @Transactional
    public User register(User user){
        Optional<User> userFromDb = repository.findByEmailIgnoreCase(user.getEmail());
        if (userFromDb.isEmpty()) {
            log.info("Registering user: {}", user);
            return repository.save(user);
        }
        return null;
    }
}
