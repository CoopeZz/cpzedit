package com.coopezz.cpzedit.controllers;

import com.coopezz.cpzedit.dto.UserDTO;
import com.coopezz.cpzedit.models.entity.User;
import com.coopezz.cpzedit.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class apiController {

    private final UserService userSVC;

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable Long id) {
        return ResponseEntity.ok(UserDTO.buildUserDTO(userSVC.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user: userSVC.findAll()) {
            usersDTO.add(UserDTO.buildUserDTO(user));
        }
        return ResponseEntity.ok(usersDTO);
    }
}