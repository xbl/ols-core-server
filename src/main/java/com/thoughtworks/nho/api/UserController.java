package com.thoughtworks.nho.api;

import com.thoughtworks.nho.cofiguration.security.JWTUser;
import com.thoughtworks.nho.domain.User;
import com.thoughtworks.nho.service.UserService;
import com.thoughtworks.nho.util.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.thoughtworks.nho.cofiguration.security.APISecureRolePrivilege.CREATE_USER;
import static com.thoughtworks.nho.cofiguration.security.APISecureRolePrivilege.RETRIVE_USER;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(CREATE_USER)
    public JWTUser create(@RequestBody User user) {
        return UserFactory.fromUser(userService.create(user));
    }

    @GetMapping("{username}")
    @ResponseStatus(HttpStatus.OK)
    @Secured(RETRIVE_USER)
    public JWTUser find(@PathVariable String username) {
        return UserFactory.fromUser(userService.findByName(username));
    }
}
