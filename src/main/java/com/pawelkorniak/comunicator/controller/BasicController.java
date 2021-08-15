package com.pawelkorniak.comunicator.controller;

import com.pawelkorniak.comunicator.model.ComunicatorUser;
import com.pawelkorniak.comunicator.repository.UserRepository;
import com.pawelkorniak.comunicator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasicController {

    private final UserService userService;

    @PostMapping("save")
    public ComunicatorUser saveUser(@RequestBody ComunicatorUser user){
        return userService.save(user);
    }

    @GetMapping("{id}")
    public ComunicatorUser getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

}
