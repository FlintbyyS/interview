package ru.flint.interview.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.flint.interview.service.UserService;
import ru.flint.interview.entity.user.User;
import ru.flint.interview.web.dto.UserProfileDTO;
import ru.flint.interview.web.mapper.UserProfileMapper;


@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String REST_URL = "/api/v1/users";
    private final UserProfileMapper profileMapper;
    private final UserService service;

    public UserController(UserProfileMapper profileMapper, UserService service) {
        this.profileMapper = profileMapper;
        this.service = service;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/profile")
    public UserProfileDTO getProfile(@AuthenticationPrincipal User user) {
        return profileMapper.toDTO(get(user.getId()));
    }

    @PutMapping(path = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User updateProfile(@AuthenticationPrincipal User user, @Valid @RequestBody UserProfileDTO userDTO) {
        return service.update(user.getId(), userDTO);
    }
}