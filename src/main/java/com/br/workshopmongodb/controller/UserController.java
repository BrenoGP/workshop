package com.br.workshopmongodb.controller;

import com.br.workshopmongodb.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<List<User>> ListAll(){
        User maria = new User("1", "Maria", "maria@gmail.com");
        User alex = new User("2", "Alex", "alex@gmail.com");
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(maria, alex));


        return ResponseEntity.ok().body(list);
    }
}
