package com.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/users")
@RestController
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    private List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping(path = "{login}")
    private User getUser(@PathVariable String login){
        return service.getUserByName(login);
    }

    @PostMapping
    private ResponseEntity<?> addUser(@RequestBody User user){
        return service.createUser(user);
    }

    @DeleteMapping(path = "{login}")
    private void deleteUser(@PathVariable String login){
        service.deleteUserByName(login);
    }
}
