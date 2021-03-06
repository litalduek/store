package com.onlinestore.controller;

import com.onlinestore.dto.Message;
import com.onlinestore.dto.user.LoginData;
import com.onlinestore.dto.user.SignupData;
import com.onlinestore.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Resource(name = "userService")
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginData loginData) {
        Message message = userService.login(loginData);
        if(!message.isSuccess()){
            return ResponseEntity.badRequest().body(message.getResponse());
        }
        return ResponseEntity.ok(message.getResponse());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupData signUp) {
        Message message = userService.signup(signUp);
        if(!message.isSuccess()){
            return ResponseEntity.badRequest().body(message.getResponse());
        }
        return ResponseEntity.ok(message.getResponse());
    }
}