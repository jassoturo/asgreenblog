package com.server.springacl.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @GetMapping
    @PreAuthorize("hasPermission('USER', 'READ')")
    public ResponseEntity<List<UserDto>> getAllUser() {

    }
}
