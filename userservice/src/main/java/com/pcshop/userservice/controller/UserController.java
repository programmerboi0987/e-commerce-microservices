package com.pcshop.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("api/user/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        System.out.println("Auth = " + authentication);
        return ResponseEntity.ok("User = " + authentication.getName());
    }
}
