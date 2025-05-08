package com.br.workshopmongodb.repository;

import com.br.workshopmongodb.entity.Post;
import com.br.workshopmongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

    List<Post> findByTitleContainingIgnoreCase(String text);
}
