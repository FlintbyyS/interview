package ru.flint.interview.web.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.flint.interview.config.security.AuthorizedUser;
import ru.flint.interview.entity.User;
import ru.flint.interview.service.UserService;
import ru.flint.interview.web.dto.UserDTO;
import ru.flint.interview.web.dto.UserSecurityDTO;
import ru.flint.interview.web.mapper.UserMapper;

import java.net.URI;

import static ru.flint.interview.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = UserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    public static final String REST_URL = "/api/version1.0/users";

    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping(value = "/profile/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> saveRegister(@Valid @RequestBody UserSecurityDTO dto) {
        User created = service.register(mapper.toEntity(dto));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDTO(created));
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable long id) {
        return mapper.toDTO(service.getById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createWithLocation(@Valid @RequestBody UserDTO userDTO) {
        checkNew(userDTO);
        User created = service.create(mapper.toEntity(userDTO));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(mapper.toDTO(created));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO update(@Valid @RequestBody UserDTO userDTO, @PathVariable long id) {
        return mapper.toDTO(service.update(id, mapper.toEntity(userDTO)));
    }

    @GetMapping("/profile")
    public UserDTO getProfile(@AuthenticationPrincipal AuthorizedUser user) {
        return get(user.id());
    }

    @PutMapping(path = "/profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateProfile(@AuthenticationPrincipal AuthorizedUser user, @Valid @RequestBody UserDTO userDTO) {
        return update(userDTO, user.id());
    }
}
