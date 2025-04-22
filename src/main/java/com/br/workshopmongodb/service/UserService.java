package com.br.workshopmongodb.service;

import com.br.workshopmongodb.dto.UserDTO;
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
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public User insert(User obj) {
        return repo.insert(obj);
    }
    public User update(User obj) {
        User newobj = repo.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
        updateData(newobj, obj);
        return repo.save(newobj);
    }
    private void updateData(User newobj, User obj) {
        newobj.setName(obj.getName());
        newobj.setEmail(obj.getEmail());
    }

    public void delete(String id) {
        findById(id);
        repo.deleteById(id);
    }

    public User fromDTO (UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

}
