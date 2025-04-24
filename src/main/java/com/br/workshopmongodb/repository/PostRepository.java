package com.br.workshopmongodb.repository;

import com.br.workshopmongodb.entity.Post;
import com.br.workshopmongodb.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
