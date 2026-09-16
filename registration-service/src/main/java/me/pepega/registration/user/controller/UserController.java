package me.pepega.registration.user.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/auth/registration")
    public RequestEntity userRegistration(@RequestBody)
}
