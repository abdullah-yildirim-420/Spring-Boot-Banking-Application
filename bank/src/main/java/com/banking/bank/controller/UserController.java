package com.banking.bank.controller;

import com.banking.bank.dto.*;
import com.banking.bank.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO userCreateDTO){
        UserResponseDTO responseDTO = userService.create(userCreateDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> get(@PathVariable Long id){
        UserResponseDTO responseDTO = userService.get(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/username")
    public ResponseEntity<String> updateUsername(@PathVariable Long id,@Valid @RequestBody UpdateUsernameDTO updateUsernameDTO){
        userService.updateUsername(id, updateUsernameDTO);
        return ResponseEntity.ok().body("Username has been updated.");
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long id,@Valid @RequestBody UpdatePasswordDTO updatePasswordDTO){
        userService.updatePassword(id,updatePasswordDTO);
        return ResponseEntity.ok().body("Password has been updated.");
    }

    @PutMapping("/{id}/email")
    public ResponseEntity<String> updateEmail(@PathVariable Long id,@Valid @RequestBody UpdateEmailDTO updateEmailDTO){
        userService.updateEmail(id,updateEmailDTO);
        return ResponseEntity.ok().body("Email has been updated.");
    }





}
