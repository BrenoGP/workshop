package com.br.workshopmongodb.controller;

import com.br.workshopmongodb.dto.UserDTO;
import com.br.workshopmongodb.entity.Post;
import com.br.workshopmongodb.entity.User;
import com.br.workshopmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> ListAll(){

        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> FindUser(@PathVariable String id){

        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> FindPosts(@PathVariable String id){

        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }

    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody UserDTO objDto){

        User obj = service.fromDTO(objDto);

        return new ResponseEntity<>(service.insert(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> UpdateUser(@PathVariable String id, @RequestBody UserDTO objDto){

        User obj = service.fromDTO(objDto);
        obj.setId(id);

        return new ResponseEntity<>(service.update(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable String id){

        service.delete(id);
        return ResponseEntity.ok().body("Usuário deletado com sucesso!");
    }
}
