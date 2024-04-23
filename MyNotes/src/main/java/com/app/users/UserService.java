package com.app.users;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public ResponseEntity<?> createUser(User user){
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    public void deleteUserByName(String login){
        repository.deleteUserByLogin(login);
    }

    public User getUserByName(String name) {
        return repository.findByLogin(name);
    }
}
