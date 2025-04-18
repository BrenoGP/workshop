package com.br.workshopmongodb.service;

import com.br.workshopmongodb.entity.User;
import com.br.workshopmongodb.exceptions.ObjectNotFoundException;
import com.br.workshopmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> findAll() {
    return repo.findAll();

    }

    public User findById(String id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

}
