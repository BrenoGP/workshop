package com.br.workshopmongodb.controller;

import com.br.workshopmongodb.controller.util.URL;
import com.br.workshopmongodb.dto.UserDTO;
import com.br.workshopmongodb.entity.Post;
import com.br.workshopmongodb.entity.User;
import com.br.workshopmongodb.service.PostService;
import com.br.workshopmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> ListAll(){

        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> FindById(@PathVariable String id){

        Post obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> FindByTitle(@RequestParam(value ="text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        List<Post> list = service.findByTitle(text);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> FullSearch(
    @RequestParam(value ="text", defaultValue = "") String text,
    @RequestParam(value ="minDate", defaultValue = "") String minDate,
    @RequestParam(value ="maxDate", defaultValue = "") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        List<Post> list = service.fullSearch(text, min, max);

        return ResponseEntity.ok().body(list);
    }
}